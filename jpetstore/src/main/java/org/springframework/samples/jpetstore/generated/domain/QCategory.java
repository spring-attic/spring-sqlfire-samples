package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QCategory is a Querydsl query type for QCategory
 */
@Table("CATEGORY")
@Schema("APP")
public class QCategory extends RelationalPathBase<QCategory> {

    private static final long serialVersionUID = 1663558066;

    public static final QCategory category = new QCategory("CATEGORY");

    public final StringPath catid = createString("CATID");

    public final StringPath descn = createString("DESCN");

    public final StringPath name = createString("NAME");

    public final PrimaryKey<QCategory> categoryPk = createPrimaryKey(catid);

    public final ForeignKey<QProduct> _productcatFk = createInvForeignKey(catid, "CATEGORY");

    public QCategory(String variable) {
        super(QCategory.class, forVariable(variable));
    }

    public QCategory(BeanPath<? extends QCategory> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QCategory(PathMetadata<?> metadata) {
        super(QCategory.class, metadata);
    }

}

