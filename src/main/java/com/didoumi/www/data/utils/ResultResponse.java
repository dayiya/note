package com.didoumi.www.data.utils;

import lombok.Data;

@Data
public class ResultResponse {
    private String code;
    private String errMsg;
    private Object data;
    public ResultResponse() {
        this.code = "200";
    }
}
