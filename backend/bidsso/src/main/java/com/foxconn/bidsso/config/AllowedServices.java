package com.foxconn.bidsso.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "allowed")
@PropertySource(value = "classpath:configuration/allowedServices.properties")
public class AllowedServices {
    private List<String> services;
}
