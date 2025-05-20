package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeletedUser is a Querydsl query type for DeletedUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeletedUser extends EntityPathBase<DeletedUser> {

    private static final long serialVersionUID = 468179926L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeletedUser deletedUser = new QDeletedUser("deletedUser");

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath reason = createString("reason");

    public final QUser user;

    public final NumberPath<Integer> userCode = createNumber("userCode", Integer.class);

    public QDeletedUser(String variable) {
        this(DeletedUser.class, forVariable(variable), INITS);
    }

    public QDeletedUser(Path<? extends DeletedUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeletedUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeletedUser(PathMetadata metadata, PathInits inits) {
        this(DeletedUser.class, metadata, inits);
    }

    public QDeletedUser(Class<? extends DeletedUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

