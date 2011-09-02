package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QSequence is a Querydsl query type for QSequence
 */
@Table("SEQUENCE")
@Schema("APP")
public class QSequence extends RelationalPathBase<QSequence> {

    private static final long serialVersionUID = -1332372363;

    public static final QSequence sequence = new QSequence("SEQUENCE");

    public final StringPath name = createString("NAME");

    public final NumberPath<Integer> nextid = createNumber("NEXTID", Integer.class);

    public final PrimaryKey<QSequence> sequencePk = createPrimaryKey(name);

    public QSequence(String variable) {
        super(QSequence.class, forVariable(variable));
    }

    public QSequence(BeanPath<? extends QSequence> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QSequence(PathMetadata<?> metadata) {
        super(QSequence.class, metadata);
    }

}

