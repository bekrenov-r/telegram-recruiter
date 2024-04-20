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

drop table if exists company;
create table company
(
    id uuid primary key,
    name text,
    created_by_telegram_user text,
    foreign key (created_by_telegram_user) references telegram_user(telegram_id)
);

drop table if exists recruiter;
create table recruiter
(
    telegram_id  text primary key,
    company_id uuid,
    foreign key (telegram_id) references telegram_user(telegram_id),
    foreign key (company_id) references company(id)
);

drop table if exists offer;
create table offer
(
    id uuid primary key,
    name text,
    description text,
    salary_range_bottom int,
    salary_range_top int,
    city text,
    position text,
    work_mode text,
    created_at timestamp,
    company_id text,
    created_by_recruiter_id text,
    foreign key (created_by_recruiter_id) references recruiter(telegram_id),
    foreign key (company_id) references company(id)
);

drop table if exists offer_technology;
create table offer_technology
(
    offer_id uuid,
    technology text,
    foreign key (offer_id) references offer(id)
);