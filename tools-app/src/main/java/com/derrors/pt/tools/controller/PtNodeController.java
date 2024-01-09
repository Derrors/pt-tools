package com.derrors.pt.tools.controller;

import static com.derrors.pt.tools.common.exception.enums.ExceptionEnum.SYSTEM_ERROR;

import com.derrors.pt.tools.data.entity.PtNode;
import com.derrors.pt.tools.service.PtNodeService;
import com.derrors.pt.tools.common.core.request.PtNodeAddRequest;
import com.derrors.pt.tools.common.core.request.PtNodeCodesRequest;
import com.derrors.pt.tools.common.exception.BizException;
import com.derrors.pt.tools.common.result.Result;
import com.derrors.pt.tools.data.entity.vo.PtNodeVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置 API
 *
 * @author derrors
 * @date 2023/12/29
 */

@Slf4j
@RestController
@RequestMapping("/pt-node")
@RequiredArgsConstructor
public class PtNodeController {

    @Resource
    private PtNodeService ptNodeService;


    @PostMapping("/get")
    @Operation(summary = "根据站点编码获取站点信息")
    public Result<List<PtNodeVo>> getPtNodes(@RequestBody PtNodeCodesRequest request) {
        try {
            return Result.okWithData(ptNodeService.getPtNodeByCodes(request.getPtCodes()));
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "新增站点信息")
    public Result<Void> addPtNode(@RequestBody PtNodeAddRequest request) {
        PtNode ptNode = PtNode.builder()
            .name(request.getName())
            .alias(request.getAlias())
            .code(request.getCode())
            .url(request.getUrl())
            .description(request.getDescription())
            .build();
        try {
            boolean result = ptNodeService.addPtNode(ptNode);
            if (!result) {
                return Result.internalError(SYSTEM_ERROR.getMsg());
            }
            return Result.success();
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "根据站点编码删除站点信息")
    public Result<Void> deletePtNodes(@RequestBody PtNodeCodesRequest request) {
        try {
            boolean result = ptNodeService.deletePtNodes(request.getPtCodes());
            if (!result) {
                return Result.internalError(SYSTEM_ERROR.getMsg());
            }
            return Result.success();
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }
}
