package com.sebin.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
public class Board {

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

  private LocalDate boardWriteDate;

  private Long clickCount;

  private Integer boardLikeCount;

  private Integer boardCommentCount;

  public Board() {
  }

  public Board(Long boardNumber, String boardTitle, String boardContent, String boardImage,
               String boardVideo, String boardFile, Long boardWriterNumber, String boardWriterProfile,
               String boardWriterNickname, LocalDate boardWriteDate, Long clickCount, Integer boardLikeCount,
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
