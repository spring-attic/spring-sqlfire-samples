package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QProduct is a Querydsl query type for QProduct
 */
@Table("PRODUCT")
@Schema("APP")
public class QProduct extends RelationalPathBase<QProduct> {

    private static final long serialVersionUID = -1365818949;

    public static final QProduct product = new QProduct("PRODUCT");

    public final StringPath category = createString("CATEGORY");

    public final StringPath descn = createString("DESCN");

    public final StringPath name = createString("NAME");

    public final StringPath productid = createString("PRODUCTID");

    public final PrimaryKey<QProduct> productPk = createPrimaryKey(productid);

    public final ForeignKey<QCategory> productcatFk = createForeignKey(category, "CATID");

    public final ForeignKey<QItem> _itemprodFk = createInvForeignKey(productid, "PRODUCTID");

    public QProduct(String variable) {
        super(QProduct.class, forVariable(variable));
    }

    public QProduct(BeanPath<? extends QProduct> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QProduct(PathMetadata<?> metadata) {
        super(QProduct.class, metadata);
    }

}

