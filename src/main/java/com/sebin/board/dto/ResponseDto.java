package com.sebin.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<T> {
  private boolean result;
  private String message;
  private T data;

  public static <T> ResponseDto<T> setSuccess(String message, T data) {
    return ResponseDto.set(true,message, data);
  }

  public static <T> ResponseDto<T> setFailed(String message) {
    return ResponseDto.set(false,message,null);
  }
}
