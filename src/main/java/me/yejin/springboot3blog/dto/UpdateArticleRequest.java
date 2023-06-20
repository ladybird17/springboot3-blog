package me.yejin.springboot3blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateArticleRequest {
  private String title;
  private String content;
}
