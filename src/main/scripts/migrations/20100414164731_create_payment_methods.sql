alter table orders drop column billing_first_name;
alter table orders drop column billing_last_name;
alter table orders drop column billing_email;
alter table orders drop column credit_card_type;
alter table orders drop column credit_card_number;
alter table orders drop column credit_card_expiry_date;

create table payments (
  id bigint(20) unsigned not null auto_increment,
  billing_first_name varchar(255),
  billing_last_name varchar(255),
  billing_email varchar(255),
  card_type varchar(255),
  card_number varchar(255),
  card_expiry_date varchar(255),
  primary key(id)
) ;

alter table orders add payment_id bigint(20) unsigned;
create index index_payment_id on orders(payment_id);
alter table orders add foreign key(payment_id) references payments(id);