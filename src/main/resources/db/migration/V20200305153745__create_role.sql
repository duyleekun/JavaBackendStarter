create table role
(
    id          INTEGER primary key auto_increment,
    name        VARCHAR UNIQUE,
    permissions VARCHAR,
    created_at  timestamp default current_timestamp
)
