package com.derrors.pt.tools.app;

import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@MapperScan("com.derrors.pt.tools.data.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PtToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtToolsApplication.class, args);
        log.info("Pt-Tools Start Success！");
    }

}
