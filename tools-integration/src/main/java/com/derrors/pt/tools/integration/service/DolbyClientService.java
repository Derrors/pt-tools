package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.enums.PtCodeEnum.DOLBY;

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
public class DolbyClientService extends AbstractClientService {

    @Resource
    private PtHttpClient dolbyClient;

    @Override
    public String getScene() {
        return DOLBY.name();
    }

    @Override
    protected PtHttpClient getHttpClient() {
        return dolbyClient;
    }
}
