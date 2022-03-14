create table history
(
    histories_id  UUID not null,
    message       varchar(255),
    search        varchar(255),
    user_favorite varchar(255),
    user_id       UUID not null,
    primary key (histories_id)
);
create table opportunity
(
    opportunity_id     UUID         not null,
    available_position integer      not null,
    calendar           timestamp    not null,
    description        varchar(255),
    external_key       UUID         not null,
    name               varchar(255) not null,
    needed_skill       varchar(255),
    title              varchar(255),
    organization_id    UUID         not null,
    primary key (opportunity_id)
);
create table organization
(
    organization_id UUID         not null,
    about           varchar(255) not null,
    created         timestamp    not null,
    external_key    UUID         not null,
    mission         varchar(255) not null,
    name            varchar(255) not null,
    owner_id        UUID         not null,
    primary key (organization_id)
);
create table user_profile
(
    user_profile_id UUID         not null,
    created         timestamp    not null,
    display_name    varchar(50)  not null,
    email           varchar(255),
    external_key    UUID         not null,
    location        varchar(255),
    name            varchar(255) not null,
    oauth_key       varchar(30)  not null,
    phone_number    varchar(255),
    primary key (user_profile_id)
);
