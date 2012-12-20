package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOrderstatus is a Querydsl query type for QOrderstatus
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QOrderstatus extends com.mysema.query.sql.RelationalPathBase<QOrderstatus> {

    private static final long serialVersionUID = 439243660;

    public static final QOrderstatus orderstatus = new QOrderstatus("ORDERSTATUS");

    public final NumberPath<Integer> linenum = createNumber("LINENUM", Integer.class);

    public final NumberPath<Integer> orderid = createNumber("ORDERID", Integer.class);

    public final StringPath status = createString("STATUS");

    public final DatePath<java.sql.Date> timestamp = createDate("TIMESTAMP", java.sql.Date.class);

    public final com.mysema.query.sql.PrimaryKey<QOrderstatus> orderstatusPk = createPrimaryKey(linenum, orderid);

    public QOrderstatus(String variable) {
        super(QOrderstatus.class, forVariable(variable), "APP", "ORDERSTATUS");
    }

    @SuppressWarnings("all")
    public QOrderstatus(Path<? extends QOrderstatus> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "ORDERSTATUS");
    }

    public QOrderstatus(PathMetadata<?> metadata) {
        super(QOrderstatus.class, metadata, "APP", "ORDERSTATUS");
    }

}

