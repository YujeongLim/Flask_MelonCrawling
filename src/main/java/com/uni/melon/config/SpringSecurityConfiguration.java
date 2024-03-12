package com.uni.melon.config;

import com.uni.melon.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private final AuthenticationService authenticationService;

    @Autowired
    public SpringSecurityConfiguration(@Lazy AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public WebSecurityCustomizer configure() {

        return (web) -> web.ignoring().requestMatchers(
                "/css/**", "/js/**", "/img/**"
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        Map<String, List<String>> permitListMap = authenticationService.getPermitListMap();

        List<String> adminPermitList = permitListMap.getOrDefault("adminPermitList", Collections.emptyList());
        List<String> memberPermitList = permitListMap.getOrDefault("memberPermitList", Collections.emptyList());

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/playlist_detail/**").hasAnyRole("MEMBER", "ADMIN") // 플레이리스트 상세 페이지에 대한 접근 제한
                        .requestMatchers(memberPermitList.toArray(new String[0])).hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers(adminPermitList.toArray(new String[0])).hasRole("ADMIN")
                        // 인증되지 않은 모든 요청을 처리합니다.
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/member/login") // 로그인 페이지
                        .permitAll() // 모든 사용자가 로그인 페이지에 접근할 수 있도록 허용
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 리디렉션할 기본 URL
                        .failureUrl("/member/login?error=true")) // 로그인 실패 시 리디렉션할 URL
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/member/login?logout=true")) // 로그아웃 성공 시 리디렉션할 URL
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/member/login?accessDenied=true")); // 접근 거부 시 리디렉션할 URL

        return http.build();
    }
}
