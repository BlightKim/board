package com.sebin.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.sebin.board.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberInfoDto {
  private String nickname;
  private Authority authority;
  private String profile;

  public MemberInfoDto() {
  }

  @QueryProjection
  public MemberInfoDto(String nickname, Authority authority, String profile) {
    this.nickname = nickname;
    this.authority = authority;
    this.profile = profile;
  }
}
