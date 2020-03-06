create table privilege
(
    id         serial primary key,
    name       VARCHAR UNIQUE,
    created_at timestamp default current_timestamp
)
