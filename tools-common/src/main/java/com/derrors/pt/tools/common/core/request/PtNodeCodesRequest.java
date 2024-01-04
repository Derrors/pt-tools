/**
 * ***************************************************** Copyright (C) 2023 bytedance.com. All Rights Reserved This file
 * is part of bytedance EA project. Unauthorized copy of this file, via any medium is strictly prohibited. Proprietary
 * and Confidential. ****************************************************
 **/
package com.derrors.pt.tools.common.core.request;

import java.util.List;
import lombok.Data;

/**
 *
 *
 * @author derrors
 * @date 2023/12/29
 */
@Data
public class PtNodeCodesRequest {

    /**
     * Pt 站点的 code 列表
     */
    private List<String> ptCodes;
}
