create table post_tag (
id INTEGER primary key auto_increment ,
post_id INTEGER ,
tag_id INTEGER,
created_at timestamp default current_timestamp,
FOREIGN KEY (post_id) references post(id) ON DELETE CASCADE,
FOREIGN KEY (tag_id) references tagable(id) ON DELETE CASCADE


);

create index post_tag_post_id_and_tag_id on post_tag (post_id, tag_id);
