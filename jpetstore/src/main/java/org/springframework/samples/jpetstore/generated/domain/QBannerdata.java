package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QBannerdata is a Querydsl query type for QBannerdata
 */
@Table("BANNERDATA")
@Schema("APP")
public class QBannerdata extends RelationalPathBase<QBannerdata> {

    private static final long serialVersionUID = -1376272246;

    public static final QBannerdata bannerdata = new QBannerdata("BANNERDATA");

    public final StringPath bannername = createString("BANNERNAME");

    public final StringPath favcategory = createString("FAVCATEGORY");

    public final PrimaryKey<QBannerdata> bannerdataPk = createPrimaryKey(favcategory);

    public QBannerdata(String variable) {
        super(QBannerdata.class, forVariable(variable));
    }

    public QBannerdata(BeanPath<? extends QBannerdata> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QBannerdata(PathMetadata<?> metadata) {
        super(QBannerdata.class, metadata);
    }

}

