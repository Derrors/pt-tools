package com.derrors.pt.tools.common.enums;

import lombok.Getter;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Getter
public enum PtCodeEnum {

    ATMOS("atmos"),
    ;


    private final String code;

    PtCodeEnum(String code) {
        this.code = code;
    }
}
