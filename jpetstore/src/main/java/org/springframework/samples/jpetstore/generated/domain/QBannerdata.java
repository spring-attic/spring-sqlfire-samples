package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBannerdata is a Querydsl query type for QBannerdata
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QBannerdata extends com.mysema.query.sql.RelationalPathBase<QBannerdata> {

    private static final long serialVersionUID = -1376272246;

    public static final QBannerdata bannerdata = new QBannerdata("BANNERDATA");

    public final StringPath bannername = createString("BANNERNAME");

    public final StringPath favcategory = createString("FAVCATEGORY");

    public final com.mysema.query.sql.PrimaryKey<QBannerdata> bannerdataPk = createPrimaryKey(favcategory);

    public QBannerdata(String variable) {
        super(QBannerdata.class, forVariable(variable), "APP", "BANNERDATA");
    }

    @SuppressWarnings("all")
    public QBannerdata(Path<? extends QBannerdata> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "BANNERDATA");
    }

    public QBannerdata(PathMetadata<?> metadata) {
        super(QBannerdata.class, metadata, "APP", "BANNERDATA");
    }

}

