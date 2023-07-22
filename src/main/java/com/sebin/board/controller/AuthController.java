package com.sebin.board.controller;

import com.sebin.board.dto.ResponseDto;
import com.sebin.board.dto.SignInDto;
import com.sebin.board.dto.SignInResponseDto;
import com.sebin.board.dto.SignUpDto;
import com.sebin.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  @PostMapping("/signUp")
  public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
    ResponseDto<?> result = authService.signUp(requestBody);
    return result;
  }

  @PostMapping("/signIn")
  public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
    authService.signIn(requestBody);
    return null;
  }
}
