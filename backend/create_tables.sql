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
    preferred_location_voivodeship text,
    foreign key (telegram_id) references telegram_user(telegram_id)
);

drop table if exists candidate_technology cascade;
create table candidate_technology(
    candidate_id text,
    technology text
);

drop table if exists candidate_position cascade;
create table candidate_position(
    candidate_id text,
    position text
);

drop table if exists candidate_level cascade;
create table candidate_level
(
    candidate_id  text,
    level text
);

drop table if exists candidate_work_mode cascade;
create table candidate_work_mode
(
    candidate_id  text,
    work_mode text
);

drop table if exists company cascade;
create table company
(
    id uuid primary key,
    name text,
    created_by_telegram_user text,
    foreign key (created_by_telegram_user) references telegram_user(telegram_id)
);

drop table if exists recruiter cascade;
create table recruiter
(
    telegram_id  text primary key,
    company_id uuid,
    foreign key (telegram_id) references telegram_user(telegram_id),
    foreign key (company_id) references company(id)
);

drop table if exists offer cascade;
create table offer
(
    id uuid primary key,
    name text,
    description text,
    city text,
    position text,
    work_mode text,
    level text,
    created_at timestamp,
    company_id uuid,
    created_by_recruiter_id text,
    foreign key (created_by_recruiter_id) references recruiter(telegram_id),
    foreign key (company_id) references company(id)
);

drop table if exists offer_technology cascade;
create table offer_technology
(
    offer_id uuid,
    technology text,
    foreign key (offer_id) references offer(id)
);

insert into telegram_user(telegram_id, first_name, last_name, telegram_username, role) values('1234', 'John', 'Doe', null, 'MODERATOR');
insert into telegram_user(telegram_id, first_name, last_name, telegram_username, role) values('12345', 'Jane', 'Doe', null, 'RECRUITER');
insert into company(id, name, created_by_telegram_user) values('c29700e9-4f60-45de-910f-fdf2086e5eb7', 'Space Fincher', '1234');
insert into recruiter(telegram_id, company_id) values ('12345', 'c29700e9-4f60-45de-910f-fdf2086e5eb7');
insert into offer(id, name, description, city, position, work_mode, level, created_at, company_id, created_by_recruiter_id)
values
    (gen_random_uuid(), 'Junior Software Engineer', 'Something', 'Warszawa', 'BACKEND_DEV', 'OFFICE', 'JUNIOR', current_timestamp, 'c29700e9-4f60-45de-910f-fdf2086e5eb7', '12345'),
    (gen_random_uuid(), 'Senior Frontend Developer', 'Something', 'Warszawa', 'FRONTEND_DEV', 'REMOTE', 'SENIOR', current_timestamp, 'c29700e9-4f60-45de-910f-fdf2086e5eb7', '12345'),
    (gen_random_uuid(), 'Regular Full Stack Developer', 'Something', 'Warszawa', 'FULLSTACK_DEV', 'HYBRID', 'REGULAR', current_timestamp, 'c29700e9-4f60-45de-910f-fdf2086e5eb7', '12345'),
    (gen_random_uuid(), 'Intern Project Manager', 'Something', 'Warszawa', 'PROJECT_MANAGER', 'REMOTE', 'INTERN', current_timestamp, 'c29700e9-4f60-45de-910f-fdf2086e5eb7', '12345');