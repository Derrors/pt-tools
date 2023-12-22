/**
 * *****************************************************
 * Copyright (C) 2023 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.derrors.pt.tools.data.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.data.mapper.PtUserInfoMapper;
import com.derrors.pt.tools.data.repository.PtUserInfoRepository;

import io.micrometer.common.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.Collections;
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
        List<PtUserInfo> ptUserInfos = getByUserIds(Collections.singletonList(ptUserInfo.getUserId()));
        if (CollectionUtil.isEmpty(ptUserInfos)) {
            // 数据没有 ptUserInfos，新增
            log.info("PtUserInfo: {} is not exist in DB, do insert.", ptUserInfo);
            return ptUserInfoMapper.insert(ptUserInfo);
        }
        // 更新 ptNode
        log.info("PtUserInfo: {} exists in DB, do update.", ptUserInfo);
        PtUserInfo toUpdate = ptUserInfos.get(0);
        UpdateWrapper<PtUserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(PtUserInfo.ID, toUpdate.getId())
            .set(StringUtils.isNotBlank(ptUserInfo.getPasskey()), PtUserInfo.PASS_KEY, ptUserInfo.getPasskey())
            .set(StringUtils.isNotBlank(ptUserInfo.getEmail()), PtUserInfo.EMAIL, ptUserInfo.getEmail())
            .set(CollectionUtil.isNotEmpty(ptUserInfo.getCookies()), PtUserInfo.COOKIES, ptUserInfo.getCookies())
            .set(Objects.nonNull(ptUserInfo.getRegisterDate()), PtUserInfo.REGISTER_DATE, ptUserInfo.getRegisterDate());
        return ptUserInfoMapper.update(null, updateWrapper);
    }

    @Override
    public List<PtUserInfo> getByUserIds(List<String> userIds) {
        if (CollectionUtil.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        QueryWrapper<PtUserInfo> queryWrapper = new QueryWrapper<PtUserInfo>()
            .in(PtUserInfo.USER_ID, userIds)
            .eq(PtUserInfo.IS_DELETED, 0);
        List<PtUserInfo> ptUserInfos = ptUserInfoMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(ptUserInfos)) {
            log.info("GetByUserIds result is empty for userIds: {}", userIds);
            return Collections.emptyList();
        }
        return ptUserInfos;
    }

    @Override
    public int deleteByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return 0;
        }
        UpdateWrapper<PtUserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(PtUserInfo.USER_ID, userId)
            .set(PtUserInfo.IS_DELETED, 1);
        return ptUserInfoMapper.update(null, updateWrapper);
    }
}