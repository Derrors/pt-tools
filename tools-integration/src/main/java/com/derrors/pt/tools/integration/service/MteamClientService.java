package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.enums.PtCodeEnum.MTEAM;

import com.derrors.pt.tools.common.exception.BizException;
import com.derrors.pt.tools.common.exception.enums.ExceptionEnum;
import com.derrors.pt.tools.integration.client.PtHttpClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Slf4j
@Component
public class MteamClientService extends AbstractClientService {

    @Resource
    private PtHttpClient mteamClient;

    @Override
    public String getScene() {
        return MTEAM.name();
    }


    @Override
    public boolean checkIn(String cookies) {
        throw new BizException(ExceptionEnum.NOT_SUPPORTED_OPERATION);
    }

    @Override
    protected PtHttpClient getHttpClient() {
        return mteamClient;
    }

    @Override
    public boolean thanksForPoints(String cookies, Integer id) {
        log.info("request: {} {}", cookies, id);
        return super.thanksForPoints(cookies, id);
    }
}
