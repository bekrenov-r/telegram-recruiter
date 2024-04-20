drop table if exists telegram_user cascade;
create table telegram_user
(
    id          serial primary key,
    telegram_id text unique,
    first_name  text,
    last_name   text,
    email text,
    telegram_username text,
    role text,
    preferred_location_voivodeship text
);

drop table if exists user_technology;
create table user_technology(
    user_id text,
    technology text
);

drop table if exists user_position;
create table user_position(
    user_id text,
    position text
);

drop table if exists user_level;
create table user_level
(
    user_id  text,
    level text
);

drop table if exists user_work_mode;
create table user_work_mode
(
    user_id  text,
    work_mode text
);