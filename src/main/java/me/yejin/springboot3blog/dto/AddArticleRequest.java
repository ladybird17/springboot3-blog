package me.yejin.springboot3blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yejin.springboot3blog.domain.Article;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-19
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

  private String title;
  private String content;

  public Article toEntity() {
    return Article.builder().title(title).content(content).build();
  }
}
