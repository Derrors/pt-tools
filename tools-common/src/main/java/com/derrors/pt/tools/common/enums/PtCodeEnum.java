package com.derrors.pt.tools.common.enums;

import lombok.Getter;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Getter
public enum PtCodeEnum {

    MTEAM("mteam"),
    ATMOS("atmos"),
    DOLBY("dolby"),
    CARPT("carpt"),
    CYANBUG("cyanbug"),
    ;


    private final String code;

    PtCodeEnum(String code) {
        this.code = code;
    }

    public static PtCodeEnum enumOf(String ptCode) {
        for (PtCodeEnum ptCodeEnum : PtCodeEnum.values()) {
            if (ptCodeEnum.getCode().equals(ptCode)) {
                return ptCodeEnum;
            }
        }
        return null;
    }
}
