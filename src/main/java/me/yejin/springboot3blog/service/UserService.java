package me.yejin.springboot3blog.service;

import lombok.RequiredArgsConstructor;
import me.yejin.springboot3blog.domain.User;
import me.yejin.springboot3blog.dto.AddUserRequest;
import me.yejin.springboot3blog.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * author : yjseo
 * <p>
 * date : 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Long save(AddUserRequest dto){
    return userRepository.save(User.builder()
            .email(dto.getEmail())
            .password(bCryptPasswordEncoder.encode(dto.getPassword()))
            .build()).getId();
  }

}
