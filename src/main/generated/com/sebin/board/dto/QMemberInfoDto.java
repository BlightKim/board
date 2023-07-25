package com.sebin.board.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sebin.board.dto.QMemberInfoDto is a Querydsl Projection type for MemberInfoDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberInfoDto extends ConstructorExpression<MemberInfoDto> {

    private static final long serialVersionUID = -206503366L;

    public QMemberInfoDto(com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<com.sebin.board.entity.Authority> authority, com.querydsl.core.types.Expression<String> profile) {
        super(MemberInfoDto.class, new Class<?>[]{String.class, com.sebin.board.entity.Authority.class, String.class}, nickname, authority, profile);
    }

}

