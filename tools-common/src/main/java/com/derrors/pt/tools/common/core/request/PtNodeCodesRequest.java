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
