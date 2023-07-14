package com.sebin.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class SignInResponseDto {
  private String token;
  private Integer exprTime;

}
