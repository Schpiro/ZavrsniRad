create table if not exists user
(
    id       identity,
    username varchar(10) unique not null,
    password nvarchar           not null
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

create table if not exists message_group
(
    id   identity,
    name nvarchar not null
);

create table if not exists user_group
(
    id       identity,
    user_id  number not null,
    group_id number not null,
    constraint fk_user_group foreign key (user_id) references user (id),
    constraint fk_group foreign key (group_id) references message_group (id)
);

create table if not exists message
(
    id                identity,
    creator_id        number   not null,
    message_body      nvarchar not null,
    create_date       datetime not null,
    parent_message_id number,
    recipient_id       number,
    recipient_group_id number,
    constraint fk_creator_id foreign key (creator_id) references user (id),
    constraint fk_parent_id foreign key (parent_message_id) references message (id),
    constraint fk_recipient_id foreign key (recipient_id) references user (id),
    constraint fk_recipient_group_id foreign key (recipient_group_id) references user_group (id)
);

create table if not exists comment
(
    id                identity,
    creator_id        number   not null,
    comment_body      nvarchar not null,
    creation_date     datetime not null,
    parent_comment_id number,
    constraint fk_comment_creator_id foreign key (creator_id) references user (id),
    constraint fk_comment_parent_id foreign key (parent_comment_id) references comment (id)
);

create table if not exists event
(
    id            identity,
    creator_id    number   not null,
    event_details nvarchar not null,
    creation_date datetime not null
);
