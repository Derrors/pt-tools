package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.enums.PtCodeEnum.ATMOS;

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
public class AtmosClientService extends AbstractClientService {

    @Resource
    private PtHttpClient atmosClient;

    @Override
    public String getScene() {
        return ATMOS.name();
    }

    @Override
    protected PtHttpClient getHttpClient() {
        return atmosClient;
    }
}
