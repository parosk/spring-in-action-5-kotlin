-- Ingredient : Holds ingredient information
-- Taco : Holds information about a taco design
-- Taco_Ingredients : one row means one ingredient in one taco
-- Taco_Order : order details
-- Taco_Order_Tacos : one row means one taco in one order

create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  created_at timestamp not null
);

create table if not exists Taco_Ingredients (
  taco_id bigint not null,
  ingredients_id varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients
    add foreign key (ingredients_id) references Ingredient(id);

create table if not exists Taco_Order (
	id identity,
	delivery_name varchar(50) not null,
	street varchar(50) not null,
	city varchar(50) not null,
	state varchar(2) not null,
	zip varchar(10) not null,
	cc_number varchar(16) not null,
	cc_expiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Taco_Order_Tacos (
	order_id bigint not null,
	tacos_id bigint not null
);

alter table Taco_Order_Tacos
    add foreign key (order_id) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (tacos_id) references Taco(id);