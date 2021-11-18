package com.foxconn.bidding.model;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
public class ResultParam<T> {
    @NonNull
    private String code;// 結果代碼
    @NonNull
    private String msg;// 結果信息
    private T t;// 結果數據
}
