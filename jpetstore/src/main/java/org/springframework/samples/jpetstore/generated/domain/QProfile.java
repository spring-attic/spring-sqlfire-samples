package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QProfile is a Querydsl query type for QProfile
 */
@Table("PROFILE")
@Schema("APP")
public class QProfile extends RelationalPathBase<QProfile> {

    private static final long serialVersionUID = -1365770635;

    public static final QProfile profile = new QProfile("PROFILE");

    public final NumberPath<Short> banneropt = createNumber("BANNEROPT", Short.class);

    public final StringPath favcategory = createString("FAVCATEGORY");

    public final StringPath langpref = createString("LANGPREF");

    public final NumberPath<Short> mylistopt = createNumber("MYLISTOPT", Short.class);

    public final StringPath userid = createString("USERID");

    public final PrimaryKey<QProfile> profilePk = createPrimaryKey(userid);

    public QProfile(String variable) {
        super(QProfile.class, forVariable(variable));
    }

    public QProfile(BeanPath<? extends QProfile> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QProfile(PathMetadata<?> metadata) {
        super(QProfile.class, metadata);
    }

}

