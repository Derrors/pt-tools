/**
 * ***************************************************** Copyright (C) 2023 bytedance.com. All Rights Reserved This file
 * is part of bytedance EA project. Unauthorized copy of this file, via any medium is strictly prohibited. Proprietary
 * and Confidential. ****************************************************
 **/
package com.derrors.pt.tools.common.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Controller 返回值包装
 *
 * @author derrors
 * @date 2023/12/24
 */
@Getter
@Setter
@ToString
public class Result<T> {
    public static final int OK = 200;
    // 跳转系统内部错误页面
    public static final int ERROR = 500;
    // 告知无权限或者跳转无权限页面
    public static final int NOT_AUTHORIZED = 403;
    // 需要重新登陆
    public static final int NOT_AUTHENTICATED = 401;
    private static final String MESSAGE = "OK";

    public static final Result SUCCESS_RESULT = new Result();

    private int code;
    private String msg;
    private T data;

    public Result() {
        code = OK;
        msg = MESSAGE;
    }

    public static Result nonLogin(String msg) {
        Result result = new Result();
        result.code = NOT_AUTHENTICATED;
        result.msg = msg;
        return result;
    }

    public static Result internalError(String msg) {
        Result result = new Result();
        result.code = ERROR;
        result.msg = msg;
        return result;
    }

    public static Result fail(int code, String message) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    public static <T> Result<T> internalErrorWithData(T data) {
        Result<T> result = new Result<>();
        result.setCode(ERROR);
        result.setData(data);
        return result;
    }

    public static Result noAuthorization(String msg) {
        Result result = new Result();
        result.code = NOT_AUTHORIZED;
        result.msg = msg;
        return result;
    }

    public static Result success() {
        return SUCCESS_RESULT;
    }

    public static <T> Result<T> okWithData(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }
}

