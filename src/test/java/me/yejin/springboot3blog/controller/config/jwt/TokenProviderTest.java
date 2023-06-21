package me.yejin.springboot3blog.controller.config.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import me.yejin.springboot3blog.config.jwt.JwtProperties;
import me.yejin.springboot3blog.config.jwt.TokenProvider;
import me.yejin.springboot3blog.domain.User;
import me.yejin.springboot3blog.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-22
 */
@SpringBootTest
public class TokenProviderTest {

  @Autowired
  private TokenProvider tokenProvider;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtProperties jwtProperties;

  @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 만드는 것에 성공한다.")
  @Test
  void generateToken() {
    //given
    User testUser = userRepository.save(
        User.builder().email("user@gmail.com").password("test").build()
    );

    //when
    String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));
    //then
    Long userId = Jwts.parser()
        .setSigningKey(jwtProperties.getSecretKey())
        .parseClaimsJws(token)
        .getBody()
        .get("id", Long.class);

    assertThat(userId).isEqualTo(testUser.getId());
  }

  @DisplayName("validToken(): 만료된 토큰일 때 유효성 검증에 실패한다.")
  @Test
  void validToken_fail() {
    //given
    String token = JwtFactory.builder()
        .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
        .build()
        .createToken(jwtProperties);

    //when
    boolean result = tokenProvider.validToken(token);

    //then
    assertThat(result).isFalse();
  }

  @DisplayName("validToken(): 유효한 토큰일 때 유효성 검증에 성공한다.")
  @Test
  void validToken_success() {
    //given
    String token = JwtFactory.withDefaultValues().createToken(jwtProperties);

    //when
    boolean result = tokenProvider.validToken(token);

    //then
    assertThat(result).isTrue();
  }

  @DisplayName("getAuthentication(): 토큰 기반으로 인증 정보를 가져오는 것에 성공한다.")
  @Test
  void getAuthentication() {
    //given
    String userEmail = "user@email.com";
    String token = JwtFactory.builder()
        .subject(userEmail)
        .build()
        .createToken(jwtProperties);

    //when
    Authentication authentication = tokenProvider.getAuthentication(token);

    //then
    assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
  }

  @DisplayName("getUserId(): 토큰 기반으로 사용자 id를 가져오는 것에 성공한다.")
  @Test
  void getUserId() {
    //given
    Long userId = 1L;
    String token = JwtFactory.builder()
        .claims(Map.of("id", userId))
        .build()
        .createToken(jwtProperties);

    //when
    Long userIdByToken = tokenProvider.getUserId(token);

    //then
    assertThat(userIdByToken).isEqualTo(userId);
  }


}
