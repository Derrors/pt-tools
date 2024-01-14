package com.derrors.pt.tools.common.exception.enums;

import lombok.Getter;

/**
 * 异常枚举类
 *
 * @author derrors
 * @date 2023/12/29
 */
@Getter
public enum ExceptionEnum {
    // 系统内部错误
    SYSTEM_ERROR("000001", "Internal System Error."),


    // 配置场景业务异常
    PT_NODE_PARAM_EMPTY("200001", "The name or code of the pt node can't be empty."),
    PT_NODE_CODE_EMPTY("200002", "The code of the pt node can't be empty."),
    PT_USER_PARAM_EMPTY("200003", "The userId or the ptCode is blank, so can't process ptUser."),
    NOT_SUPPORTED_OPERATION("200010", "The operation is not supported.")
    ;

    private final String code;
    private final String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
