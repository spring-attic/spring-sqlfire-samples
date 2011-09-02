package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QOrderstatus is a Querydsl query type for QOrderstatus
 */
@Table("ORDERSTATUS")
@Schema("APP")
public class QOrderstatus extends RelationalPathBase<QOrderstatus> {

    private static final long serialVersionUID = 439243660;

    public static final QOrderstatus orderstatus = new QOrderstatus("ORDERSTATUS");

    public final NumberPath<Integer> linenum = createNumber("LINENUM", Integer.class);

    public final NumberPath<Integer> orderid = createNumber("ORDERID", Integer.class);

    public final StringPath status = createString("STATUS");

    public final DatePath<java.sql.Date> timestamp = createDate("TIMESTAMP", java.sql.Date.class);

    public final PrimaryKey<QOrderstatus> orderstatusPk = createPrimaryKey(linenum, orderid);

    public QOrderstatus(String variable) {
        super(QOrderstatus.class, forVariable(variable));
    }

    public QOrderstatus(BeanPath<? extends QOrderstatus> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QOrderstatus(PathMetadata<?> metadata) {
        super(QOrderstatus.class, metadata);
    }

}

