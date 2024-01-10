package com.derrors.pt.tools.data.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.data.mapper.PtUserInfoMapper;
import com.derrors.pt.tools.data.repository.PtUserInfoRepository;

import io.micrometer.common.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import cn.hutool.core.collection.CollectionUtil;
import jakarta.annotation.Resource;

/**
 * @author derrors
 * @date 2023/12/21
 */

@Slf4j
@Repository
public class PtUserInfoRepositoryImpl implements PtUserInfoRepository {

    @Resource
    private PtUserInfoMapper ptUserInfoMapper;

    @Override
    public int savePtUser(PtUserInfo ptUserInfo) {
        if (Objects.isNull(ptUserInfo) || StringUtils.isBlank(ptUserInfo.getUserId())) {
            return 0;
        }
        PtUserInfo ptUserInfoInDb = getByUserIdAndPtCode(ptUserInfo.getUserId(), ptUserInfo.getPtCode());
        if (ptUserInfoInDb == null) {
            // 数据没有 ptUserInfo，新增
            log.info("PtUserInfo: {} is not exist in DB, do insert.", ptUserInfo);
            return ptUserInfoMapper.insert(ptUserInfo);
        }
        // 更新 ptNode
        log.info("PtUserInfo: {} exists in DB, do update.", ptUserInfo);
        UpdateWrapper<PtUserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(PtUserInfo.USER_ID, ptUserInfoInDb.getUserId())
            .eq(PtUserInfo.PASS_KEY, ptUserInfoInDb.getPtCode())
            .set(StringUtils.isNotBlank(ptUserInfo.getPasskey()), PtUserInfo.PASS_KEY, ptUserInfo.getPasskey())
            .set(StringUtils.isNotBlank(ptUserInfo.getEmail()), PtUserInfo.EMAIL, ptUserInfo.getEmail())
            .set(StringUtils.isNotBlank(ptUserInfo.getCookies()), PtUserInfo.COOKIES, ptUserInfo.getCookies())
            .set(Objects.nonNull(ptUserInfo.getRegisterDate()), PtUserInfo.REGISTER_DATE, ptUserInfo.getRegisterDate());
        return ptUserInfoMapper.update(null, updateWrapper);
    }

    @Override
    public PtUserInfo getByUserIdAndPtCode(String userId, String ptCode) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(ptCode)) {
            return null;
        }
        QueryWrapper<PtUserInfo> queryWrapper = new QueryWrapper<PtUserInfo>()
            .eq(PtUserInfo.USER_ID, userId)
            .eq(PtUserInfo.PT_CODE, ptCode)
            .eq(PtUserInfo.IS_DELETED, 0);
        List<PtUserInfo> ptUserInfos = ptUserInfoMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(ptUserInfos)) {
            log.info("GetByUserIds result is empty for userId: {}, ptCode: {}", userId, ptCode);
            return null;
        }
        return ptUserInfos.get(0);
    }

    @Override
    public int deleteByUserIdAndPtCode(String userId, String ptCode) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(ptCode)) {
            return 0;
        }
        UpdateWrapper<PtUserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(PtUserInfo.USER_ID, userId)
            .eq(PtUserInfo.PT_CODE, ptCode)
            .set(PtUserInfo.IS_DELETED, 1);
        return ptUserInfoMapper.update(null, updateWrapper);
    }
}
