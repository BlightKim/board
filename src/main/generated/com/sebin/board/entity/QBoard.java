package com.sebin.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -619182103L;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Integer> boardCommentCount = createNumber("boardCommentCount", Integer.class);

    public final StringPath boardContent = createString("boardContent");

    public final StringPath boardFile = createString("boardFile");

    public final StringPath boardImage = createString("boardImage");

    public final NumberPath<Integer> boardLikeCount = createNumber("boardLikeCount", Integer.class);

    public final NumberPath<Long> boardNumber = createNumber("boardNumber", Long.class);

    public final StringPath boardTitle = createString("boardTitle");

    public final StringPath boardVideo = createString("boardVideo");

    public final StringPath boardWriteDate = createString("boardWriteDate");

    public final StringPath boardWriterNickname = createString("boardWriterNickname");

    public final NumberPath<Long> boardWriterNumber = createNumber("boardWriterNumber", Long.class);

    public final StringPath boardWriterProfile = createString("boardWriterProfile");

    public final NumberPath<Long> clickCount = createNumber("clickCount", Long.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

