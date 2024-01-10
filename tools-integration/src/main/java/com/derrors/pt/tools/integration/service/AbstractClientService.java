package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.constant.HttpConstant.DEFAULT_AGENT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Slf4j
public abstract class AbstractClientService {

    public abstract String getScene();

    public HttpHeaders buildHeaderWithCookies(String cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, cookies);
        headers.set(HttpHeaders.USER_AGENT, DEFAULT_AGENT);
        return headers;
    }
}
