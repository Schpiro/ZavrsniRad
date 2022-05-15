delete from user;
delete from authority;
delete from user_authority;
delete from event;

insert into authority (authority_name)
values
('ROLE_ADMIN'),
('ROLE_USER');

insert into user (username,password)
values
('admin','$2a$12$idvVNoEhmDFHg9/my0c.juxD77hEID6AX1bc0YqRJXdpuNvuJE9b2'),
('user','$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq');

insert into user_authority (user_id,authority_id)
values
(1,1),
(2,2);

insert into event (creator_id, event_details, creation_date)
values
(2,'testni event','2022-05-02'),
(2,'testni event 2','2022-05-02'),
(2,'testni event 3','2022-05-02');