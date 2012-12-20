package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSequence is a Querydsl query type for QSequence
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSequence extends com.mysema.query.sql.RelationalPathBase<QSequence> {

    private static final long serialVersionUID = -1332372363;

    public static final QSequence sequence = new QSequence("SEQUENCE");

    public final StringPath name = createString("NAME");

    public final NumberPath<Integer> nextid = createNumber("NEXTID", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSequence> sequencePk = createPrimaryKey(name);

    public QSequence(String variable) {
        super(QSequence.class, forVariable(variable), "APP", "SEQUENCE");
    }

    @SuppressWarnings("all")
    public QSequence(Path<? extends QSequence> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "SEQUENCE");
    }

    public QSequence(PathMetadata<?> metadata) {
        super(QSequence.class, metadata, "APP", "SEQUENCE");
    }

}

