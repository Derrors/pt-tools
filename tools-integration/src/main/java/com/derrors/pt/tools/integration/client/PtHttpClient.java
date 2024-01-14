package com.derrors.pt.tools.integration.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author derrors
 * @date 2024/1/13
 */
public interface PtHttpClient {
    @GetExchange("/attendance.php")
    ResponseEntity<Void> checkIn(@RequestHeader HttpHeaders headers);

    @GetExchange("/userdetails.php")
    ResponseEntity<String> userDetail(@RequestHeader HttpHeaders headers, @RequestParam("id") Integer id);

    @PostExchange("/thanks.php")
    ResponseEntity<Void> thanksForPoints(@RequestHeader HttpHeaders headers, @RequestParam("id") Integer id);

    @GetExchange("/shoutbox.php")
    ResponseEntity<Void> sendMsgToShotBox(@RequestHeader HttpHeaders headers, @RequestParam("shbox_text") String text, @RequestParam("shout") String shout, @RequestParam("sent") String sent, @RequestParam("type") String type);
}
