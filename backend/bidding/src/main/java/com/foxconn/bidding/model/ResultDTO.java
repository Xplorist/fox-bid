package com.foxconn.bidding.model;

import lombok.*;

/**
 * 结果DTO，用于RestController返回数据给前端
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class ResultDTO<T> {
    @NonNull
    private String code;// 结果代码，0：失败，1：成功
    @NonNull
    private String msg;// 结果信息
    private T t;// 泛型类，返回结果数据
}
