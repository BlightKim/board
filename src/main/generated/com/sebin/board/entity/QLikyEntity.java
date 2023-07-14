package com.sebin.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLikyEntity is a Querydsl query type for LikyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikyEntity extends EntityPathBase<LikyEntity> {

    private static final long serialVersionUID = 925295691L;

    public static final QLikyEntity likyEntity = new QLikyEntity("likyEntity");

    public final NumberPath<Long> boardNumber = createNumber("boardNumber", Long.class);

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final StringPath likeUserNickname = createString("likeUserNickname");

    public final StringPath likeUserProfile = createString("likeUserProfile");

    public final StringPath userEmail = createString("userEmail");

    public QLikyEntity(String variable) {
        super(LikyEntity.class, forVariable(variable));
    }

    public QLikyEntity(Path<? extends LikyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikyEntity(PathMetadata metadata) {
        super(LikyEntity.class, metadata);
    }

}

