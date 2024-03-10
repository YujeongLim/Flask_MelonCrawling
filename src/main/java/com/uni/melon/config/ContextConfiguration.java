package com.uni.melon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @Component 어노테이션은 @Service, @Repository, @Controller 등이 포함된다는 것을 의미합니다.
@Configuration
// @ComponentScan 어노테이션은 Spring에게 com.uni.YJProject 패키지와 그 하위 패키지에서
// Spring @Component 어노테이션이 붙은 클래스들을 찾아 자동으로 빈으로 등록하라고 지시
@ComponentScan(basePackages = "com.uni.melon")
public class ContextConfiguration {
}
