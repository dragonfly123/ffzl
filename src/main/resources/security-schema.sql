    create table users(
        username varchar(256) not null primary key,
        password varchar(256) not null,
        enabled boolean not null,
        salt VARCHAR(25) not null
    );

    create table authorities (
        username varchar(256) not null,
        authority varchar(256) not null,
        constraint fk_authorities_users foreign key(username) references users(username)
    );
    create unique index ix_auth_username on authorities (username,authority);

--     INSERT INTO users(username, password, enabled) VALUES ('admin','admin',true);
--     INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_USER');
--     INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_ADMIN');
--     INSERT INTO  users(username, password, enabled) VALUES ('guest','guest',TRUE );
--     INSERT INTO authorities(username, authority) VALUES ('guest','ROLE_USER');


    CREATE TABLE groups (
      id  int,
      group_name VARCHAR(256)
    );

    INSERT INTO groups(id,group_name) VALUES (1,'Users');
    INSERT INTO groups(id,group_name) VALUES (2,'Administrators');

    CREATE TABLE group_authorities(
        group_id  int,
        authority VARCHAR(255)
    );

    CREATE TABLE group_members(
        group_id int,
        username  varchar(255)
    );
    INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' from groups WHERE group_name =  'Users';
    INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' from groups WHERE group_name =  'Administrators';
    INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_ADMIN' from groups WHERE group_name =  'Administrators';


    INSERT INTO users(username, password, enabled,salt) VALUES ('admin','admin',true,cast(rand()*1000000000 as VARCHAR(255) ));
    INSERT INTO users(username, password, enabled,salt) VALUES ('guest','guest',true,cast(rand()*1000000000 as VARCHAR(255) ));

    INSERT INTO group_members(group_id, username) SELECT id,'guest' FROM groups WHERE group_name = 'Users';
    INSERT INTO group_members(group_id, username) SELECT id,'admin' FROM groups WHERE group_name = 'Administrators';

    create table persistent_logins(
    username varchar(50) not null,
    series varchar(64) PRIMARY key,
    token varchar(64) not null,
    last_used TIMESTAMP not null
    );

