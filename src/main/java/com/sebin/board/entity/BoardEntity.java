package com.sebin.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity(name = "Board")
@Table(name = "Board")
@Builder
public class BoardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardNumber;

  private String boardTitle;

  private String boardContent;

  private String boardImage;

  private String boardVideo;

  private String boardFile;

  private Long boardWriterNumber;

  private String boardWriterProfile;

  private String boardWriterNickname;

  private String boardWriteDate;

  private Long clickCount;

  private Integer boardLikeCount;

  private Integer boardCommentCount;

  public BoardEntity() {
  }

  public BoardEntity(Long boardNumber, String boardTitle, String boardContent, String boardImage,
      String boardVideo, String boardFile, Long boardWriterNumber, String boardWriterProfile,
      String boardWriterNickname, String boardWriteDate, Long clickCount, Integer boardLikeCount,
      Integer boardCommentCount) {
    this.boardNumber = boardNumber;
    this.boardTitle = boardTitle;
    this.boardContent = boardContent;
    this.boardImage = boardImage;
    this.boardVideo = boardVideo;
    this.boardFile = boardFile;
    this.boardWriterNumber = boardWriterNumber;
    this.boardWriterProfile = boardWriterProfile;
    this.boardWriterNickname = boardWriterNickname;
    this.boardWriteDate = boardWriteDate;
    this.clickCount = clickCount;
    this.boardLikeCount = boardLikeCount;
    this.boardCommentCount = boardCommentCount;
  }
}
