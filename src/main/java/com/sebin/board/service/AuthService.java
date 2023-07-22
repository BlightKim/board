package com.sebin.board.service;

import com.sebin.board.dto.ResponseDto;
import com.sebin.board.dto.SignInDto;
import com.sebin.board.dto.SignInResponseDto;
import com.sebin.board.dto.SignUpDto;
import com.sebin.board.entity.UserEntity;
import com.sebin.board.exception.DuplicateIdException;
import com.sebin.board.reposiotry.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public ResponseDto<?> signUp(SignUpDto signUpDto) {
    String userPassword = signUpDto.getUserPassword();
    String userPasswordCheck = signUpDto.getUserPasswordCheck();

    if (isDuplicated(signUpDto)) {
      throw new DuplicateIdException("중복 된 이메일 입니다.");
    }

    if (!userPassword.equals(userPasswordCheck)) {
      throw new RuntimeException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
    }

    String plainPassword = signUpDto.getUserPassword(); // 암호화 전

    encodePlainPwd(signUpDto, plainPassword); // 암호화 후 dto 패스워드 수정

    UserEntity userEntity = signUpDto.toUserEntity();

    userRepository.save(userEntity);

    return ResponseDto.setSuccess("SignUp Success!", null);
  }


  public ResponseDto<SignInResponseDto> signIn(SignInDto signInDto) {
    String userEmail = signInDto.getUserEmail();

    return null;
  }


  private void encodePlainPwd(SignUpDto signUpDto, String plainPassword) {
    signUpDto.setUserPassword(passwordEncoder.encode(plainPassword));
  }


  private boolean isDuplicated(SignUpDto signUpDto) {
    return userRepository.existsByUserEmail(signUpDto.getUserEmail());
  }
}