create table tagable (
id serial primary key,
tag_name VARCHAR ,
creator_id INTEGER,
created_at timestamp default current_timestamp,
FOREIGN KEY (creator_id) references user(id)
);
