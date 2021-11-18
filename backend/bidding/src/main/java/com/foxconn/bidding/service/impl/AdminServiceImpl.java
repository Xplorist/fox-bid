package com.foxconn.bidding.service.impl;

import com.foxconn.bidding.mapper.AdminMapper;
import com.foxconn.bidding.mapper.UserMapper;
import com.foxconn.bidding.model.*;
import com.foxconn.bidding.service.AdminService;
import com.foxconn.bidding.service.EmailService;
import com.foxconn.bidding.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;

    // 【01】管理員查詢賬號審核任務(分頁)
    @Override
    public ResultParam query_user_au_list_pagi(USER_INFO_bean param, HttpServletRequest request) {
        Integer pageIndex = param.getPageIndex();
        Integer pageSize = param.getPageSize();
        if(pageIndex == null || pageSize == null) {
            throw new RuntimeException("請求中分頁頁碼和每頁數據條數為空");
        }
        if(pageSize == 0) {
            throw new RuntimeException("每頁數據條數為不能為0");
        }

        Integer row_total = 0;
        Integer page_total = 1;

        // 管理員查詢賬號審核任務(分頁)
        List<USER_INFO_bean> list = mapper.admin_query_user_au_list_pagi(param);
        if(!list.isEmpty()) {
            USER_INFO_bean bean = list.get(0);
            row_total = bean.getRow_total();
            page_total = row_total % pageSize == 0 ? row_total / pageSize : (row_total / pageSize) + 1;
        }

        // 查詢接單方的加工範圍list
        for(int i = 0; i < list.size(); i++) {
            USER_INFO_bean bean = list.get(i);
            if("recv".equals(bean.getSend_recv_type())) {
                String recv_mnufc_range_rel_id = bean.getRecv_mnufc_range_rel_id();
                if(recv_mnufc_range_rel_id != null && !"".equals(recv_mnufc_range_rel_id)) {
                    List<RECV_MNUFC_RANGE_bean> recv_range_list = userMapper.query_recv_range_list(recv_mnufc_range_rel_id);
                    bean.setRecv_range_list(recv_range_list);
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("row_total", row_total);
        map.put("page_total", page_total);
        map.put("list", list);

        return new ResultParam("1", "管理員查詢賬號審核任務(分頁)成功", map);
    }

    // 【02】管理員賬號審核（1:通過，2:駁回）
    @Override
    @Transactional
    public ResultParam user_au(USER_INFO_bean param, HttpServletRequest request) {
        String au_type = param.getAu_type();
        if(au_type == null || "".equals(au_type)) {
            throw new RuntimeException("審核類型（1:通過，2:駁回）不能為空");
        }

        String pkid = param.getPkid();
        if(pkid == null || "".equals(pkid)) {
            throw new RuntimeException("需審核的賬號pkid不能為空");
        }
        // 根據pkid查詢臨時用戶
        USER_INFO_bean bean = userMapper.query_user_temp_by_pkid(pkid);
        if(bean == null) {
            throw new RuntimeException("需審核的賬號pkid錯誤，無法查到相應的賬號");
        }

        if("1".equals(au_type)) {
            param.setAu_status("1");
            // 將該賬號的狀態修改為1(通過)
            Integer f_update_user_temp_au_status = userMapper.update_user_temp_au_status(param);
            if(f_update_user_temp_au_status <= 0) {
                throw new RuntimeException("將該賬號的狀態修改為1(通過)失敗");
            }

            // 新增用戶信息至用戶表
            Integer f_add_user_info = userMapper.add_user_info(bean);
            if(f_add_user_info <= 0) {
                throw new RuntimeException("新增用戶信息至用戶表失敗");
            }
        } else {
            param.setAu_status("2");
            String rej_msg = param.getRej_msg();
            if(rej_msg != null && !"".equals(rej_msg)) {
                bean.setRej_msg(rej_msg);
            }

            // 將該賬號的狀態修改為2(駁回)
            Integer f_update_user_temp_au_status = userMapper.update_user_temp_au_status(param);
            if(f_update_user_temp_au_status <= 0) {
                throw new RuntimeException("將該賬號的狀態修改為2(駁回)失敗");
            }
        }

        // 賬號審核後發送郵件給該賬號的郵箱
        ResultParam f_user_au_send_email = emailService.user_au_send_email(bean, au_type);
        if(!"1".equals(f_user_au_send_email.getCode())) {
            throw new RuntimeException("賬號審核後發送郵件給該賬號的郵箱失敗");
        }

        return new ResultParam("1", "管理員審核通過", null);
    }

    // 【03】保存公告
    @Transactional
    @Override
    public ResultParam saveBulletin(List<BulletinDO> paramList, HttpServletRequest request) {
        // 判斷權限
        String loginUserPkid = UserUtil.getLoginUserPkid(request);
        USER_INFO_bean loginUser = userMapper.findUserById(loginUserPkid);
        String loginUsername = loginUser.getUsername();
        if (!"admin".equals(loginUsername)) {
            throw new RuntimeException("當前賬號不是管理員賬號，無權限操作");
        }

        // 保存前先刪除
        // 刪除公告
        mapper.deleteBulletin();

        // 保存公告
        Integer count = 1;
        for (int i = 0; i < paramList.size(); i++) {
            BulletinDO bulletin = paramList.get(i);
            String list_content = bulletin.getList_content();
            if (list_content == null || "".equals(list_content)) {
                continue;
            }
            bulletin.setList_order(count);
            Integer saveFlag = mapper.saveBulletin(bulletin);
            if (saveFlag <= 0) {
                throw new RuntimeException("保存公告失敗");
            }
            count++;
        }

        return new ResultParam("1", "【03】保存公告成功", null);
    }

    // 【04】查詢公告list
    @Override
    public ResultParam listBulletin(RequestParam param, HttpServletRequest request) {
        // 查詢公告list
        List<BulletinDO> list = mapper.listBulletin();

        return new ResultParam("1", "【04】查詢公告list成功", list);
    }
}
