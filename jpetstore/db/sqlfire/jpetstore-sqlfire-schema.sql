
-- identity column ordernum increment by 1 start 1000;
-- identity column linenum increment by 1 start 1000;
-- change datatype bool to smallint table: profile -- remove null declarations

DROP TABLE lineitem;
DROP TABLE orderstatus;
DROP TABLE orders;
DROP TABLE bannerdata;
DROP TABLE profile;
DROP TABLE signon;
DROP TABLE inventory;
DROP TABLE item;
DROP TABLE product;
DROP TABLE account;
DROP TABLE category;
DROP TABLE supplier;
DROP TABLE sequence;

create table supplier (
    suppid int not null,
    name varchar(80) ,
    status varchar(2) not null,
    addr1 varchar(80) ,
    addr2 varchar(80) ,
    city varchar(80) ,
    state varchar(80) ,
    zip varchar(5) ,
    phone varchar(80) ,
    constraint pk_supplier primary key (suppid)
)
REPLICATE
;

create table signon (
    --username varchar(25) not null,
    username varchar(80) not null,
    password varchar(25)  not null,
    constraint pk_signon primary key (username)
)
REPLICATE
;

create table account (
    userid varchar(80) not null,
    email varchar(80) not null,
    firstname varchar(80) not null,
    lastname varchar(80) not null,
    status varchar(2) ,
    addr1 varchar(80) not null,
    addr2 varchar(40) ,
    city varchar(80) not  null,
    state varchar(80) not null,
    zip varchar(20) not null,
    country varchar(20) not null,
    phone varchar(80) not null,
    constraint pk_account primary key (userid)
)
REPLICATE
;

create table profile (
    userid varchar(80) not null,
    langpref varchar(80) not null,
    favcategory varchar(30),
    mylistopt smallint,
    banneropt smallint,
    constraint pk_profile primary key (userid)
)
REPLICATE
;

create table bannerdata (
    favcategory varchar(80) not null,
    bannername varchar(255) ,
    constraint pk_bannerdata primary key (favcategory)
) 
REPLICATE;

create table orders (
      orderid int not null,
      userid varchar(80) not null,
      orderdate date not null,
      shipaddr1 varchar(80) not null,
      shipaddr2 varchar(80) ,
      shipcity varchar(80) not null,
      shipstate varchar(80) not null,
      shipzip varchar(20) not null,
      shipcountry varchar(20) not null,
      billaddr1 varchar(80) not null,
      billaddr2 varchar(80) ,
      billcity varchar(80) not null,
      billstate varchar(80) not null,
      billzip varchar(20) not null,
      billcountry varchar(20) not null,
      courier varchar(80) not null,
      totalprice decimal(10,2) not null,
      billtofirstname varchar(80) not null,
      billtolastname varchar(80) not null,
      shiptofirstname varchar(80) not null,
      shiptolastname varchar(80) not null,
      creditcard varchar(80) not null,
      exprdate varchar(7) not null,
      cardtype varchar(80) not null,
      locale varchar(80) not null,
      constraint pk_orders primary key (orderid)
)
PARTITION BY column(orderid)
;

create table orderstatus (
      orderid int not null,
      linenum int not null,
      timestamp date not null,
      status varchar(2) not null,
      constraint pk_orderstatus primary key (orderid, linenum)
)
PARTITION BY column(orderid) COLOCATE WITH (orders)
;

create table lineitem (
      orderid int not null,
      linenum int not null,
      itemid varchar(10) not null,
      quantity int not null,
      unitprice decimal(10,2) not null,
      constraint pk_lineitem primary key (orderid, linenum)
)
PARTITION BY column(orderid) COLOCATE WITH (orders)
;

create table category (
	catid varchar(10) not null,
	name varchar(80) ,
	descn varchar(255) ,
	constraint pk_category primary key (catid)
)
REPLICATE;

create table product (
    productid varchar(10) not null,
    category varchar(10) not null,
    name varchar(80) ,
    descn varchar(255) ,
    constraint pk_product primary key (productid),
        constraint fk_productCat foreign key (category)
        references category (catid)
)
REPLICATE;

-- create index productCat on product (category);
create index productName on product (name);

create table item (
    itemid varchar(10) not null,
    productid varchar(10) not null,
    listprice decimal(10,2) ,
    unitcost decimal(10,2) ,
    supplier int ,
    status varchar(2) ,
    attr1 varchar(80) ,
    attr2 varchar(80) ,
    attr3 varchar(80) ,
    attr4 varchar(80) ,
    attr5 varchar(80) ,
    constraint pk_item primary key (itemid),
        constraint fk_itemProd foreign key (productid)
        references product (productid),
        constraint fk_itemSup foreign key (supplier)
        references supplier (suppid)
);

-- create index itemProd on item (productid);

create table inventory (
    itemid varchar(10) not null,
    qty int not null,
    constraint pk_inventory primary key (itemid)
) 
REPLICATE
;

CREATE TABLE sequence
(
    name               varchar(30)  not null,
    nextid             int          not null,
    constraint pk_sequence primary key (name)
);
