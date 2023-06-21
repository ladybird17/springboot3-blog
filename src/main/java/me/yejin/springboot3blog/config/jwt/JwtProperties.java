package me.yejin.springboot3blog.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-21
 */
@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
  private String issuer;
  private String secretKey;
}
