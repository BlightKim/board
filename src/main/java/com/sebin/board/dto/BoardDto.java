package com.sebin.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BoardDto {
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

  public BoardDto() {

  }

  @QueryProjection
  public BoardDto(Long boardNumber, String boardTitle, String boardContent, String boardImage,
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
