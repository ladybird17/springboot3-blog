package me.yejin.springboot3blog.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import me.yejin.springboot3blog.config.jwt.TokenProvider;
import me.yejin.springboot3blog.domain.User;
import org.springframework.stereotype.Service;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-26
 */
@RequiredArgsConstructor
@Service
public class TokenService {
  private final TokenProvider tokenProvider;
  private final RefreshTokenService refreshTokenService;
  private final UserService userService;

  public String createNewAccessToken(String refreshToken){
    if(!tokenProvider.validToken(refreshToken)){
      throw new IllegalArgumentException("Unexpected token");
    }

    Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
    User user = userService.findById(userId);
    return tokenProvider.generateToken(user, Duration.ofHours(2));
  }
}
