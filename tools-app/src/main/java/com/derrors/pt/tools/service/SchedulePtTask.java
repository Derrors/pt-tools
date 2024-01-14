package com.derrors.pt.tools.service;

import static com.derrors.pt.tools.common.enums.PtCodeEnum.*;

import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.data.entity.vo.PtUserVo;
import com.derrors.pt.tools.integration.service.AbstractClientService;
import com.derrors.pt.tools.integration.service.CyanClientService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author derrors
 * @date 2024/1/9
 */

@Slf4j
@Component
public class SchedulePtTask {

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

    @Scheduled(cron = "0 10 8 * * *")
    public void autoCheckIn() {
        List<String> ptCodes = Arrays.asList(ATMOS.getCode(), DOLBY.getCode(), CARPT.getCode(), CYANBUG.getCode());
        try {
            ptCodes.forEach(code -> {
                log.info("[SchedulePtTask] Execute autoCheckIn for {}", code);
                String userCookies = ptUserService.getUserCookies("derrors", code);
                AbstractClientService clientService = clientServiceMap.get(code);
                boolean result = clientService.checkIn(userCookies);
                log.info("[SchedulePtTask] Execute autoCheckIn result is {} for {}", result, code);
            });
        } catch (Exception e) {
            log.info("[SchedulePtTask] Execute autoCheckIn fail.", e);
        }
    }

    @Scheduled(cron = "0 20 8 * * *")
    public void syncUserInfo() {
        List<String> ptCodes = Arrays.asList(MTEAM.getCode(), ATMOS.getCode(), DOLBY.getCode(), CARPT.getCode(),
            CYANBUG.getCode());
        try {
            ptCodes.forEach(code -> {
                log.info("[SchedulePtTask] Execute syncUserInfo for {}", code);
                String userCookies = ptUserService.getUserCookies("derrors", code);
                PtUserVo ptUser = ptUserService.getPtUser("derrors", code);
                AbstractClientService clientService = clientServiceMap.get(code);
                PtUserInfo userDetail = clientService.getUserDetail(userCookies, ptUser.getUid());
                boolean result = ptUserService.updatePtUser(userDetail);
                log.info("[SchedulePtTask] Execute syncUserInfo result is {} for {}", result, code);
            });
        } catch (Exception e) {
            log.info("[SchedulePtTask] Execute syncUserInfo fail.", e);
        }
    }

    @Scheduled(cron = "0 30 8 * * *")
    public void thanksForBonus() {
    }

    @Scheduled(cron = "0 40 8 * * *")
    public void cyanbugFeature() {
        try {
            log.info("[SchedulePtTask] Execute cyanbugFeature.");
            String userCookies = ptUserService.getUserCookies("derrors", CYANBUG.getCode());
            CyanClientService clientService = (CyanClientService) clientServiceMap.get(CYANBUG.getCode());
            boolean result = clientService.sengMsgForBonus(userCookies);
            log.info("[SchedulePtTask] Execute cyanbugFeature result is {}", result);
        } catch (Exception e) {
            log.info("[SchedulePtTask] Execute cyanbugFeature fail.", e);
        }
    }
}
