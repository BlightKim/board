package com.sebin.board.service;

import com.sebin.board.dto.*;
import com.sebin.board.entity.Member;
import com.sebin.board.exception.DuplicateIdException;
import com.sebin.board.jwt.TokenProvider;
import com.sebin.board.reposiotry.MemberQueryRepository;
import com.sebin.board.reposiotry.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManagerBuilder managerBuilder;
  private final TokenProvider tokenProvider;
  private final MemberQueryRepository memberQueryRepository;

  @Transactional
  public ResponseDto<?> signUp(SignUpDto signUpDto) {
    String userPassword = signUpDto.getPassword();
    String userPasswordCheck = signUpDto.getPasswordCheck();

    if (isDuplicated(signUpDto)) {
      throw new DuplicateIdException("중복 된 이메일 입니다.");
    }

    if (!userPassword.equals(userPasswordCheck)) {
      throw new RuntimeException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
    }

    String plainPassword = signUpDto.getPassword(); // 암호화 전

    encodePlainPwd(signUpDto, plainPassword); // 암호화 후 dto 패스워드 수정

    Member member = signUpDto.toMember();

    memberRepository.save(member);

    return ResponseDto.setSuccess("SignUp Success!", null);
  }


  public ResponseDto<TokenDto> signIn(SignInDto signInDto) {

    UsernamePasswordAuthenticationToken authenticationToken = signInDto.toAuthentication();
    Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
    String email = authentication.getName();
    MemberInfoDto memberInfoDto = memberQueryRepository.searchOne(email);
    TokenDto tokenDto = tokenProvider.generateToken(authentication, memberInfoDto);
    return ResponseDto.set(true, "Login Success", tokenDto);
  }


  private void encodePlainPwd(SignUpDto signUpDto, String plainPassword) {
    signUpDto.setPassword(passwordEncoder.encode(plainPassword));
  }


  private boolean isDuplicated(SignUpDto signUpDto) {
    return memberRepository.existsByEmail(signUpDto.getEmail());
  }
}