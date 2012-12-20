package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QItem is a Querydsl query type for QItem
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QItem extends com.mysema.query.sql.RelationalPathBase<QItem> {

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

    public final com.mysema.query.sql.PrimaryKey<QItem> itemPk = createPrimaryKey(itemid);

    public final com.mysema.query.sql.ForeignKey<QSupplier> itemsupFk = createForeignKey(supplier, "SUPPID");

    public final com.mysema.query.sql.ForeignKey<QProduct> itemprodFk = createForeignKey(productid, "PRODUCTID");

    public QItem(String variable) {
        super(QItem.class, forVariable(variable), "APP", "ITEM");
    }

    @SuppressWarnings("all")
    public QItem(Path<? extends QItem> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "ITEM");
    }

    public QItem(PathMetadata<?> metadata) {
        super(QItem.class, metadata, "APP", "ITEM");
    }

}

