package org.springframework.samples.jpetstore.generated.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import com.mysema.query.sql.*;
import java.util.*;


/**
 * QAccount is a Querydsl query type for QAccount
 */
@Table("ACCOUNT")
@Schema("APP")
public class QAccount extends RelationalPathBase<QAccount> {

    private static final long serialVersionUID = 2061303545;

    public static final QAccount account = new QAccount("ACCOUNT");

    public final StringPath addr1 = createString("ADDR1");

    public final StringPath addr2 = createString("ADDR2");

    public final StringPath city = createString("CITY");

    public final StringPath country = createString("COUNTRY");

    public final StringPath email = createString("EMAIL");

    public final StringPath firstname = createString("FIRSTNAME");

    public final StringPath lastname = createString("LASTNAME");

    public final StringPath phone = createString("PHONE");

    public final StringPath state = createString("STATE");

    public final StringPath status = createString("STATUS");

    public final StringPath userid = createString("USERID");

    public final StringPath zip = createString("ZIP");

    public final PrimaryKey<QAccount> accountPk = createPrimaryKey(userid);

    public QAccount(String variable) {
        super(QAccount.class, forVariable(variable));
    }

    public QAccount(BeanPath<? extends QAccount> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QAccount(PathMetadata<?> metadata) {
        super(QAccount.class, metadata);
    }

}

