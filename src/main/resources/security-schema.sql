    create table users(
        username varchar(256) not null primary key,
        password varchar(256) not null,
        enabled boolean not null
    );

    create table authorities (
        username varchar(256) not null,
        authority varchar(256) not null,
        constraint fk_authorities_users foreign key(username) references users(username)
    );
    create unique index ix_auth_username on authorities (username,authority);

    INSERT INTO users(username, password, enabled) VALUES ('admin','admin',true);
    INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_USER');
    INSERT INTO authorities(username, authority) VALUES ('admin','ROLE_ADMIN');
    INSERT INTO  users(username, password, enabled) VALUES ('guest','guest',TRUE );
    INSERT INTO authorities(username, authority) VALUES ('guest','ROLE_USER');
    COMMIT ;
