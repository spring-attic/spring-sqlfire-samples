package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QLineitem is a Querydsl query type for QLineitem
 */
@Table("LINEITEM")
@Schema("APP")
public class QLineitem extends RelationalPathBase<QLineitem> {

    private static final long serialVersionUID = -1492634181;

    public static final QLineitem lineitem = new QLineitem("LINEITEM");

    public final StringPath itemid = createString("ITEMID");

    public final NumberPath<Integer> linenum = createNumber("LINENUM", Integer.class);

    public final NumberPath<Integer> orderid = createNumber("ORDERID", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("QUANTITY", Integer.class);

    public final NumberPath<java.math.BigDecimal> unitprice = createNumber("UNITPRICE", java.math.BigDecimal.class);

    public final PrimaryKey<QLineitem> lineitemPk = createPrimaryKey(linenum, orderid);

    public QLineitem(String variable) {
        super(QLineitem.class, forVariable(variable));
    }

    public QLineitem(BeanPath<? extends QLineitem> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QLineitem(PathMetadata<?> metadata) {
        super(QLineitem.class, metadata);
    }

}

