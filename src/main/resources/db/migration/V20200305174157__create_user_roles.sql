create table user_role
(
    id         serial primary key,
    user_id    INTEGER,
    role_id    INTEGER,
    created_at timestamp default current_timestamp,
    FOREIGN KEY (user_id) references user (id),
    FOREIGN KEY (role_id) references role (id)
)
