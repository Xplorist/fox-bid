package com.foxconn.bidstatistics.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 廠部分析DTO
 */
@Data
public class FactoryAnalysisDTO {
    private String name;// 廠區類型
    private List<PublishTypeDTO> value;// 廠區屬性list
}
