package com.foxconn.bidsso.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 用戶信息DTO
 */
@Data
@RequiredArgsConstructor(staticName = "of")
public class UserInfoDTO {
    @NonNull
    private String userId;
    @NonNull
    private String token;
}
