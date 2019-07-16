package com.patent.renew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RenewApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenewApplication.class, args);
    }
}
