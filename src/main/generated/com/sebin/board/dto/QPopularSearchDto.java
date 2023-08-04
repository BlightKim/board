package com.sebin.board.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sebin.board.dto.QPopularSearchDto is a Querydsl Projection type for PopularSearchDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPopularSearchDto extends ConstructorExpression<PopularSearchDto> {

    private static final long serialVersionUID = -191326853L;

    public QPopularSearchDto(com.querydsl.core.types.Expression<String> popularTerm, com.querydsl.core.types.Expression<Integer> popularSearchCount) {
        super(PopularSearchDto.class, new Class<?>[]{String.class, int.class}, popularTerm, popularSearchCount);
    }

}

