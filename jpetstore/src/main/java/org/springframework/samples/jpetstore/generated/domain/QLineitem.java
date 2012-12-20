package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLineitem is a Querydsl query type for QLineitem
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QLineitem extends com.mysema.query.sql.RelationalPathBase<QLineitem> {

    private static final long serialVersionUID = -1492634181;

    public static final QLineitem lineitem = new QLineitem("LINEITEM");

    public final StringPath itemid = createString("ITEMID");

    public final NumberPath<Integer> linenum = createNumber("LINENUM", Integer.class);

    public final NumberPath<Integer> orderid = createNumber("ORDERID", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("QUANTITY", Integer.class);

    public final NumberPath<java.math.BigDecimal> unitprice = createNumber("UNITPRICE", java.math.BigDecimal.class);

    public final com.mysema.query.sql.PrimaryKey<QLineitem> lineitemPk = createPrimaryKey(linenum, orderid);

    public QLineitem(String variable) {
        super(QLineitem.class, forVariable(variable), "APP", "LINEITEM");
    }

    @SuppressWarnings("all")
    public QLineitem(Path<? extends QLineitem> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "LINEITEM");
    }

    public QLineitem(PathMetadata<?> metadata) {
        super(QLineitem.class, metadata, "APP", "LINEITEM");
    }

}

