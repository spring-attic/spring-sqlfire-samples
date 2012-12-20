package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QInventory is a Querydsl query type for QInventory
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QInventory extends com.mysema.query.sql.RelationalPathBase<QInventory> {

    private static final long serialVersionUID = 739216168;

    public static final QInventory inventory = new QInventory("INVENTORY");

    public final StringPath itemid = createString("ITEMID");

    public final NumberPath<Integer> qty = createNumber("QTY", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QInventory> inventoryPk = createPrimaryKey(itemid);

    public QInventory(String variable) {
        super(QInventory.class, forVariable(variable), "APP", "INVENTORY");
    }

    @SuppressWarnings("all")
    public QInventory(Path<? extends QInventory> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "INVENTORY");
    }

    public QInventory(PathMetadata<?> metadata) {
        super(QInventory.class, metadata, "APP", "INVENTORY");
    }

}

