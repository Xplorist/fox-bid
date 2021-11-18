package com.xplorist.bidforum.model.dto;

import com.xplorist.bidforum.model.UserInfoDO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDTO extends UserInfoDO {

    public UserInfoDTO(UserInfoDO obj) {
        super(obj);
    }
}
