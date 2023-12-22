package com.derrors.pt.tools.data.repository;

import com.derrors.pt.tools.data.entity.PtNode;

import java.util.List;

/**
 * @author derrors
 * @date 2023/12/21
 */
public interface PtNodeRepository {

    int savePtNode(PtNode ptNode);

    List<PtNode> getByPtCodes(List<String> ptCodes);

    int deleteByPtCode(String ptCode);
}
