package com.sebin.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PopularSearchDto {
  private String popularTerm;
  private Integer popularSearchCount;

  public PopularSearchDto() {
  }
  @QueryProjection
  public PopularSearchDto(String popularTerm, Integer popularSearchCount) {
    this.popularTerm = popularTerm;
    this.popularSearchCount = popularSearchCount;
  }
}
