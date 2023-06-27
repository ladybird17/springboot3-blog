package me.yejin.springboot3blog.controller;

import lombok.RequiredArgsConstructor;
import me.yejin.springboot3blog.dto.CreateAccessTokenRequest;
import me.yejin.springboot3blog.dto.CreateAccessTokenResponse;
import me.yejin.springboot3blog.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-26
 */
@RequiredArgsConstructor
@RestController
public class TokenApiController {
  private final TokenService tokenService;

  @PostMapping("/api/token")
  public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
      @RequestBody CreateAccessTokenRequest request){
    String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

    return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
  }
}
