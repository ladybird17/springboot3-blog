package me.yejin.springboot3blog;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-19
 */
@RestController
public class TestController {
  @Autowired
  TestService testService;

  @GetMapping("/test")
  public List<Member> getAllMembers() {
    return testService.getAllMembers();
  }
}
