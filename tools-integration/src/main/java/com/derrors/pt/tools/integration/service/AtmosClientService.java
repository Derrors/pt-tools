package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.constant.HtmlConstant.*;
import static com.derrors.pt.tools.common.constant.HttpConstant.*;
import static com.derrors.pt.tools.common.enums.PtCodeEnum.ATMOS;

import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.integration.client.AtmosClient;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
            log.info("response:{}", responseEntity);
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
            log.info("The response status is {} when execute thanksForPoints in for atmos.",
                responseEntity.getStatusCode());
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Execute thanksForPoints on atmos website fail.", e);
            return false;
        }
    }

    public PtUserInfo getUserDetail(String cookies, Integer uid) {
        try {
            ResponseEntity<String> responseEntity = atmosClient.userDetail(buildHeaderWithCookies(cookies), uid);
            log.info("The response status is {} when execute getUserDetail in for atmos.",
                responseEntity.getStatusCode());
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return parseUserDetail(responseEntity.getBody());
            }
            return null;
        } catch (Exception e) {
            log.error("Execute getUserDetail on atmos website fail.", e);
            return null;
        }
    }

    private PtUserInfo parseUserDetail(String htmlStr) {
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
                            userInfo.setUploadCount(s[1] + SPACE + s[2]);
                            userInfo.setDownloadCount(s[4] + SPACE + s[5]);
                            break;
                        case BONUS:
                            userInfo.setBonus(s[1]);
                            break;
                        case SHARE_RATIO:
                            userInfo.setShareRatio(s[1].split(LEFT)[0]);
                            break;
                        case LEVEL:
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

}
