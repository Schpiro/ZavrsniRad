create table if not exists user
(
    id       identity,
    username varchar(10) not null,
    password nvarchar    not null
);

create table if not exists authority
(
    id             identity,
    authority_name varchar(10) not null
);

create table if not exists user_authority
(
    user_id      number not null,
    authority_id number not null,
    constraint fk_user foreign key (user_id) references user (id),
    constraint fk_authority foreign key (authority_id) references authority (id)
);
