package com.sebin.board.controller;

import com.sebin.board.dto.ResponseDto;
import com.sebin.board.dto.SignUpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
  @PostMapping("/signUp")
  public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
    log.info("requestBody.toString()={}", requestBody.toString());
    return null;
  }
}
