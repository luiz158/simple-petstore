alter table orders alter column number varchar(8) not null;
alter table orders add constraint unique_order_number unique(number);