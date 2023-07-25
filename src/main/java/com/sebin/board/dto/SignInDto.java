package com.sebin.board.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class SignInDto {
  @NotBlank(message = "이메일을 입력해주세요.")
  private String email;
  @NotBlank(message = "비밀번호를 입력해주세요.")
  private String password;


  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
