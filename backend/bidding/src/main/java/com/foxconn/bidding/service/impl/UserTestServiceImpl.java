package com.foxconn.bidding.service.impl;

import com.foxconn.bidding.mapper.UserTestMapper;
import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.model.USER_INFO_bean;
import com.foxconn.bidding.service.UserTestService;
import com.foxconn.bidding.util.SimpleEncodeUtil;
import com.foxconn.bidding.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTestServiceImpl implements UserTestService {
    @Autowired
    private UserTestMapper mapper;

    @Override
    public USER_INFO_bean findUserById(String id) {
        return mapper.findUserById(id);
    }

    @Override
    public ResultParam login(USER_INFO_bean param) {
        USER_INFO_bean user = mapper.findUserByUsername(param.getUsername());
        String encodedPSW = SimpleEncodeUtil.encode(param.getPassword());

        if(user == null) {
            return ResultParam.of("01", "用戶不存在");
        }
        if(!encodedPSW.equals(user.getPassword())) {
            return ResultParam.of("02", "密碼錯誤");
        }
        String token = TokenUtil.getToken(user.getPkid(), param.getPassword());

        return new ResultParam("1", "登錄成功", token);
    }

    @Override
    public ResultParam getUser(String username) {
        USER_INFO_bean user = mapper.findUserByUsername(username);
        if(user == null) {
            throw new RuntimeException("findUserByUsername is failed !");
        }

        return new ResultParam("1", "查詢成功", user);
    }

    @Override
    @Transactional
    public ResultParam addUser(USER_INFO_bean param) {
        ResultParam result = ResultParam.of("1", "addUser is successful !");

        String password = param.getPassword();
        String encodePSW = SimpleEncodeUtil.encode(password);
        param.setPassword(encodePSW);

       /*
        String user_info_pkid = UUID_Util.getUUID32();
        USER_INFO_bean user_info = param.getUser_info();
        user_info.setPkid(user_info_pkid);
        */

        // 新增用戶信息
        Integer addUserInfo_flag = mapper.addUserInfo(param);
        if(addUserInfo_flag <= 0) {
            result = ResultParam.of("0", "addUserInfo is failed !");
            throw new RuntimeException("addUserInfo is failed !");
        }

        return result;
    }
}
