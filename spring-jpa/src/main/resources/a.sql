create database if not exists course;
use course;
create table if not exists user_data(
    id bigint auto_increment not null,
    name varchar(255) default '',
    dob date not null,
    primary key(id)
    );

create table if not exists accounts(
    account_no bigint auto_increment not null,
    type varchar(25),
    user_id bigint not null,
    primary key (account_no),
    foreign key (user_id) references user_data(id)
)