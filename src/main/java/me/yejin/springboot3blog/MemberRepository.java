package me.yejin.springboot3blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-19
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
