create table shop (id bigint not null IDENTITY , name varchar(255),location varchar(255), primary key (id)) engine=InnoDB;
create table appOrder (id bigint not null IDENTITY , name varchar(255), dateoforder DATE, primary key (id)) engine=InnoDB;
create table product (id bigint not null IDENTITY , name VARCHAR(255), price DECIMAL(19,2), status VARCHAR(255), description VARCHAR(255), image longblob, category VARCHAR(255), primary key (id)) engine=InnoDB;
create table product_shop (product_id bigint not null, shop_id bigint not null, primary key (product_id, shop_id)) engine=InnoDB;

alter table appOrder add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (product_id) references product (id);
alter table product_shop add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (product_id) references product (id);
alter table product_shop add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (shop_id) references shop (id);