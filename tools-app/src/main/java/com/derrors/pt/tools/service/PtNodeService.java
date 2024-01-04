/**
 * ***************************************************** Copyright (C) 2023 bytedance.com. All Rights Reserved This file
 * is part of bytedance EA project. Unauthorized copy of this file, via any medium is strictly prohibited. Proprietary
 * and Confidential. ****************************************************
 **/
package com.derrors.pt.tools.service;

import com.derrors.pt.tools.common.core.request.PtNodeAddRequest;
import com.derrors.pt.tools.common.exception.BizException;
import com.derrors.pt.tools.common.exception.enums.ExceptionEnum;
import com.derrors.pt.tools.data.entity.PtNode;
import com.derrors.pt.tools.data.entity.vo.PtNodeVo;
import com.derrors.pt.tools.data.repository.PtNodeRepository;
import com.derrors.pt.tools.data.repository.PtUserInfoRepository;
import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author derrors
 * @date 2023/12/29
 */
@Slf4j
@Component
public class PtNodeService {

    @Resource
    private PtNodeRepository ptNodeRepository;

    @Resource
    private PtUserInfoRepository ptUserInfoRepository;


    public List<PtNodeVo> getPtNodeByCodes(List<String> ptNodeCodes) {
        if (CollectionUtils.isEmpty(ptNodeCodes)) {
            log.info("The ptNodeCodes are empty.");
            return Collections.emptyList();
        }
        return ptNodeRepository.getByPtCodes(ptNodeCodes)
            .stream()
            .map(PtNodeVo::buildForm)
            .collect(Collectors.toList());
    }

    public boolean addPtNode(PtNodeAddRequest request) {
        if (StringUtils.isBlank(request.getName()) || StringUtils.isBlank(request.getCode())) {
            log.warn("The name or code of the pt node is blank, add request: {}", request);
            throw new BizException(ExceptionEnum.PT_NODE_PARAM_EMPTY);
        }

        PtNode ptNode = PtNode.builder()
            .name(request.getName())
            .alias(request.getAlias())
            .code(request.getCode())
            .url(request.getUrl())
            .description(request.getDescription())
            .build();

        log.info("Execute add pt-node info, pt-node info is: {}", ptNode);
        return 1 == ptNodeRepository.savePtNode(ptNode);
    }

    public boolean deletePtNodes(List<String> ptNodeCodes) {
        if (CollectionUtils.isEmpty(ptNodeCodes)) {
            log.info("The ptNodeCodes are empty.");
            return false;
        }
        return 1 == ptNodeRepository.deleteByPtCodes(ptNodeCodes);
    }
}
