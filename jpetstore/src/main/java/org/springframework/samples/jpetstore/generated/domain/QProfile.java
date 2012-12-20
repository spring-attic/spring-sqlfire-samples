package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProfile is a Querydsl query type for QProfile
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QProfile extends com.mysema.query.sql.RelationalPathBase<QProfile> {

    private static final long serialVersionUID = -1365770635;

    public static final QProfile profile = new QProfile("PROFILE");

    public final NumberPath<Short> banneropt = createNumber("BANNEROPT", Short.class);

    public final StringPath favcategory = createString("FAVCATEGORY");

    public final StringPath langpref = createString("LANGPREF");

    public final NumberPath<Short> mylistopt = createNumber("MYLISTOPT", Short.class);

    public final StringPath userid = createString("USERID");

    public final com.mysema.query.sql.PrimaryKey<QProfile> profilePk = createPrimaryKey(userid);

    public QProfile(String variable) {
        super(QProfile.class, forVariable(variable), "APP", "PROFILE");
    }

    @SuppressWarnings("all")
    public QProfile(Path<? extends QProfile> path) {
        super((Class)path.getType(), path.getMetadata(), "APP", "PROFILE");
    }

    public QProfile(PathMetadata<?> metadata) {
        super(QProfile.class, metadata, "APP", "PROFILE");
    }

}

