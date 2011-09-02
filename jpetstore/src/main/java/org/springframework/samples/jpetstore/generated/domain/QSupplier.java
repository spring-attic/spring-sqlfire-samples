package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QSupplier is a Querydsl query type for QSupplier
 */
@Table("SUPPLIER")
@Schema("APP")
public class QSupplier extends RelationalPathBase<QSupplier> {

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

    public final PrimaryKey<QSupplier> supplierPk = createPrimaryKey(suppid);

    public final ForeignKey<QItem> _itemsupFk = createInvForeignKey(suppid, "SUPPLIER");

    public QSupplier(String variable) {
        super(QSupplier.class, forVariable(variable));
    }

    public QSupplier(BeanPath<? extends QSupplier> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QSupplier(PathMetadata<?> metadata) {
        super(QSupplier.class, metadata);
    }

}

