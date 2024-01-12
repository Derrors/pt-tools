package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.constant.HttpConstant.DEFAULT_AGENT;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Slf4j
public abstract class AbstractClientService {

    public static final String REGISTER_DATE = "加入日期";
    public static final String EMAIL = "邮箱";
    public static final String LEVEL = "等级";
    public static final String BONUS = "魔力值";
    public static final String UPLOAD = "上传量:";
    public static final String DOWNLOAD = "下载量:";
    public static final String SHARE_RATIO = "分享率:";



    public abstract String getScene();

    public HttpHeaders buildHeaderWithCookies(String cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, cookies);
        headers.set(HttpHeaders.USER_AGENT, DEFAULT_AGENT);
        return headers;
    }
}
