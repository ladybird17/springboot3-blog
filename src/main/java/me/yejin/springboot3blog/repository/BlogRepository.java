package me.yejin.springboot3blog.repository;

import me.yejin.springboot3blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-19
 */
public interface BlogRepository extends JpaRepository<Article, Long> {

}
