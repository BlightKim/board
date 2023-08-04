package com.sebin.board.reposiotry.queryrepository;

import static com.sebin.board.entity.QBoard.board;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sebin.board.dto.BoardDto;
import com.sebin.board.dto.QBoardDto;
import io.netty.util.internal.StringUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BoardQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<BoardDto> findTopThree() {
        LocalDate now = LocalDate.now();
        LocalDate oneWeekAgo = now.minusWeeks(1);

        return queryFactory.select(
                        new QBoardDto(board.boardNumber, board.boardTitle, board.boardContent, board.boardImage,
                                board.boardVideo, board.boardFile, board.boardWriterNumber, board.boardWriterProfile,
                                board.boardWriterNickname, board.boardWriteDate, board.clickCount, board.boardLikeCount,
                                board.boardCommentCount)
                ).from(board).where(board.boardWriteDate.after(oneWeekAgo).and(board.boardWriteDate.before(now)))
                .orderBy(board.clickCount.desc())
                .limit(3)
                .fetch();
    }
}
