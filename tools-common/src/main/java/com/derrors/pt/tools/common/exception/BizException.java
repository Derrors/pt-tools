/**
 * ***************************************************** Copyright (C) 2023 bytedance.com. All Rights Reserved This file
 * is part of bytedance EA project. Unauthorized copy of this file, via any medium is strictly prohibited. Proprietary
 * and Confidential. ****************************************************
 **/
package com.derrors.pt.tools.common.exception;

import com.derrors.pt.tools.common.exception.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常类
 *
 * @author derrors
 * @date 2023/12/29
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public BizException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(ExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public BizException(ExceptionEnum exceptionEnum, Exception e) {
        super(e);
        this.errorCode = exceptionEnum.getCode();
        this.errorMsg = exceptionEnum.getMsg();
    }
}
