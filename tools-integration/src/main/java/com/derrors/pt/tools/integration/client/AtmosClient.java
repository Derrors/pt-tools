package com.derrors.pt.tools.integration.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author derrors
 * @date 2024/1/9
 */

public interface AtmosClient {

    @GetExchange("/attendance.php")
    ResponseEntity<Void> checkIn(@RequestHeader HttpHeaders headers);

    @PostExchange("/thanks.php")
    ResponseEntity<Void> thanksForPoints(@RequestHeader HttpHeaders headers, @RequestParam("id") Integer id);
}
