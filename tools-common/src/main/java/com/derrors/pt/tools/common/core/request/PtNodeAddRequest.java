/**
 * ***************************************************** Copyright (C) 2023 bytedance.com. All Rights Reserved This file
 * is part of bytedance EA project. Unauthorized copy of this file, via any medium is strictly prohibited. Proprietary
 * and Confidential. ****************************************************
 **/
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
