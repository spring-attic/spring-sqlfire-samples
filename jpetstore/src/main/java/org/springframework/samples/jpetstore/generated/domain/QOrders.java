package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOrders is a Querydsl query type for QOrders
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QOrders extends com.mysema.query.sql.RelationalPathBase<QOrders> {

    private static final long serialVersionUID = 481174681;

    public static final QOrders orders = new QOrders("ORDERS");

    public final StringPath billaddr1 = createString("BILLADDR1");

    public final StringPath billaddr2 = createString("BILLADDR2");

    public final StringPath billcity = createString("BILLCITY");

    public final StringPath billcountry = createString("BILLCOUNTRY");

    public final StringPath billstate = createString("BILLSTATE");

    public final StringPath billtofirstname = createString("BILLTOFIRSTNAME");

    public final StringPath billtolastname = createString("BILLTOLASTNAME");

    public final StringPath billzip = createString("BILLZIP");

    public final StringPath cardtype = createString("CARDTYPE");

    public final StringPath courier = createString("COURIER");

    public final StringPath creditcard = createString("CREDITCARD");

    public final StringPath exprdate = createString("EXPRDATE");

    public final StringPath locale = createString("LOCALE");

    public final DatePath<java.sql.Date> orderdate = createDate("ORDERDATE", java.sql.Date.class);

    public final NumberPath<Integer> orderid = createNumber("ORDERID", Integer.class);

    public final StringPath shipaddr1 = createString("SHIPADDR1");

    public final StringPath shipaddr2 = createString("SHIPADDR2");

    public final StringPath shipcity = createString("SHIPCITY");

    public final StringPath shipcountry = createString("SHIPCOUNTRY");

    public final StringPath shipstate = createString("SHIPSTATE");

    public final StringPath shiptofirstname = createString("SHIPTOFIRSTNAME");

    public final StringPath shiptolastname = createString("SHIPTOLASTNAME");

    public final StringPath shipzip = createString("SHIPZIP");

    public final NumberPath<java.math.BigDecimal> totalprice = createNumber("TOTALPRICE", java.math.BigDecimal.class);

    public final StringPath userid = createString("USERID");

    public final com.mysema.query.sql.PrimaryKey<QOrders> ordersPk = createPrimaryKey(orderid);

    public QOrders(String variable) {
        super(QOrders.class, forVariable(variable), "APP", "ORDERS");
    }

    @SuppressWarnings("all")
    public QOrders(Path<? extends QOrders> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "ORDERS");
    }

    public QOrders(PathMetadata<?> metadata) {
        super(QOrders.class, metadata, "APP", "ORDERS");
    }

}

