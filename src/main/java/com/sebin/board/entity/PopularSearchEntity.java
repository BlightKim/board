package com.sebin.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "PopularSearch")
@Table(name = "PopularSearch")
@Builder
@Getter
public class PopularSearchEntity {
  @Id
  private String popular_team;

  private Integer popularSearchCount;

  public PopularSearchEntity() {
  }

  public PopularSearchEntity(String popular_team, Integer popularSearchCount) {
    this.popular_team = popular_team;
    this.popularSearchCount = popularSearchCount;
  }
}
