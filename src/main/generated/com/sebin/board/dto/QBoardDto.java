package com.sebin.board.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sebin.board.dto.QBoardDto is a Querydsl Projection type for BoardDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardDto extends ConstructorExpression<BoardDto> {

    private static final long serialVersionUID = 1580914518L;

    public QBoardDto(com.querydsl.core.types.Expression<Long> boardNumber, com.querydsl.core.types.Expression<String> boardTitle, com.querydsl.core.types.Expression<String> boardContent, com.querydsl.core.types.Expression<String> boardImage, com.querydsl.core.types.Expression<String> boardVideo, com.querydsl.core.types.Expression<String> boardFile, com.querydsl.core.types.Expression<Long> boardWriterNumber, com.querydsl.core.types.Expression<String> boardWriterProfile, com.querydsl.core.types.Expression<String> boardWriterNickname, com.querydsl.core.types.Expression<String> boardWriteDate, com.querydsl.core.types.Expression<Long> clickCount, com.querydsl.core.types.Expression<Integer> boardLikeCount, com.querydsl.core.types.Expression<Integer> boardCommentCount) {
        super(BoardDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, long.class, String.class, String.class, String.class, long.class, int.class, int.class}, boardNumber, boardTitle, boardContent, boardImage, boardVideo, boardFile, boardWriterNumber, boardWriterProfile, boardWriterNickname, boardWriteDate, clickCount, boardLikeCount, boardCommentCount);
    }

}

