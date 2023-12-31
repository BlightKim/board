package com.sebin.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "Liky")
@Table(name = "Liky")
@Getter
@Builder
public class LikyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long likeId;

  private Long boardNumber;

  private String userEmail;

  private String likeUserProfile;

  private String likeUserNickname;

  public LikyEntity() {
  }

  public LikyEntity(Long likeId, Long boardNumber, String userEmail, String likeUserProfile,
      String likeUserNickname) {
    this.likeId = likeId;
    this.boardNumber = boardNumber;
    this.userEmail = userEmail;
    this.likeUserProfile = likeUserProfile;
    this.likeUserNickname = likeUserNickname;
  }
}
