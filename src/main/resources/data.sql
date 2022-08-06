delete
from user;
delete
from authority;
delete
from user_authority;
delete
from event;

insert into authority (authority_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into user (username, password)
values ('admin', '$2a$12$idvVNoEhmDFHg9/my0c.juxD77hEID6AX1bc0YqRJXdpuNvuJE9b2'),
       ('user', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('user2', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq');


insert into user_authority (user_id, authority_id)
values (1, 1),
       (2, 2),
       (3, 2);

insert into event (creator_id, event_details, creation_date)
values (2, 'testni event', '2022-05-02'),
       (2, 'testni event 2', '2022-05-02'),
       (2, 'testni event 3', '2022-05-02');

insert into message_group (name)
values ('prva testna grupa'),
       ('druga testna grupa');

insert into user_group (user_id, group_id)
values (1, 1),
       (2, 2);

insert into message (id, creator_id, message_body, create_date, recipient_id)
values (1, 1, 'test message', '2022-01-01', 1);

insert into message (id, creator_id, message_body, create_date, recipient_group_id)
values (2, 1, 'test group message', '2022-01-01', 1);

insert into comment (event_id,creator_id,comment_body,creation_date)
values (1,1,'o da ovo je komentar tuturutu', '2022-01-01'),
       (2,2,'pipurutu', '2022-01-01'),
       (3,3,'tuUUUUUUUUUU', '2022-01-01')