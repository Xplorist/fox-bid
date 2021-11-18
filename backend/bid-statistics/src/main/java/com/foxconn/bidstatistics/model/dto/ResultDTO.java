package com.foxconn.bidstatistics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class ResultDTO<T> {
    @NonNull
    private String code;// 結果代碼
    @NonNull
    private String msg;// 結果信息
    private T t;// 結果數據
}
