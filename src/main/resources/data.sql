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
       ('John', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Mathew', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Claire', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Thia', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Guldar', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Carridin', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Pirin', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Evendur', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq');


insert into user_authority (user_id, authority_id)
values (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2);

insert into event (creator_id, title, location, date, event_details, creation_date)
values (2, '1', 'RIJEKA', '2022-05-02', 'testni event', '2022-05-02'),
       (2, '2', 'ZAGREB', '2022-05-02', 'testni event 2', '2022-05-02'),
       (2, '3', 'LOVRAN', '2022-05-02', 'testni event 3', '2022-05-02');

insert into message_group (name)
values ('prva testna grupa'),
       ('druga testna grupa');

insert into user_group (user_id, group_id)
values (1, 1),
       (2, 2),
       (1, 2),
       (2, 1);

insert into message (creator_id, message_body, create_date, recipient_id)
values (1, 'test message', '2022-01-01', 2),
       (2, 'test message', '2022-01-01', 1),
       (1, 'old test message', '2021-01-01', 2),
       (1, 'old test message', '2021-01-01', 3),
       (1, 'old test message', '2021-01-01', 2),
       (1, 'old test message', '2021-01-01', 3),
       (3, 'old test message', '2021-01-01', 2),
       (3, 'old test message', '2021-01-01', 1),
       (3, 'old test message', '2021-01-01', 2),
       (3, 'old test message', '2021-01-01', 2);

insert into message (creator_id, message_body, create_date, recipient_group_id)
values (1, 'test group message', '2022-01-01', 1),
       (2, 'test group message od usera', '2022-01-01', 1),
       (1, '2. test group message', '2022-01-01', 2),
       (3, '2. test group message od usera', '2022-01-01', 2),
       (2, 'test group message', '2021-01-01', 1),
       (2, '2. test group message', '2021-01-01', 2),
       (3, '2. test group message od usera', '2021-01-01', 2);;

insert into comment (event_id, creator_id, comment_body, creation_date)
values (1, 1, 'o da ovo je komentar tuturutu', '2022-01-01'),
       (2, 2, 'pipurutu', '2022-01-01'),
       (3, 3, 'tuUUUUUUUUUU', '2022-01-01')