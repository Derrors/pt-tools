package com.derrors.pt.tools.data.repository;

import com.derrors.pt.tools.data.entity.PtUserInfo;

import java.util.List;

/**
 * @author derrors
 * @date 2023/12/21
 */
public interface PtUserInfoRepository {

    int savePtUser(PtUserInfo ptUserInfo);

    List<PtUserInfo> getByUserIds(List<String> userIds);

    int deleteByUserId(String userId);
}
