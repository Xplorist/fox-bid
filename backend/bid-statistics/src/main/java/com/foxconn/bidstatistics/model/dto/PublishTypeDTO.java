package com.foxconn.bidstatistics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 發佈類型DTO
 */
@Data
public class PublishTypeDTO {
    private String name;// 發佈類型名稱
    private Integer value;// 發佈類型數量
}
