package com.sebin.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPopularSearchEntity is a Querydsl query type for PopularSearchEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPopularSearchEntity extends EntityPathBase<PopularSearchEntity> {

    private static final long serialVersionUID = -1968457497L;

    public static final QPopularSearchEntity popularSearchEntity = new QPopularSearchEntity("popularSearchEntity");

    public final StringPath popular_team = createString("popular_team");

    public final NumberPath<Integer> popularSearchCount = createNumber("popularSearchCount", Integer.class);

    public QPopularSearchEntity(String variable) {
        super(PopularSearchEntity.class, forVariable(variable));
    }

    public QPopularSearchEntity(Path<? extends PopularSearchEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPopularSearchEntity(PathMetadata metadata) {
        super(PopularSearchEntity.class, metadata);
    }

}

