package me.yejin.springboot3blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.yejin.springboot3blog.dto.AddUserRequest;
import me.yejin.springboot3blog.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-20
 */
@RequiredArgsConstructor
@Controller
public class UserApiController {

  private final UserService userService;

  @PostMapping("/user")
  public String signup(AddUserRequest request){
    userService.save(request);
    return "redirect:/login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response){
    new SecurityContextLogoutHandler().logout(request, response,
        SecurityContextHolder.getContext().getAuthentication());
    return "redirect:/login";
  }
}
