create table role_privilege
(
    id           serial primary key,
    privilege_id INTEGER,
    role_id      INTEGER,
    created_at   timestamp default current_timestamp,
    FOREIGN KEY (privilege_id) references privilege (id),
    FOREIGN KEY (role_id) references role (id)
)
