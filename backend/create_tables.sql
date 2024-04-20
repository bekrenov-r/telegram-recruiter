drop table if exists telegram_user cascade;
create table telegram_user
(
    telegram_id text primary key,
    first_name  text,
    last_name   text,
    telegram_username text,
    role text
);

drop table if exists candidate cascade;
create table candidate
(
    telegram_id text primary key,
    email text,
    preferred_location_voivodeship text,
    foreign key (telegram_id) references telegram_user(telegram_id)
);

drop table if exists candidate_technology;
create table candidate_technology(
    candidate_id text,
    technology text
);

drop table if exists candidate_position;
create table candidate_position(
    candidate_id text,
    position text
);

drop table if exists candidate_level;
create table candidate_level
(
    candidate_id  text,
    level text
);

drop table if exists candidate_work_mode;
create table candidate_work_mode
(
    candidate_id  text,
    work_mode text
);