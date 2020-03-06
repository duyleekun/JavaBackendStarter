create table post (
id INTEGER primary key auto_increment ,
title VARCHAR ,
content VARCHAR ,
poster_id INTEGER,
created_at timestamp default current_timestamp,
FOREIGN KEY (poster_id) references user(id)
);
