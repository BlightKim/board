package com.sebin.board.reposiotry.queryrepository;

import static com.sebin.board.entity.QPopularSearchEntity.popularSearchEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sebin.board.dto.PopularSearchDto;
import com.sebin.board.dto.QPopularSearchDto;
import com.sebin.board.entity.QPopularSearchEntity;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PopularSearchQueryRepository {

  private final JPAQueryFactory queryFactory;

  public PopularSearchQueryRepository(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  public List<PopularSearchDto> findPopularSearchList() {
    return queryFactory
        .select(
            new QPopularSearchDto(popularSearchEntity.popularTerm,
                popularSearchEntity.popularSearchCount)
        ).from(popularSearchEntity)
        .orderBy(popularSearchEntity.popularSearchCount.desc())
        .limit(10)
        .fetch();
  }
}
