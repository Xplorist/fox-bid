package com.foxconn.bidding.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ftp")
@PropertySource("classpath:configuration/ftp.properties")
public class FTP_Properties_Util {
    private String address;
    private Integer port;
    private String username;
    private String password;
}
