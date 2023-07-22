package com.sebin.board.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {
  @NotBlank
  private String userEmail;
  @NotBlank
  private String userPassword;

}
