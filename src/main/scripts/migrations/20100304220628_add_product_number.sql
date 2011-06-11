alter table products add column number varchar(8) not null;
alter table products add constraint number_unique unique(number);