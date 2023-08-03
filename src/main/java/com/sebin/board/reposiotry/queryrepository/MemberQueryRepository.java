package com.sebin.board.reposiotry.queryrepository;

import static com.sebin.board.entity.QMember.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sebin.board.dto.MemberInfoDto;
import com.sebin.board.dto.QMemberInfoDto;
import com.sebin.board.entity.QMember;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MemberQueryRepository {

  private final JPAQueryFactory queryFactory;

  public MemberQueryRepository(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  public MemberInfoDto searchOne(String email) {
    return queryFactory
        .select(
            new QMemberInfoDto(member.nickname, member.authority, member.profile)
        )
        .from(member)
        .where(member.email.eq(email))
        .fetchOne();
  }
}
