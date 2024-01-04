package com.derrors.pt.tools.common.core.request;

import lombok.Data;

/**
 *
 *
 * @author derrors
 * @date 2023/12/29
 */
@Data
public class PtNodeAddRequest {
    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点简称
     */
    private String alias;

    /**
     * 站点 Code
     */
    private String code;

    /**
     * 站点地址
     */
    private String url;

    /**
     * 站点描述
     */
    private String description;
}
