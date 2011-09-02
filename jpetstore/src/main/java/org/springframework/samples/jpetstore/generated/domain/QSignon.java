package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QSignon is a Querydsl query type for QSignon
 */
@Table("SIGNON")
@Schema("APP")
public class QSignon extends RelationalPathBase<QSignon> {

    private static final long serialVersionUID = 587477520;

    public static final QSignon signon = new QSignon("SIGNON");

    public final StringPath password = createString("PASSWORD");

    public final StringPath username = createString("USERNAME");

    public final PrimaryKey<QSignon> signonPk = createPrimaryKey(username);

    public QSignon(String variable) {
        super(QSignon.class, forVariable(variable));
    }

    public QSignon(BeanPath<? extends QSignon> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QSignon(PathMetadata<?> metadata) {
        super(QSignon.class, metadata);
    }

}

