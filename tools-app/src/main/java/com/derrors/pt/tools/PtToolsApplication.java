package com.derrors.pt.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class PtToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtToolsApplication.class, args);
        log.info("Pt-Tools Start Success！");
    }

}
