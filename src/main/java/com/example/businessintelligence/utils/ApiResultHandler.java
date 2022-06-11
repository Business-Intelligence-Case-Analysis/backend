package com.example.businessintelligence.utils;

import com.example.businessintelligence.entity.logicalEntity.ApiResult;

public class ApiResultHandler {
    public static ApiResult success(){
        return success(null);
    }

    public static ApiResult success(Object object) {return new ApiResult(200,"请求成功",object);}

    public static ApiResult fail(){
        return new ApiResult(-1,"请求失败",null);
    }

    public static ApiResult empty() {return new ApiResult(204,"空对象",null);}

    public static ApiResult buildApiResult(Integer code, String message, Object value) {
        ApiResult apiResult = new ApiResult();

        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setValue(value);
        return apiResult;
    }
}