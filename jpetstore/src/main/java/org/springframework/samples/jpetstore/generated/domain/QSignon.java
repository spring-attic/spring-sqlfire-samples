package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSignon is a Querydsl query type for QSignon
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSignon extends com.mysema.query.sql.RelationalPathBase<QSignon> {

    private static final long serialVersionUID = 587477520;

    public static final QSignon signon = new QSignon("SIGNON");

    public final StringPath password = createString("PASSWORD");

    public final StringPath username = createString("USERNAME");

    public final com.mysema.query.sql.PrimaryKey<QSignon> signonPk = createPrimaryKey(username);

    public QSignon(String variable) {
        super(QSignon.class, forVariable(variable), "APP", "SIGNON");
    }

    @SuppressWarnings("all")
    public QSignon(Path<? extends QSignon> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "SIGNON");
    }

    public QSignon(PathMetadata<?> metadata) {
        super(QSignon.class, metadata, "APP", "SIGNON");
    }

}

