create table items (
  id bigint(20) unsigned not null auto_increment,
  reference_number varchar(8) not null unique,
  product_id bigint(20) unsigned not null,
  primary key(id),
  foreign key(product_id) references products(id)
) ;

create index index_product_id on items(product_id);