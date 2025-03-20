package com.example.diary.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//해당 클래스는 spring-boot-starter-security 의존성 추가로 인해
//모든 요청에 대해 인증을 요구하여 그 부분을 인증없이 접근할 수 있도록 짠 클래스입니다
//비밀번호 해쉬 저장을 위해서 추가한 것이 뭔가 배보다 배꼽이 커진 느낌이지만 일단 이게 최선이었습니다 ㅎㅎ;;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // BCryptPasswordEncoder 객체를 bean 등록하기 위해서 사용하였습니다

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
