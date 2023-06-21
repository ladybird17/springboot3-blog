package me.yejin.springboot3blog.service;

import lombok.RequiredArgsConstructor;
import me.yejin.springboot3blog.domain.User;
import me.yejin.springboot3blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public User loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException((email)));
  }
}