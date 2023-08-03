package com.sebin.board.reposiotry.queryrepository;

import static com.sebin.board.entity.QBoard.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sebin.board.dto.BoardDto;
import com.sebin.board.dto.QBoardDto;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BoardQueryRepository {

  private final JPAQueryFactory queryFactory;

  public BoardQueryRepository(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  public List<BoardDto> findTopThree() {
    return queryFactory.select(
        new QBoardDto(board.boardNumber, board.boardTitle, board.boardContent, board.boardImage,
            board.boardVideo, board.boardFile, board.boardWriterNumber, board.boardWriterProfile,
            board.boardWriterNickname, board.boardWriteDate, board.clickCount, board.boardLikeCount,
            board.boardCommentCount)
    ).from(board)
        .where(board.boardWriteDate.)
  }
}
