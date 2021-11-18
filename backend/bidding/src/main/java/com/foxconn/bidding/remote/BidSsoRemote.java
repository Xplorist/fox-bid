package com.foxconn.bidding.remote;

import com.foxconn.bidding.model.ResultDTO;
import com.foxconn.bidding.model.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "bidsso")
public interface BidSsoRemote {
    @RequestMapping("/tokenValidate")
    ResultDTO tokenValidate(@RequestBody TokenDTO param);
}
