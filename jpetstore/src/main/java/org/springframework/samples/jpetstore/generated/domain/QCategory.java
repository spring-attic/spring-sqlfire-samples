package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCategory is a Querydsl query type for QCategory
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QCategory extends com.mysema.query.sql.RelationalPathBase<QCategory> {

    private static final long serialVersionUID = 1663558066;

    public static final QCategory category = new QCategory("CATEGORY");

    public final StringPath catid = createString("CATID");

    public final StringPath descn = createString("DESCN");

    public final StringPath name = createString("NAME");

    public final com.mysema.query.sql.PrimaryKey<QCategory> categoryPk = createPrimaryKey(catid);

    public final com.mysema.query.sql.ForeignKey<QProduct> _productcatFk = createInvForeignKey(catid, "CATEGORY");

    public QCategory(String variable) {
        super(QCategory.class, forVariable(variable), "APP", "CATEGORY");
    }

    @SuppressWarnings("all")
    public QCategory(Path<? extends QCategory> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "CATEGORY");
    }

    public QCategory(PathMetadata<?> metadata) {
        super(QCategory.class, metadata, "APP", "CATEGORY");
    }

}

