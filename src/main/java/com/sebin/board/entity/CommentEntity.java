package com.sebin.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Comment")
@Table(name = "Comment")
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  private Long boardNumber;

  private String userEmail;

  private String commentUserProfile;

  private String commentUserNickname;

  private String commentWriteDate;

  private String commentContent;
}
