package com.sebin.board.exhandler;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

@Data
public class ErrorResult {
  private String timeStamp;
  private String path;
  private Integer code;
  private String message;

  @Builder
  public ErrorResult(String timeStamp, String path, Integer code, String message) {
    this.timeStamp = timeStamp;
    this.path = path;
    this.code = code;
    this.message = message;
  }
}
