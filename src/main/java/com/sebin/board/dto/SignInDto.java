package com.sebin.board.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class SignInDto {
  @NotBlank
  private String email;
  @NotBlank
  private String password;


  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
