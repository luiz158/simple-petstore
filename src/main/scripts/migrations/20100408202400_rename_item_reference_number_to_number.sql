alter table items alter column reference_number rename to number;
alter table items alter column number varchar(8) not null;
alter table items add constraint unique_item_number unique(number);