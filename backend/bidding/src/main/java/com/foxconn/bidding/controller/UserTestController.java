package com.foxconn.bidding.controller;

import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.model.USER_INFO_bean;
import com.foxconn.bidding.service.UserTestService;
import com.foxconn.bidding.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userTest")
public class UserTestController {
    @Autowired
    private UserTestService userTestService;

    // 註冊
    @PostMapping("/register")
    public ResultParam register(@RequestBody USER_INFO_bean param) {
        return userTestService.addUser(param);
    }

    // 登錄
    @PostMapping("/login")
    public ResultParam login(@RequestBody USER_INFO_bean param) {
        return userTestService.login(param);
    }

    @VerifyToken
    @GetMapping("/getUser")
    public ResultParam getUser(String username) {
        return userTestService.getUser(username);
    }
}
