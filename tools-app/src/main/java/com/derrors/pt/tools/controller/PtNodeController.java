package com.derrors.pt.tools.controller;

import static com.derrors.pt.tools.common.exception.enums.ExceptionEnum.SYSTEM_ERROR;

import com.derrors.pt.tools.common.core.request.PtNodeAddRequest;
import com.derrors.pt.tools.common.core.request.PtNodeCodesRequest;
import com.derrors.pt.tools.common.core.request.PtUserAddRequest;
import com.derrors.pt.tools.common.enums.PtCodeEnum;
import com.derrors.pt.tools.common.exception.BizException;
import com.derrors.pt.tools.common.result.Result;
import com.derrors.pt.tools.data.entity.PtNode;
import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.data.entity.vo.PtNodeVo;
import com.derrors.pt.tools.data.entity.vo.PtUserVo;
import com.derrors.pt.tools.integration.service.AbstractClientService;
import com.derrors.pt.tools.integration.service.CyanClientService;
import com.derrors.pt.tools.service.PtNodeService;
import com.derrors.pt.tools.service.PtUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Resource
    private PtUserService ptUserService;

    @Resource
    private List<AbstractClientService> clientServiceList;

    @Resource
    private Map<String, AbstractClientService> clientServiceMap;

    @PostConstruct
    private void init() {
        clientServiceList.forEach(client -> clientServiceMap.putIfAbsent(client.getScene(), client));
    }

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

    @PostMapping("/add-user")
    @Operation(summary = "新增站点用户信息")
    public Result<Void> addPtUser(@RequestBody PtUserAddRequest request) {
        PtUserInfo ptUser = PtUserInfo.builder()
            .userId(request.getUserId())
            .ptCode(request.getPtCode())
            .uid(request.getUid())
            .passkey(request.getPasskey())
            .cookies(request.getCookies())
            .registerDate(LocalDateTime.parse(request.getRegisterDate()))
            .build();
        try {
            boolean result = ptUserService.addNewPtUser(ptUser);
            if (!result) {
                return Result.internalError(SYSTEM_ERROR.getMsg());
            }
            return Result.success();
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }

    @GetMapping("/checkIn")
    @Operation(summary = "测试站点功能")
    public Result<Boolean> dolbyCheckIn(@RequestParam String userId, @RequestParam String ptCode) {
        try {
            String userCookies = ptUserService.getUserCookies(userId, ptCode);
            PtCodeEnum ptCodeEnum = PtCodeEnum.enumOf(ptCode);
            AbstractClientService client = clientServiceMap.get(ptCodeEnum.name());
            return Result.okWithData(client.checkIn(userCookies));
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }

    @GetMapping("/thanks")
    @Operation(summary = "测试站点功能")
    public Result<Boolean> testFunThanks(@RequestParam String userId, @RequestParam String ptCode, @RequestParam Integer id) {
        try {
            String userCookies = ptUserService.getUserCookies(userId, ptCode);
            PtCodeEnum ptCodeEnum = PtCodeEnum.enumOf(ptCode);
            AbstractClientService client = clientServiceMap.get(ptCodeEnum.name());
            return Result.okWithData(client.thanksForPoints(userCookies, id));
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }

    @GetMapping("/userDetail")
    @Operation(summary = "userDetail")
    public Result<PtUserVo> userDetail(@RequestParam String userId, @RequestParam String ptCode) {
        try {
            String userCookies = ptUserService.getUserCookies(userId, ptCode);
            PtUserVo ptUser = ptUserService.getPtUser(userId, ptCode);
            PtCodeEnum ptCodeEnum = PtCodeEnum.enumOf(ptCode);
            AbstractClientService client = clientServiceMap.get(ptCodeEnum.name());
            return Result.okWithData(PtUserVo.buildForm(client.getUserDetail(userCookies, ptUser.getUid())));
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }

    @GetMapping("/sendMsg")
    @Operation(summary = "sendMsg")
    public Result<Boolean> sendMsg(@RequestParam String userId, @RequestParam String ptCode) {
        try {
            String userCookies = ptUserService.getUserCookies(userId, ptCode);
            PtCodeEnum ptCodeEnum = PtCodeEnum.enumOf(ptCode);
            CyanClientService client = (CyanClientService) clientServiceMap.get(ptCodeEnum.name());
            return Result.okWithData(client.sengMsgForBonus(userCookies));
        } catch (BizException e) {
            return Result.failWithMsg(e);
        }
    }
}
