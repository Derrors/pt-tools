package com.derrors.pt.tools.data.repository;

import com.derrors.pt.tools.data.entity.PtUserInfo;

/**
 * @author derrors
 * @date 2023/12/21
 */
public interface PtUserInfoRepository {

    int savePtUser(PtUserInfo ptUserInfo);

    PtUserInfo getByUserIdAndPtCode(String userId, String ptCode);

    int deleteByUserIdAndPtCode(String userId, String ptCode);
}
