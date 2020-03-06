create table `user` (
	`id` serial primary key,
	`email` VARCHAR UNIQUE,
	`password` VARCHAR,
    `name` VARCHAR,
    `active` BOOL default true,
    `created_at` timestamp default current_timestamp
);
