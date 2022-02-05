create table DATABASECHANGELOG
(
    ID            varchar(255) not null,
    AUTHOR        varchar(255) not null,
    FILENAME      varchar(255) not null,
    DATEEXECUTED  datetime     not null,
    ORDEREXECUTED int          not null,
    EXECTYPE      varchar(10)  not null,
    MD5SUM        varchar(35)  null,
    DESCRIPTION   varchar(255) null,
    COMMENTS      varchar(255) null,
    TAG           varchar(255) null,
    LIQUIBASE     varchar(20)  null,
    CONTEXTS      varchar(255) null,
    LABELS        varchar(255) null,
    DEPLOYMENT_ID varchar(10)  null
);

create table DATABASECHANGELOGLOCK
(
    ID          int          not null
        primary key,
    LOCKED      bit          not null,
    LOCKGRANTED datetime     null,
    LOCKEDBY    varchar(255) null
);

create table ascendex
(
    id      bigint       not null,
    apikey  varchar(255) null,
    secret  varchar(255) null,
    user_id bigint       null,
    email   varchar(150) null,
    `group` bigint       null
);

create table binance
(
    id      bigint auto_increment
        primary key,
    user_id bigint       null,
    apikey  varchar(255) null,
    secret  varchar(255) null,
    email   varchar(100) null
);

create table roles
(
    id      bigint auto_increment
        primary key,
    name    varchar(100)                          not null,
    created timestamp   default CURRENT_TIMESTAMP not null,
    updated timestamp   default CURRENT_TIMESTAMP not null,
    status  varchar(25) default 'ACTIVE'          not null,
    constraint name
        unique (name)
);

create table users
(
    id         bigint auto_increment
        primary key,
    username   varchar(100)                          null,
    email      varchar(255)                          not null,
    first_name varchar(100)                          null,
    last_name  varchar(100)                          null,
    password   varchar(255)                          not null,
    created    timestamp   default CURRENT_TIMESTAMP not null,
    updated    timestamp   default CURRENT_TIMESTAMP not null,
    status     varchar(25) default 'ACTIVE'          not null,
    constraint email
        unique (email),
    constraint username
        unique (username)
);

create table user_roles
(
    user_id bigint null,
    role_id bigint null,
    constraint fk_user_roles_roles
        foreign key (role_id) references roles (id)
            on delete cascade,
    constraint fk_user_roles_user
        foreign key (user_id) references users (id)
            on delete cascade
);


