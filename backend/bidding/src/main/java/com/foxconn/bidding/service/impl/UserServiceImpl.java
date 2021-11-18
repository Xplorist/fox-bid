package com.foxconn.bidding.service.impl;

import com.foxconn.bidding.mapper.BillMapper;
import com.foxconn.bidding.mapper.UserMapper;
import com.foxconn.bidding.model.*;
import com.foxconn.bidding.service.EmailService;
import com.foxconn.bidding.service.UserService;
import com.foxconn.bidding.util.SimpleEncodeUtil;
import com.foxconn.bidding.util.TokenUtil;
import com.foxconn.bidding.util.UUID_Util;
import com.foxconn.bidding.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private EmailService emailService;

    @Override
    public ResultParam ck_user_is_exist(USER_INFO_bean param, HttpServletRequest request) {
        Integer query_user_is_exist_flag = mapper.query_user_is_exist(param.getUsername());
        if (query_user_is_exist_flag > 0) {
            return new ResultParam("0", "此賬號名稱已存在，無法新增", null);
        }

        return new ResultParam("1", "此賬號名稱可新增", null);
    }

    // 提交註冊
    @Override
    @Transactional
    public ResultParam register(USER_INFO_bean param, HttpServletRequest request) {
        String send_recv_type = param.getSend_recv_type();
        if(send_recv_type == null || "".equals(send_recv_type)) {
            throw new RuntimeException("發單方接單方類型不能為空");
        }
        // 保存用戶頭像文件
        String user_pic_file_pkid = "";
        USER_PIC_FILE_bean user_pic_file = param.getUser_pic_file();
        if(user_pic_file != null && user_pic_file.getFile_save_name() != null) {
            user_pic_file_pkid = UUID_Util.getUUID32();
            user_pic_file.setPkid(user_pic_file_pkid);

            Integer add_USER_PIC_FILE_flag = mapper.add_USER_PIC_FILE(user_pic_file);
            if(add_USER_PIC_FILE_flag <= 0) {
                throw new RuntimeException("新增用戶頭像文件信息失敗");
            }
        }
        // 保存接單方加工範圍
        List<RECV_MNUFC_RANGE_bean> recv_range_list = param.getRecv_range_list();
        if ("recv".equals(send_recv_type) && recv_range_list != null && !recv_range_list.isEmpty()) {
            String recv_mnufc_range_rel_id = UUID_Util.getUUID32();
            param.setRecv_mnufc_range_rel_id(recv_mnufc_range_rel_id);
            for(int i = 0; i < recv_range_list.size(); i++) {
                RECV_MNUFC_RANGE_bean range_bean = recv_range_list.get(i);
                range_bean.setRecv_mnufc_range_rel_id(recv_mnufc_range_rel_id);
                range_bean.setList_order(i + 1);
                Integer f_add_recv_mnufc_range = mapper.add_recv_mnufc_range(range_bean);
                if(f_add_recv_mnufc_range <= 0) {
                    throw new RuntimeException("保存加工範圍失敗");
                }
            }
        }

        param.setPassword(SimpleEncodeUtil.encode(param.getPassword()));
        param.setUser_pic_file_pkid(user_pic_file_pkid);
        Integer add_user_temp_flag = mapper.add_USER_TEMP(param);
        if(add_user_temp_flag <= 0) {
            throw new RuntimeException("新增用戶信息失敗");
        }

        // 提交註冊，發送郵件給管理員
        ResultParam f_send_email = emailService.register_send_email_to_admin(param);
        if(!"1".equals(f_send_email.getCode())) {
            throw new RuntimeException("給管理員發送賬號審核郵件失敗");
        }

        return new ResultParam("1", "提交註冊成功", null);
    }

    @Override
    public ResultParam login(USER_INFO_bean param, HttpServletRequest request) {
        USER_INFO_bean user = mapper.query_user_by_username(param.getUsername());
        String encodedPSW = SimpleEncodeUtil.encode(param.getPassword());
        String userPkid = user.getPkid();

        if(user == null) {
            //return ResultParam.of("0", "用戶不存在");
            return ResultParam.of("0", "用戶或密碼錯誤");
        }
        if(!encodedPSW.equals(user.getPassword())) {
            //return ResultParam.of("0", "密碼錯誤");
            return ResultParam.of("0", "用戶或密碼錯誤");
        }
        String token = TokenUtil.getToken(user.getPkid(), param.getPassword());// token必須用前端傳遞的param中傳遞的password

        // 更新賬號最近登錄時間
        Integer updateFlag = mapper.updateUserLatestLoginTime(userPkid);
        if (updateFlag <= 0) {
            throw new RuntimeException("更新賬號最近登錄時間失敗");
        }

        return new ResultParam("1", "登錄成功", token);
    }

    @Override
    public USER_INFO_bean findUserById(String id) {
        return mapper.findUserById(id);
    }

    @Override
    public ResultParam login_user_info(USER_INFO_bean param, HttpServletRequest request) {
        USER_INFO_bean user_info_bean = mapper.query_user_by_username(param.getUsername());

        if(user_info_bean == null) {
            return new ResultParam("0", "查詢用戶信息失敗", null);
        } else {
            String user_pic_file_pkid = user_info_bean.getUser_pic_file_pkid();
            if(user_pic_file_pkid != null) {
                USER_PIC_FILE_bean user_pic_file_bean = mapper.query_user_pic_file(user_pic_file_pkid);
                user_info_bean.setUser_pic_file(user_pic_file_bean);
            } else {
                USER_PIC_FILE_bean user_pic_file_bean = mapper.query_user_pic_file("default");
                user_info_bean.setUser_pic_file(user_pic_file_bean);
            }

            String recv_mnufc_range_rel_id = user_info_bean.getRecv_mnufc_range_rel_id();
            if(recv_mnufc_range_rel_id != null && !"".equals(recv_mnufc_range_rel_id)) {
                List<RECV_MNUFC_RANGE_bean> recv_range_list = mapper.query_recv_range_list(recv_mnufc_range_rel_id);
                user_info_bean.setRecv_range_list(recv_range_list);
            }

            return new ResultParam("1", "查詢用戶信息成功", user_info_bean);
        }
    }

    // 【05】根據用戶id查詢用戶信息
    /**
     * 根據用戶id查詢用戶信息
     * @param param
     * USER_INFO_bean param 屬性list:
     * String pkid;//用戶信息表主鍵id
     * @param request
     * @return
     * ResultParam 屬性list:
     * private String code;// 返回參數代碼
     * private String msg;// 返回參數信息
     * private T t;// 返回參數泛型對象
     * t: USER_INFO_bean
     *
     */
    @Override
    public ResultParam query_user_info_by_pkid(USER_INFO_bean param, HttpServletRequest request) {
        if (param == null) {
            throw new RuntimeException("參數不能為空");
        }
        String pkid = param.getPkid();
        if (pkid == null || "".equals(pkid)) {
            throw new RuntimeException("用戶pkid不能為空");
        }
        // 通過id查找用戶
        USER_INFO_bean user_info_bean = mapper.findUserById(pkid);
        if (user_info_bean == null) {
            throw new RuntimeException("根據用戶id查詢用戶信息失敗");
        }

        // 查詢用戶頭像
        String user_pic_file_pkid = user_info_bean.getUser_pic_file_pkid();
        if (user_pic_file_pkid != null && !"".equals(user_pic_file_pkid)) {
            USER_PIC_FILE_bean user_pic_file_bean = mapper.query_user_pic_file(user_pic_file_pkid);
            user_info_bean.setUser_pic_file(user_pic_file_bean);
        } else {
            USER_PIC_FILE_bean user_pic_file_bean = mapper.query_user_pic_file("default");
            user_info_bean.setUser_pic_file(user_pic_file_bean);
        }
        // 查詢接單方的加工範圍list
        String recv_mnufc_range_rel_id = user_info_bean.getRecv_mnufc_range_rel_id();
        if (recv_mnufc_range_rel_id != null && !"".equals(recv_mnufc_range_rel_id)) {
            List<RECV_MNUFC_RANGE_bean> recv_range_list = mapper.query_recv_range_list(recv_mnufc_range_rel_id);
            user_info_bean.setRecv_range_list(recv_range_list);
        }

        String send_recv_type = user_info_bean.getSend_recv_type();
        if ("send".equals(send_recv_type)) {
            // 查詢發單方收到的評價list
            List<RECV_EVAL_bean> send_get_eval_list = billMapper.query_send_get_eval_list_nopagi(pkid);
            user_info_bean.setGet_eval_list(send_get_eval_list);
            for(int i = 0; i < send_get_eval_list.size(); i++) {
                RECV_EVAL_bean recv_eval_bean = send_get_eval_list.get(i);
                String recv_user_pkid = recv_eval_bean.getRecv_user_pkid();
                USER_INFO_bean user = mapper.findUserById(recv_user_pkid);
                recv_eval_bean.setUser(user);
            }

            /*--查詢發單方的統計數據--*/
            // 統計發單方發單量和發單總金額
            SendUserStatistics sendUserStatistics = mapper.queryUserSendBillAmountAndMoney(pkid);
            Integer send_amount = sendUserStatistics.getSend_amount();
            // 統計發單方選標金額
            Long pick_money = mapper.queryUserPickMoney(pkid);
            sendUserStatistics.setPick_money(pick_money);
            // 統計發單方棄標量
            Integer abandon_amount = mapper.queryUserAbandonBidAmount(pkid);
            Float abandon_rate = 0f;
            if (send_amount != 0) {
                abandon_rate = (float)abandon_amount / send_amount;
                abandon_rate = (float)Math.round(abandon_rate * 10000) / 10000;
            }
            sendUserStatistics.setAbandon_rate(abandon_rate);
            // 統計發單方流標量
            Integer flow_amount = mapper.queryUserFlowBidAmount(pkid);
            Float flow_rate = 0f;
            if (send_amount != 0) {
                flow_rate = (float)flow_amount / send_amount;
                flow_rate = (float)Math.round(flow_rate * 10000) / 10000;
            }
            sendUserStatistics.setFlow_rate(flow_rate);
            // 統計發單方選標量
            Integer pick_amount = mapper.queryUserPickBidAmount(pkid);
            Float pick_rate = 0f;
            if (send_amount != 0) {
                pick_rate = (float)pick_amount / send_amount;
                pick_rate = (float)Math.round(pick_rate * 10000) /  10000;
            }
            sendUserStatistics.setPick_rate(pick_rate);
            // 統計發單方的客戶評分
            Float user_score = mapper.querySendUserGetScore(pkid);
            sendUserStatistics.setUser_score(user_score);
            // 查詢發單方最近發單時間
            String latest_send_bill_time = mapper.querySendUserLatestSendBillTime(pkid);
            sendUserStatistics.setLatest_send_bill_time(latest_send_bill_time);
            // 查詢最近登錄時間
            sendUserStatistics.setLatest_login_time(user_info_bean.getLatest_login_time());
            // 查詢發單方信用（發單方信用 = 中標量 - 棄標量 * （1 + 棄標量 / （棄標量 + 中標量）））
            float reputation = 0f;
            if ((abandon_amount + pick_amount) != 0) {
                reputation = pick_amount - abandon_amount * (1 + (float)abandon_amount / (abandon_amount + pick_amount));
                reputation = (float)Math.round(reputation * 100) / 100;
            }
            sendUserStatistics.setReputation(reputation);
            user_info_bean.setSend_user_statistics(sendUserStatistics);
        } else if ("recv".equals(send_recv_type)) {
            // 查詢接單方收到的評價list
            List<SEND_EVAL_bean> recv_get_eval_list = billMapper.query_recv_get_eval_list_nopagi(pkid);
            user_info_bean.setGet_eval_list(recv_get_eval_list);
            for(int i = 0; i < recv_get_eval_list.size(); i++) {
                SEND_EVAL_bean send_eval_bean = recv_get_eval_list.get(i);
                String send_user_pkid = send_eval_bean.getSend_user_pkid();
                USER_INFO_bean user = mapper.findUserById(send_user_pkid);
                send_eval_bean.setUser(user);
            }

            /*--查詢接單方的統計數據--*/
            // 接單方統計接單量和報價金額
            RecvUserStatistics recvUserStatistics = mapper.queryRecvUserOfferAmountAndMoney(pkid);
            Integer recv_amount = recvUserStatistics.getRecv_amount();
            // 統計接單方中標金額
            Long win_bid_money = mapper.queryRecvUserWinBidMoney(pkid);
            recvUserStatistics.setWin_bid_money(win_bid_money);
            // 統計接單方競標成功量
            Integer win_bid_amount = mapper.queryRecvUserWinBidAmount(pkid);
            Float win_bid_rate = 0f;
            if (recv_amount != 0) {
                win_bid_rate = (float)win_bid_amount / recv_amount;
                win_bid_rate = (float)Math.round(win_bid_rate * 10000) /  10000;
            }
            recvUserStatistics.setWin_bid_rate(win_bid_rate);
            // 統計接單方的客戶評分
            Float user_score = mapper.queryRecvUserGetScore(pkid);
            recvUserStatistics.setUser_score(user_score);
            // 查詢接單方最近接單時間
            String latest_recv_bill_time = mapper.queryRecvUserLatestOfferTime(pkid);
            recvUserStatistics.setLatest_recv_bill_time(latest_recv_bill_time);
            // 查詢最近登錄時間
            recvUserStatistics.setLatest_login_time(user_info_bean.getLatest_login_time());
            user_info_bean.setRecv_user_statistics(recvUserStatistics);
        }

        return new ResultParam("1", "根據用戶id查詢用戶信息成功", user_info_bean);
    }

    @Override
    @Transactional
    public ResultParam update_user_info(USER_INFO_bean param, HttpServletRequest request) {
        String pkid = param.getPkid();
        USER_PIC_FILE_bean user_pic_file = param.getUser_pic_file();
        USER_INFO_bean user_info_bean = mapper.findUserById(pkid);
        String user_pic_file_pkid = user_info_bean.getUser_pic_file_pkid();
        param.setUser_pic_file_pkid(user_pic_file_pkid);
        // 修改了用戶頭像文件，先刪除舊的文件信息，再保存新的文件信息
        if(user_pic_file != null && user_pic_file.getFile_save_name() != null && !"".equals(user_pic_file.getFile_save_name())) {
            if(user_pic_file_pkid == null || "".equals(user_info_bean)) {
                user_pic_file_pkid = UUID_Util.getUUID32();
            }
            param.setUser_pic_file_pkid(user_pic_file_pkid);
            Integer f_delete_user_pic_file = mapper.delete_user_pic_file(user_pic_file_pkid);
            /*if(f_delete_user_pic_file <= 0) {
                throw new RuntimeException("刪除用戶頭像文件信息失敗");
            }*/
            user_pic_file.setPkid(user_pic_file_pkid);
            Integer f_add_user_pic_file = mapper.add_USER_PIC_FILE(user_pic_file);
            if(f_add_user_pic_file <= 0) {
                throw new RuntimeException("保存用戶頭像文件信息失敗");
            }
        }
        // 修改接單方加工範圍list,先刪除舊的接單方加工範圍，在保存新的接單方加工範圍
        List<RECV_MNUFC_RANGE_bean> recv_range_list = param.getRecv_range_list();
        String send_recv_type = param.getSend_recv_type();
        if(send_recv_type == null || "".equals(send_recv_type)) {
            throw new RuntimeException("發單接單類型不能為空");
        }
        if("recv".equals(send_recv_type) && !recv_range_list.isEmpty()) {
            String recv_mnufc_range_rel_id = user_info_bean.getRecv_mnufc_range_rel_id();
            Integer f_delete_recv_range_list = mapper.delete_recv_range_list(recv_mnufc_range_rel_id);
            if(f_delete_recv_range_list <= 0) {
                throw new RuntimeException("刪除接單方加工範圍失敗");
            }
            for (int i = 0; i < recv_range_list.size(); i++) {
                RECV_MNUFC_RANGE_bean range_bean = recv_range_list.get(i);
                range_bean.setRecv_mnufc_range_rel_id(recv_mnufc_range_rel_id);
                range_bean.setList_order(i + 1);
                Integer f_add_recv_mnufc_range = mapper.add_recv_mnufc_range(range_bean);
                if (f_add_recv_mnufc_range <= 0) {
                    throw new RuntimeException("新增接單方加工範圍失敗");
                }
            }
        }

        String dept_code = param.getDept_code();
        if(dept_code == null || "".equals(dept_code)) {
            throw new RuntimeException("更改用戶信息失败，用户单位代码为空");
        }
        // 更新用戶主表信息
        Integer f_update_user_info = mapper.update_user_info(param);
        if(f_update_user_info <= 0) {
            throw new RuntimeException("更新用戶主表信息失敗");
        }

        return new ResultParam("1", "更改用戶信息成功", null);
    }

    // 【07】忘記密碼，生成驗證碼
    @Override
    @Transactional
    public ResultParam generate_code(USER_INFO_bean param, HttpServletRequest request) {
        String username = param.getUsername();
        // 查詢此用戶是否存在
        USER_INFO_bean user_bean = mapper.query_user_by_username(username);
        if(user_bean == null) {
            throw new RuntimeException("此用戶不存在");
        }

        String email = user_bean.getEmail();
        if(email == null || "".equals(email)) {
            throw new RuntimeException( "用戶【" + username + "】的郵箱地址為空");
        }

        // 後台中生成隨機數
        Integer random_code = new Random().nextInt(888889) + 111111;
        String user_pkid = user_bean.getPkid();
        VERIFICATION_CODE_bean code_bean = new VERIFICATION_CODE_bean();
        code_bean.setCode(random_code.toString());
        code_bean.setUser_pkid(user_pkid);
        // 將此賬號的驗證碼狀態更新為非最新狀態
        mapper.update_user_verification_code_not_newest(user_pkid);
        // 將隨機數和用戶pkid保存到驗證碼表中
        Integer f_add_verification_code = mapper.add_verification_code(code_bean);
        if(f_add_verification_code <= 0) {
            throw new RuntimeException("新增驗證碼和用戶pkid到驗證碼表中失敗");
        }
        // 發送驗證碼郵件
        ResultParam code_result = emailService.send_verification_code_mail(user_bean, code_bean);
        if(!"1".equals(code_result.getCode())) {
            throw new RuntimeException("發送驗證碼郵件失敗");
        }

        return new ResultParam("1", "生成驗證碼成功,並已成功發送至郵箱", email);
    }

    // 【08】忘記密碼，檢查驗證碼是否正確
    @Override
    @Transactional
    public ResultParam check_code(VERIFICATION_CODE_bean param, HttpServletRequest request) {
        String code_fr_front = param.getCode();
        if(code_fr_front == null || "".equals(code_fr_front)) {
            throw new RuntimeException("未輸入驗證碼");
        }
        String username = param.getUsername();
        USER_INFO_bean user_bean = mapper.query_user_by_username(username);
        if(user_bean == null) {
            throw new RuntimeException("用戶【" + username + "】不存在");
        }
        // 根據用戶id查詢最新的驗證碼
        String user_pkid = user_bean.getPkid();
        param.setUser_pkid(user_pkid);
        String code_in_db = mapper.query_newest_code_by_user_pkid(user_pkid);
        if(code_in_db == null) {
            throw new RuntimeException("此用戶未生成最新的驗證碼");
        }
        if(!code_fr_front.equals(code_in_db)) {
            throw new RuntimeException("輸入的驗證碼有誤，請輸入最新生成的驗證碼");
        }

        return new ResultParam("1", "輸入的驗證碼正確", null);
    }

    /*
      check_code_before_update和check_code的區別在於：
      check_code_before_update在檢查驗證碼為最新後將最新的驗證碼設置為非最新狀態
    */
    @Transactional
    public ResultParam check_code_before_update(VERIFICATION_CODE_bean param, HttpServletRequest request) {
        String code_fr_front = param.getCode();
        if(code_fr_front == null || "".equals(code_fr_front)) {
            throw new RuntimeException("未輸入驗證碼");
        }
        String username = param.getUsername();
        USER_INFO_bean user_bean = mapper.query_user_by_username(username);
        if(user_bean == null) {
            throw new RuntimeException("用戶【" + username + "】不存在");
        }
        // 根據用戶id查詢最新的驗證碼
        String user_pkid = user_bean.getPkid();
        param.setUser_pkid(user_pkid);
        String code_in_db = mapper.query_newest_code_by_user_pkid(user_pkid);
        if(code_in_db == null) {
            throw new RuntimeException("此用戶未生成最新的驗證碼");
        }
        if(!code_fr_front.equals(code_in_db)) {
            throw new RuntimeException("輸入的驗證碼有誤，請輸入最新生成的驗證碼");
        }
        // 更新此驗證碼不為最新
        Integer f_update_code_not_newest = mapper.update_code_not_newest(param);
        if(f_update_code_not_newest <= 0) {
            throw new RuntimeException("更新此驗證碼不為最新失敗");
        }

        return new ResultParam("1", "輸入的驗證碼正確", null);
    }

    // 【09】忘記密碼，提交更新密碼
    @Override
    @Transactional
    public ResultParam update_password(VERIFICATION_CODE_bean param, HttpServletRequest request) {
        ResultParam f_check_code = check_code_before_update(param, request);
        if(!"1".equals(f_check_code.getCode())) {
            throw new RuntimeException("驗證碼有誤，無法更新密碼");
        }
        String password = param.getPassword();
        password = SimpleEncodeUtil.encode(password);
        param.setPassword(password);
        // 更新用戶密碼
        Integer f_update_password = mapper.update_password(param);
        if(f_update_password <= 0) {
            throw new RuntimeException("提交更新密碼失敗");
        }

        return new ResultParam("1", "提交更新密碼成功", null);
    }

    // 【10】條件分頁查詢用戶list
    /*
     請求參數：USER_INFO_bean param
    【非空】private String send_recv_type;//發單接單類別(send：發單方，recv：接單方)
    【非空】private String fctry_zone;//所屬廠區
    【非空】private String secn_cmpy;//所屬次集團
    【非空】private String entrps_group;//所屬事業群
    【非空】private String pd_office;//所屬產品處
    【非空】private Integer pageIndex;// 分頁頁碼
    【非空】private Integer pageSize;// 每頁數據條數

     返回結果：Map<String, Object> map
      key: "row_total", value:// 總行數
      key: "page_total", value:// 總頁數
      key: "list", value:// 用戶list
     */
    @Override
    public ResultParam queryUserList(USER_INFO_bean param, HttpServletRequest request) {
        // 權限判斷
        String user_pkid = (String) request.getAttribute("user_pkid");
        USER_INFO_bean user = mapper.findUserById(user_pkid);
        String username = user.getUsername();
        if (!"admin".equals(username)) {
            throw new RuntimeException("當前用戶非管理員，無權限查詢");
        }

        // 請求參數非空判斷
        if (param == null) {
            throw new RuntimeException("參數不能為空");
        }
        String send_recv_type = param.getSend_recv_type();
        String fctry_zone = param.getFctry_zone();
        String secn_cmpy = param.getSecn_cmpy();
        String entrps_group = param.getEntrps_group();
        String pd_office = param.getPd_office();
        Integer pageIndex = param.getPageIndex();
        Integer pageSize = param.getPageSize();
        if(send_recv_type == null || "".equals(send_recv_type)
        || fctry_zone == null || "".equals(fctry_zone)
        || secn_cmpy == null || "".equals(secn_cmpy)
        || entrps_group == null || "".equals(entrps_group)
        || pd_office == null || "".equals(pd_office)
        || pageIndex == null || pageSize == null) {
            throw new RuntimeException("參數不能為空");
        }

        if ("發單方".equals(send_recv_type)) {
            send_recv_type = "send";
        } else if ("接單方".equals(send_recv_type)) {
            send_recv_type = "recv";
        } else {
            send_recv_type = "全部";
        }
        param.setSend_recv_type(send_recv_type);

        Integer row_total = 0;// 總行數
        Integer page_total = 1;// 總頁數
       // 條件分頁查詢用戶list
        List<USER_INFO_bean> list = mapper.queryUserList(param);
        if (!list.isEmpty()) {
            USER_INFO_bean bean = list.get(0);
            row_total = bean.getRow_total();
            page_total = row_total % pageSize == 0 ? row_total / pageSize : (row_total / pageSize) + 1;
        }

        // 判斷用戶是否活躍
        for (int i = 0; i < list.size(); i++) {
            USER_INFO_bean bean = list.get(i);
            String bean_pkid = bean.getPkid();
            String user_type = bean.getSend_recv_type();
            if ("send".equals(user_type)) {
                // 判斷發單用戶是否活躍 (count > 0 代表活躍)
                Integer activeFlag = mapper.querySendUserIsActive(bean_pkid);
                if (activeFlag > 0) {
                    bean.setF_active("Y");
                } else {
                    bean.setF_active("N");
                }
            } else if ("recv".equals(user_type)) {
                // 判斷接單用戶是否活躍(count > 0 代表活躍)
                Integer activeFlag = mapper.queryRecvUserIsActive(bean_pkid);
                if (activeFlag > 0) {
                    bean.setF_active("Y");
                } else {
                    bean.setF_active("N");
                }
            }
        }

        // 返回結果聲明
        Map<String, Object> map = new HashMap<>();
        map.put("row_total", row_total);
        map.put("page_total", page_total);
        map.put("list", list);

        return new ResultParam("1", "【10】條件分頁查詢用戶list成功", map);
    }

    // 【11】刪除賬號
    @Transactional
    @Override
    public ResultParam deleteUser(RequestParam param, HttpServletRequest request) {
        // 判斷用戶權限
        String loginUserPkid = UserUtil.getLoginUserPkid(request);
        USER_INFO_bean loginUser = mapper.findUserById(loginUserPkid);
        String loginUsername = loginUser.getUsername();
        if (!"admin".equals(loginUsername)) {
            throw new RuntimeException("當前用戶非管理員，無權限刪除賬號");
        }

        // 判斷參數非空
        if (param == null) {
            throw new RuntimeException("JSON參數不能為空");
        }
        String user_pkid = param.getUser_pkid();
        if (user_pkid == null || "".equals(user_pkid)) {
            throw new RuntimeException("需要刪除的用戶pkid不能為空");
        }
        // 邏輯刪除用戶賬號
        Integer updateFlag = mapper.updateUserInvalid(user_pkid);
        if (updateFlag <= 0) {
            throw new RuntimeException("邏輯刪除用戶賬號失敗");
        }

        return new ResultParam("1", "【11】刪除賬號成功", null);
    }

    // 【12】統計所有賬號的相關信息
    @Override
    public ResultParam queryAllUsersStatistics(RequestParam param, HttpServletRequest request) {
        // 判斷用戶權限
        /*String loginUserPkid = UserUtil.getLoginUserPkid(request);
        USER_INFO_bean loginUser = mapper.findUserById(loginUserPkid);
        String loginUsername = loginUser.getUsername();
        if (!"admin".equals(loginUsername)) {
            throw new RuntimeException("當前用戶非管理員，無權限");
        }*/

        // 統計註冊賬號總數
        Integer total_num = mapper.queryUserTotalNum();
        // 統計發單方賬號總數
        Integer send_num = mapper.querySendUserNum();
        // 統計接單方賬號總數
        Integer recv_num = mapper.queryRecvUserNum();
        // 統計活躍發單方賬號個數
        Integer active_send = mapper.queryActiveSendUserNum();
        // 統計活躍的接單方用戶個數
        Integer active_recv = mapper.queryActiveRecvUserNum();
        // 統計待審核賬號總數
        Integer wait_num = mapper.queryWaitAuUserNum();

        int active_num = active_send + active_recv;
        int inactive_num = total_num - active_num;

        Map<String, Object> map = new HashMap<>();
        map.put("total_num", total_num);// 註冊賬號總數
        map.put("send_num", send_num);// 發單方賬號總數
        map.put("recv_num", recv_num);// 接單方賬號總數
        map.put("active_num", active_num);// 活躍賬號總數
        map.put("inactive_num", inactive_num);// 不活躍賬號總數
        map.put("wait_num", wait_num);// 待審核賬號總數

        return new ResultParam("1", "【12】統計所有賬號的相關信息成功", map);
    }
}
