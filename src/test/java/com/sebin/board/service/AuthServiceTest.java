package com.sebin.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.sebin.board.dto.SignUpDto;
import com.sebin.board.entity.UserEntity;
import com.sebin.board.exception.DuplicateIdException;
import com.sebin.board.reposiotry.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AuthServiceTest {

  @Autowired
  AuthService authService;

  @Autowired
  UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  @BeforeEach
  public void beforeEach() {
    passwordEncoder = new BCryptPasswordEncoder();

    UserEntity user = UserEntity
        .builder()
        .userEmail("asd@naver.com")
        .userPassword(passwordEncoder.encode("123"))
        .userPhoneNumber("010-1234-5678")
        .userNickname("rlatpqls")
        .userAddress("포천시")
        .build();

    userRepository.save(user);
  }

  @Test
  void signUp() {
    String duplicatedEmail = "asd@naver.com";
    String notDuplicatedEmail = "asdf@naver.com";

    SignUpDto example1 = SignUpDto
        .builder()
        .userEmail(duplicatedEmail)
        .userPassword("12")
        .userPhoneNumber("010-1234-5678")
        .userNickname("rlatpqls")
        .userAddress("포천시")
        .build();

    assertThatThrownBy(() -> authService.signUp(example1)).isInstanceOf(DuplicateIdException.class);

    SignUpDto example2 = SignUpDto
        .builder()
        .userEmail(notDuplicatedEmail)
        .userPassword("12")
        .userPhoneNumber("010-1234-5678")
        .userNickname("rlatpqls")
        .userAddress("포천시")
        .build();

  }

  @Test
  void encodePassword() {
    String rightRawPwd = "123";
    String wrongRawPwd = "1234";
    String userEmail = "asd@naver.com";
    UserEntity findUser = userRepository.findByUserEmail(userEmail);

    assertThat(passwordEncoder.matches(wrongRawPwd, findUser.getUserPassword())).isFalse();
    assertThat(passwordEncoder.matches(rightRawPwd, findUser.getUserPassword())).isTrue();
  }
  @Test
  void signIn() {
  }
}