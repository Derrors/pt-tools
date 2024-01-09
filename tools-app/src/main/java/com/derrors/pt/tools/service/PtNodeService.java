package com.derrors.pt.tools.service;

import com.derrors.pt.tools.common.exception.BizException;
import com.derrors.pt.tools.common.exception.enums.ExceptionEnum;
import com.derrors.pt.tools.data.entity.PtNode;
import com.derrors.pt.tools.data.entity.vo.PtNodeVo;
import com.derrors.pt.tools.data.repository.PtNodeRepository;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author derrors
 * @date 2023/12/29
 */
@Slf4j
@Component
public class PtNodeService {

    @Resource
    private PtNodeRepository ptNodeRepository;


    public List<PtNodeVo> getPtNodeByCodes(List<String> ptNodeCodes) {
        return ptNodeRepository.getByPtCodes(ptNodeCodes)
            .stream()
            .map(PtNodeVo::buildForm)
            .collect(Collectors.toList());
    }

    public boolean addPtNode(PtNode ptNode) {
        if (StringUtils.isBlank(ptNode.getName()) || StringUtils.isBlank(ptNode.getCode())) {
            throw new BizException(ExceptionEnum.PT_NODE_PARAM_EMPTY);
        }
        log.info("Execute add pt-node info, pt-node info is: {}", ptNode);
        return 1 == ptNodeRepository.savePtNode(ptNode);
    }

    public boolean updatePtNode(PtNode ptNode) {
        if (StringUtils.isBlank(ptNode.getName()) || StringUtils.isBlank(ptNode.getCode())) {
            throw new BizException(ExceptionEnum.PT_NODE_PARAM_EMPTY);
        }
        log.info("Execute update pt-node info, pt-node info is: {}", ptNode);
        return 1 == ptNodeRepository.savePtNode(ptNode);
    }

    public boolean deletePtNodes(List<String> ptNodeCodes) {
        return 1 == ptNodeRepository.deleteByPtCodes(ptNodeCodes);
    }
}
