package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.constant.HttpConstant.CONTENT_TYPE_FORM;
import static com.derrors.pt.tools.common.enums.PtCodeEnum.ATMOS;

import com.derrors.pt.tools.integration.client.AtmosClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Slf4j
@Component
public class AtmosClientService extends AbstractClientService {

    @Resource
    private AtmosClient atmosClient;

    @Override
    public String getScene() {
        return ATMOS.name();
    }

    public boolean checkIn(String cookies) {
        try {
            ResponseEntity<Void> responseEntity = atmosClient.checkIn(buildHeaderWithCookies(cookies));
            log.info("The response status is {} when execute check in for atmos.", responseEntity.getStatusCode());
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Execute checkIn atmos website fail.", e);
            return false;
        }
    }

    public boolean thanksForPoints(String cookies, Integer id) {
        try {
            HttpHeaders headers = buildHeaderWithCookies(cookies);
            headers.set(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_FORM);
            ResponseEntity<Void> responseEntity = atmosClient.thanksForPoints(headers, id);
            log.info("response: {}", responseEntity);
            log.info("The response status is {} when execute thanksForPoints in for atmos.", responseEntity.getStatusCode());
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Execute thanksForPoints on atmos website fail.", e);
            return false;
        }
    }
}
