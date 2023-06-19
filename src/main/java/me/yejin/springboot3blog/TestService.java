package me.yejin.springboot3blog;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-19
 */
@Service
public class TestService {
  @Autowired
  MemberRepository memberRepository;

  public List<Member> getAllMembers(){
    return memberRepository.findAll();
  }
}
