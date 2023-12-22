package com.derrors.pt.tools.app;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PtToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtToolsApplication.class, args);
        log.info("Pt-Tools Start Success！");
    }

}
