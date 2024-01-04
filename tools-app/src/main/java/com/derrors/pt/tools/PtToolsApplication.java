package com.derrors.pt.tools;

import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@SpringBootApplication
public class PtToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtToolsApplication.class, args);
        log.info("Pt-Tools Start Success！");
    }

}
