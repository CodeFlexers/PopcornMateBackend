package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenreName is a Querydsl query type for GenreName
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGenreName extends EntityPathBase<GenreName> {

    private static final long serialVersionUID = 916351872L;

    public static final QGenreName genreName = new QGenreName("genreName");

    public final NumberPath<Integer> genreNameCode = createNumber("genreNameCode", Integer.class);

    public final StringPath name = createString("name");

    public QGenreName(String variable) {
        super(GenreName.class, forVariable(variable));
    }

    public QGenreName(Path<? extends GenreName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenreName(PathMetadata metadata) {
        super(GenreName.class, metadata);
    }

}

