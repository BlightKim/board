/*
package com.sebin.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sebin.board.dto.SignUpDto;
import com.sebin.board.entity.Member;
import com.sebin.board.exception.DuplicateIdException;
import com.sebin.board.reposiotry.MemberRepository;
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
  MemberRepository memberRepository;

  private PasswordEncoder passwordEncoder;

  @BeforeEach
  public void beforeEach() {
    passwordEncoder = new BCryptPasswordEncoder();

    Member user = Member
        .builder()
        .email("asd@naver.com")
        .password(passwordEncoder.encode("123"))
        .phoneNumber("010-1234-5678")
        .nickname("rlatpqls")
        .address("포천시")
        .build();

    memberRepository.save(user);
  }

  @Test
  void signUp() {
    String duplicatedEmail = "asd@naver.com";
    String notDuplicatedEmail = "asdf@naver.com";

    SignUpDto example1 = SignUpDto
            .builder()
            .email(duplicatedEmail)
        .password("12")
        .phoneNumber("010-1234-5678")
        .nickname("rlatpqls")
        .address("포천시")
        .build();

    assertThatThrownBy(() -> authService.signUp(example1)).isInstanceOf(DuplicateIdException.class);

    SignUpDto example2 = SignUpDto
        .builder()
        .email(notDuplicatedEmail)
        .password("12")
        .phoneNumber("010-1234-5678")
        .nickname("rlatpqls")
        .address("포천시")
        .build();

  }

  @Test
  void encodePassword() {
    String rightRawPwd = "123";
    String wrongRawPwd = "1234";
    String userEmail = "asd@naver.com";
    Member findUser = memberRepository.findByEmail(userEmail).get();

    assertThat(passwordEncoder.matches(wrongRawPwd, findUser.getPassword())).isFalse();
    assertThat(passwordEncoder.matches(rightRawPwd, findUser.getPassword())).isTrue();
  }
  @Test
  void signIn() {
  }
}*/
