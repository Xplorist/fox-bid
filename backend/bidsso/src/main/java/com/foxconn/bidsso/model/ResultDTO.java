package com.foxconn.bidsso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class ResultDTO<T> {
    @NonNull
    private String code;
    @NonNull
    private String msg;
    private T t;
}
