package com.derrors.pt.tools.integration.service;

import static com.derrors.pt.tools.common.constant.HtmlConstant.COLOR_RATIO;
import static com.derrors.pt.tools.common.enums.PtCodeEnum.CYANBUG;

import com.derrors.pt.tools.common.exception.BizException;
import com.derrors.pt.tools.data.entity.PtUserInfo;
import com.derrors.pt.tools.integration.client.PtHttpClient;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author derrors
 * @date 2024/1/10
 */
@Slf4j
@Component
public class CyanClientService extends AbstractClientService {

    @Resource
    private PtHttpClient cyanClient;

    private static final List<String> COMMAND_LIST = Arrays.asList(
        "青虫娘 求魔力", "青虫娘 求上传", "青虫娘 求VIP", "青虫娘 求彩虹ID"
    );

    private static final String MSG_SHOT = "我喊";

    private static final String MSG_SENT = "yes";

    private static final String MSG_TYPE = "shoutbox";

    @Override
    public String getScene() {
        return CYANBUG.name();
    }


    @Override
    protected PtHttpClient getHttpClient() {
        return cyanClient;
    }

    @Override
    public PtUserInfo getUserDetail(String cookies, Integer uid) {
        try {
            ResponseEntity<String> responseEntity = cyanClient.userDetail(buildHeaderWithCookies(cookies), uid);
            log.info("The response status is {} when execute getUserDetail in for {}.", getScene(), responseEntity.getStatusCode());
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PtUserInfo ptUserInfo = super.parseUserDetail(responseEntity.getBody());
                ptUserInfo.setShareRatio(parseShareRatio(responseEntity.getBody()));
                return ptUserInfo;
            }
            return null;
        } catch (Exception e) {
            log.error("Execute getUserDetail on {} website fail.", getScene(), e);
            return null;
        }
    }

    public boolean sengMsgForBonus(String cookies) {
        try {
            COMMAND_LIST.forEach(text -> {
                ResponseEntity<Void> responseEntity = cyanClient.sendMsgToShotBox(buildHeaderWithCookies(cookies),
                    text, MSG_SHOT, MSG_SENT, MSG_TYPE);
                log.info("The response status is {} when execute sengMsgForBonus in for {}.", getScene(), responseEntity.getStatusCode());
                if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                    throw new RuntimeException("The status of response is not 200 OK.");
                }
            });
            return true;
        } catch (Exception e) {
            log.error("Execute sengMsgForBonus on {} website fail.", getScene(), e);
            return false;
        }
    }

    private String parseShareRatio(String htmlStr) {
        Document document = Jsoup.parse(htmlStr);
        Elements elements = document.getElementsByClass(COLOR_RATIO);
        for (Element element : elements) {
            if (element.nextSibling() != null) {
                return element.nextSibling().toString().strip();
            }
        }
        return null;
    }
}
