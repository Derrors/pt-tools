package com.derrors.pt.tools.service;

import com.derrors.pt.tools.common.exception.BizException;
import com.derrors.pt.tools.common.exception.enums.ExceptionEnum;
import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.data.entity.vo.PtUserVo;
import com.derrors.pt.tools.data.repository.PtUserInfoRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author derrors
 * @date 2024/1/9
 */
@Slf4j
@Component
public class PtUserService {

    @Resource
    private PtUserInfoRepository ptUserInfoRepository;


    public PtUserVo getPtUser(String userId, String ptCode) {
        PtUserInfo ptUser = ptUserInfoRepository.getByUserIdAndPtCode(userId, ptCode);
        return PtUserVo.buildForm(ptUser);
    }

    public boolean addNewPtUser(PtUserInfo ptUserInfo) {
        if (ptUserInfo == null) {
            return false;
        }
        if (StringUtils.isBlank(ptUserInfo.getUserId()) || StringUtils.isBlank(ptUserInfo.getPtCode())) {
            throw new BizException(ExceptionEnum.PT_USER_PARAM_EMPTY);
        }
        return 1 == ptUserInfoRepository.savePtUser(ptUserInfo);
    }

    public boolean updatePtUser(PtUserInfo ptUserInfo) {
        if (ptUserInfo == null) {
            return false;
        }
        if (StringUtils.isBlank(ptUserInfo.getUserId()) || StringUtils.isBlank(ptUserInfo.getPtCode())) {
            throw new BizException(ExceptionEnum.PT_USER_PARAM_EMPTY);
        }
        return 1 == ptUserInfoRepository.savePtUser(ptUserInfo);
    }

    public boolean deletePtUser(String userId, String ptCode) {
        return 1 == ptUserInfoRepository.deleteByUserIdAndPtCode(userId, ptCode);
    }
}
