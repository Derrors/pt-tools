/**
 * ***************************************************** Copyright (C) 2023 bytedance.com. All Rights Reserved This file
 * is part of bytedance EA project. Unauthorized copy of this file, via any medium is strictly prohibited. Proprietary
 * and Confidential. ****************************************************
 **/
package com.derrors.pt.tools.data.entity.vo;

import com.derrors.pt.tools.data.entity.PtNode;
import lombok.Data;

/**
 *
 *
 * @author derrors
 * @date 2023/12/29
 */

@Data
public class PtNodeVo {

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

    public static PtNodeVo buildForm(PtNode ptNode) {
        if (ptNode == null) {
            return null;
        }
        PtNodeVo ptNodeVo = new PtNodeVo();
        ptNodeVo.setName(ptNode.getName());
        ptNodeVo.setAlias(ptNode.getAlias());
        ptNodeVo.setCode(ptNode.getCode());
        ptNodeVo.setUrl(ptNode.getUrl());
        ptNodeVo.setDescription(ptNode.getDescription());
        return ptNodeVo;
    }
}
