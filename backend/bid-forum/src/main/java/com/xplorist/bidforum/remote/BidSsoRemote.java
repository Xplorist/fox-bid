package com.xplorist.bidforum.remote;

import com.xplorist.bidforum.model.dto.ResultDTO;
import com.xplorist.bidforum.model.dto.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bidsso")
public interface BidSsoRemote {
    // 【04】驗證token接口（Ajax接口）[業務系統後端使用httpclient調用此接口]
    @RequestMapping("/tokenValidate")
    ResultDTO tokenValidate(@RequestBody TokenDTO param);

    // 【05】登錄接口（Ajax接口）[業務系統後端使用httpclient調用此接口]
    @RequestMapping("/loginAjax")
    ResultDTO loginAjax(@RequestParam("username") String username, @RequestParam("password") String password);
}
