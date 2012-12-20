package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSupplier is a Querydsl query type for QSupplier
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSupplier extends com.mysema.query.sql.RelationalPathBase<QSupplier> {

    private static final long serialVersionUID = -50258304;

    public static final QSupplier supplier = new QSupplier("SUPPLIER");

    public final StringPath addr1 = createString("ADDR1");

    public final StringPath addr2 = createString("ADDR2");

    public final StringPath city = createString("CITY");

    public final StringPath name = createString("NAME");

    public final StringPath phone = createString("PHONE");

    public final StringPath state = createString("STATE");

    public final StringPath status = createString("STATUS");

    public final NumberPath<Integer> suppid = createNumber("SUPPID", Integer.class);

    public final StringPath zip = createString("ZIP");

    public final com.mysema.query.sql.PrimaryKey<QSupplier> supplierPk = createPrimaryKey(suppid);

    public final com.mysema.query.sql.ForeignKey<QItem> _itemsupFk = createInvForeignKey(suppid, "SUPPLIER");

    public QSupplier(String variable) {
        super(QSupplier.class, forVariable(variable), "APP", "SUPPLIER");
    }

    @SuppressWarnings("all")
    public QSupplier(Path<? extends QSupplier> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "SUPPLIER");
    }

    public QSupplier(PathMetadata<?> metadata) {
        super(QSupplier.class, metadata, "APP", "SUPPLIER");
    }

}

