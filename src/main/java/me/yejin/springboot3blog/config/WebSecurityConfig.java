package me.yejin.springboot3blog.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import lombok.RequiredArgsConstructor;
import me.yejin.springboot3blog.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-20
 */
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

  private final UserDetailService userService;

  @Bean
  public WebSecurityCustomizer configure() {
    //스프링 시큐리티 기능 비활성화
    return (web) -> web.ignoring()
        .requestMatchers(toH2Console())
        .requestMatchers("/static/**");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //특정 HTTP 요청에 대한 웹 기반 보안 구성
    return http
        .authorizeHttpRequests()//인증, 인가 설정
        .requestMatchers("/login", "/signup", "/user").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()//폼 기반 로그인 설정
        .loginPage("/login")
        .defaultSuccessUrl("/articles")
        .and()
        .logout()//로그아웃 설정
        .logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .and()
        .csrf().disable()//csrf 비활성화
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
    //인증 관리자 관련 설정
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userService)//사용자 정보 서비스 설정
        .passwordEncoder(bCryptPasswordEncoder)
        .and()
        .build();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    //패스워드 인코더로 사용할 빈 등록
    return new BCryptPasswordEncoder();
  }
}