package com.uni.melon.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uni.melon.member.model.dao")
public class MelonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelonApplication.class, args);
    }

}

// 컨트롤 시프트 f10 실행
// 시프트 2번 검색창