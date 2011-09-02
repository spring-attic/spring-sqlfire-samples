package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QItem is a Querydsl query type for QItem
 */
@Table("ITEM")
@Schema("APP")
public class QItem extends RelationalPathBase<QItem> {

    private static final long serialVersionUID = -1184032313;

    public static final QItem item = new QItem("ITEM");

    public final StringPath attr1 = createString("ATTR1");

    public final StringPath attr2 = createString("ATTR2");

    public final StringPath attr3 = createString("ATTR3");

    public final StringPath attr4 = createString("ATTR4");

    public final StringPath attr5 = createString("ATTR5");

    public final StringPath itemid = createString("ITEMID");

    public final NumberPath<java.math.BigDecimal> listprice = createNumber("LISTPRICE", java.math.BigDecimal.class);

    public final StringPath productid = createString("PRODUCTID");

    public final StringPath status = createString("STATUS");

    public final NumberPath<Integer> supplier = createNumber("SUPPLIER", Integer.class);

    public final NumberPath<java.math.BigDecimal> unitcost = createNumber("UNITCOST", java.math.BigDecimal.class);

    public final PrimaryKey<QItem> itemPk = createPrimaryKey(itemid);

    public final ForeignKey<QSupplier> itemsupFk = createForeignKey(supplier, "SUPPID");

    public final ForeignKey<QProduct> itemprodFk = createForeignKey(productid, "PRODUCTID");

    public QItem(String variable) {
        super(QItem.class, forVariable(variable));
    }

    public QItem(BeanPath<? extends QItem> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QItem(PathMetadata<?> metadata) {
        super(QItem.class, metadata);
    }

}

