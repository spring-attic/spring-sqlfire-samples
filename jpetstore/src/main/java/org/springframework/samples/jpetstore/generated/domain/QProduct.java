package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProduct is a Querydsl query type for QProduct
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QProduct extends com.mysema.query.sql.RelationalPathBase<QProduct> {

    private static final long serialVersionUID = -1365818949;

    public static final QProduct product = new QProduct("PRODUCT");

    public final StringPath category = createString("CATEGORY");

    public final StringPath descn = createString("DESCN");

    public final StringPath name = createString("NAME");

    public final StringPath productid = createString("PRODUCTID");

    public final com.mysema.query.sql.PrimaryKey<QProduct> productPk = createPrimaryKey(productid);

    public final com.mysema.query.sql.ForeignKey<QCategory> productcatFk = createForeignKey(category, "CATID");

    public final com.mysema.query.sql.ForeignKey<QItem> _itemprodFk = createInvForeignKey(productid, "PRODUCTID");

    public QProduct(String variable) {
        super(QProduct.class, forVariable(variable), "APP", "PRODUCT");
    }

    @SuppressWarnings("all")
    public QProduct(Path<? extends QProduct> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "PRODUCT");
    }

    public QProduct(PathMetadata<?> metadata) {
        super(QProduct.class, metadata, "APP", "PRODUCT");
    }

}

