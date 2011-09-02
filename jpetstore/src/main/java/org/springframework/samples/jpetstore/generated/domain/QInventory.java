package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QInventory is a Querydsl query type for QInventory
 */
@Table("INVENTORY")
@Schema("APP")
public class QInventory extends RelationalPathBase<QInventory> {

    private static final long serialVersionUID = 739216168;

    public static final QInventory inventory = new QInventory("INVENTORY");

    public final StringPath itemid = createString("ITEMID");

    public final NumberPath<Integer> qty = createNumber("QTY", Integer.class);

    public final PrimaryKey<QInventory> inventoryPk = createPrimaryKey(itemid);

    public QInventory(String variable) {
        super(QInventory.class, forVariable(variable));
    }

    public QInventory(BeanPath<? extends QInventory> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QInventory(PathMetadata<?> metadata) {
        super(QInventory.class, metadata);
    }

}

