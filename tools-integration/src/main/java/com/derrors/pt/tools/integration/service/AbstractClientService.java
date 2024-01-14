package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.constant.HtmlConstant.BONUS;
import static com.derrors.pt.tools.common.constant.HtmlConstant.COIN;
import static com.derrors.pt.tools.common.constant.HtmlConstant.EMAIL;
import static com.derrors.pt.tools.common.constant.HtmlConstant.LEFT;
import static com.derrors.pt.tools.common.constant.HtmlConstant.LEVEL;
import static com.derrors.pt.tools.common.constant.HtmlConstant.LEVEL2;
import static com.derrors.pt.tools.common.constant.HtmlConstant.REGISTER_DATE;
import static com.derrors.pt.tools.common.constant.HtmlConstant.SHARE_RATIO;
import static com.derrors.pt.tools.common.constant.HtmlConstant.SHARE_RATIO_WITH_COMMA;
import static com.derrors.pt.tools.common.constant.HtmlConstant.SPACE;
import static com.derrors.pt.tools.common.constant.HtmlConstant.T;
import static com.derrors.pt.tools.common.constant.HtmlConstant.TD_EMBEDDED;
import static com.derrors.pt.tools.common.constant.HtmlConstant.TITLE;
import static com.derrors.pt.tools.common.constant.HtmlConstant.TR;
import static com.derrors.pt.tools.common.constant.HtmlConstant.UPLOAD;
import static com.derrors.pt.tools.common.constant.HtmlConstant.UPLOAD_WITH_COMMA;
import static com.derrors.pt.tools.common.constant.HtmlConstant.UPLOAD_WITH_COMMA2;
import static com.derrors.pt.tools.common.constant.HttpConstant.CONTENT_TYPE_FORM;
import static com.derrors.pt.tools.common.constant.HttpConstant.DEFAULT_AGENT;

import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.integration.client.PtHttpClient;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Slf4j
public abstract class AbstractClientService {

    public abstract String getScene();

    protected abstract PtHttpClient getHttpClient();

    public boolean checkIn(String cookies) {
        try {
            PtHttpClient httpClient = getHttpClient();
            ResponseEntity<Void> responseEntity = httpClient.checkIn(buildHeaderWithCookies(cookies));
            log.info("The response status is {} when execute check in for {}.", responseEntity.getStatusCode(), getScene());
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Execute checkIn {} website fail.", getScene(), e);
            return false;
        }
    }

    public boolean thanksForPoints(String cookies, Integer id) {
        try {
            PtHttpClient httpClient = getHttpClient();
            HttpHeaders headers = buildHeaderWithCookies(cookies);
            headers.set(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_FORM);
            ResponseEntity<Void> responseEntity = httpClient.thanksForPoints(headers, id);
            log.info("The response status is {} when execute thanksForPoints in for {}.", getScene(), responseEntity.getStatusCode());
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Execute thanksForPoints on {} website fail.", getScene(), e);
            return false;
        }
    }

    public PtUserInfo getUserDetail(String cookies, Integer uid) {
        try {
            PtHttpClient httpClient = getHttpClient();
            ResponseEntity<String> responseEntity = httpClient.userDetail(buildHeaderWithCookies(cookies), uid);
            log.info("The response status is {} when execute getUserDetail in for {}.", getScene(), responseEntity.getStatusCode());
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return parseUserDetail(responseEntity.getBody());
            }
            return null;
        } catch (Exception e) {
            log.error("Execute getUserDetail on {} website fail.", getScene(), e);
            return null;
        }
    }

    protected PtUserInfo parseUserDetail(String htmlStr) {
        Document document = Jsoup.parse(htmlStr);
        Elements elements = document.select(TD_EMBEDDED);
        PtUserInfo userInfo = new PtUserInfo();
        for (Element element : elements) {
            Elements trs = element.select(TR);
            trs.forEach(el -> {
                try {
                    String[] s = el.text().split(SPACE);
                    switch (s[0]) {
                        case REGISTER_DATE:
                            String date = s[1] + T + s[2];
                            userInfo.setRegisterDate(LocalDateTime.parse(date));
                            break;
                        case EMAIL:
                            userInfo.setEmail(s[1]);
                            break;
                        case UPLOAD:
                            userInfo.setUploadCount(s[2] + SPACE + s[3]);
                            userInfo.setDownloadCount(s[6] + SPACE + s[7]);
                            break;
                        case UPLOAD_WITH_COMMA, UPLOAD_WITH_COMMA2:
                            userInfo.setUploadCount(s[1] + SPACE + s[2]);
                            userInfo.setDownloadCount(s[4] + SPACE + s[5]);
                            break;
                        case BONUS, COIN:
                            userInfo.setBonus(s[1]);
                            break;
                        case SHARE_RATIO:
                            userInfo.setShareRatio(s[2]);
                            break;
                        case SHARE_RATIO_WITH_COMMA:
                            userInfo.setShareRatio(s[1].split(LEFT)[0]);
                            break;
                        case LEVEL, LEVEL2:
                            String level = Objects.requireNonNull(el.child(1).
                                firstElementChild()).attribute(TITLE).getValue();
                            userInfo.setLevel(level);
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    log.warn("Parse user detail error, element: {}", el, e);
                }
            });
        }
        log.info("Parse user detail success, parse result: {}", userInfo);
        return userInfo;
    }

    protected HttpHeaders buildHeaderWithCookies(String cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, cookies);
        headers.set(HttpHeaders.USER_AGENT, DEFAULT_AGENT);
        return headers;
    }
}
