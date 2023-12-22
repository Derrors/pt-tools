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
import com.derrors.pt.tools.data.entity.PtNode;
import com.derrors.pt.tools.data.mapper.PtNodeMapper;
import com.derrors.pt.tools.data.repository.PtNodeRepository;

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
public class PtNodeRepositoryImpl implements PtNodeRepository {
    @Resource
    private PtNodeMapper ptNodeMapper;

    @Override
    public int savePtNode(PtNode ptNode) {
        if (Objects.isNull(ptNode) || StringUtils.isBlank(ptNode.getPtCode())) {
            return 0;
        }
        List<PtNode> ptNodesInDb = getByPtCodes(Collections.singletonList(ptNode.getPtCode()));
        if (CollectionUtil.isEmpty(ptNodesInDb)) {
            // 数据没有 PtNode，新增
            log.info("PtNode: {} is not exist in DB, do insert.", ptNode);
            return ptNodeMapper.insert(ptNode);
        }
        // 更新 ptNode
        log.info("PtNode: {} exists in DB, do update.", ptNode);
        PtNode toUpdate = ptNodesInDb.get(0);
        UpdateWrapper<PtNode> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(PtNode.ID, toUpdate.getId())
            .set(StringUtils.isNotBlank(ptNode.getPtName()), PtNode.PT_NAME, ptNode.getPtName())
            .set(StringUtils.isNotBlank(ptNode.getPtCode()), PtNode.PT_CODE, ptNode.getPtCode())
            .set(StringUtils.isNotBlank(ptNode.getPtUrl()), PtNode.PT_URL, ptNode.getPtUrl())
            .set(StringUtils.isNotBlank(ptNode.getPtDescription()), PtNode.PT_DESC, ptNode.getPtDescription());
        return ptNodeMapper.update(null, updateWrapper);
    }

    @Override
    public List<PtNode> getByPtCodes(List<String> ptCodes) {
        if (CollectionUtil.isEmpty(ptCodes)) {
            return Collections.emptyList();
        }
        QueryWrapper<PtNode> queryWrapper = new QueryWrapper<PtNode>()
            .in(PtNode.PT_CODE, ptCodes)
            .eq(PtNode.IS_DELETED, 0);
        List<PtNode> ptNodesInDb = ptNodeMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(ptNodesInDb)) {
            log.info("GetByPtCodes result is empty for ptCodes: {}", ptCodes);
            return Collections.emptyList();
        }
        return ptNodesInDb;
    }

    @Override
    public int deleteByPtCode(String ptCode) {
        if (StringUtils.isBlank(ptCode)) {
            return 0;
        }
        UpdateWrapper<PtNode> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(PtNode.PT_CODE, ptCode)
            .set(PtNode.IS_DELETED, 1);
        return ptNodeMapper.update(null, updateWrapper);
    }
}
