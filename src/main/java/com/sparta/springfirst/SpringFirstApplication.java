package com.sparta.springfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class SpringFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFirstApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
