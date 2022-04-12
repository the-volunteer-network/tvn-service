create table opportunity
(
    opportunity_id  UUID         not null,
    created         timestamp    not null,
    description     varchar(255),
    external_key    UUID         not null,
    name            varchar(255) not null,
    needed_skill    varchar(255),
    title           varchar(255),
    organization_id UUID         not null,
    primary key (opportunity_id)
);
create table organization
(
    organization_id UUID         not null,
    about           varchar(255) not null,
    created         timestamp    not null,
    external_key    UUID         not null,
    latitude        double       not null,
    longitude       double       not null,
    mission         varchar(255) not null,
    name            varchar(255) not null,
    owner_id        UUID         not null,
    primary key (organization_id)
);
create table organization_volunteer
(
    organization_id UUID not null,
    user_id         UUID not null,
    primary key (organization_id, user_id)
);
create table user_favorite
(
    user_id         UUID not null,
    organization_id UUID not null,
    primary key (user_id, organization_id)
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
alter table opportunity
    add constraint UK_q0d701nlowkthnebng8gu8gxf unique (external_key);
alter table opportunity
    add constraint UK_kadvxqb91cj5r2ojbi5xpbfgu unique (name);
alter table organization
    add constraint UK_qcok43hxi47578qc4yhde8uu5 unique (external_key);
alter table organization
    add constraint UK_8j5y8ipk73yx2joy9yr653c9t unique (name);
alter table user_profile
    add constraint UK_j35xlx80xoi2sb176qdrtoy69 unique (display_name);
alter table user_profile
    add constraint UK_tcks72p02h4dp13cbhxne17ad unique (email);
alter table user_profile
    add constraint UK_22o8v4jg08yk7piojnowil30o unique (external_key);
alter table user_profile
    add constraint UK_hwj1idblnqhrrmj7w2aq9sncx unique (name);
alter table user_profile
    add constraint UK_6f815wi5o4jq8p1q1w63o4mhd unique (oauth_key);
alter table user_profile
    add constraint UK_dd0g7xm8e4gtak3ka2h89clyh unique (phone_number);
alter table opportunity
    add constraint FKchv5dr34ljqds5u1i0dw80ace foreign key (organization_id) references organization;
alter table organization
    add constraint FKdv9r2jouoamx5br23rq7a876s foreign key (owner_id) references user_profile;
alter table organization_volunteer
    add constraint FK28p6ne63n4vtq8fxsctsrilr1 foreign key (user_id) references user_profile;
alter table organization_volunteer
    add constraint FKqigbvb41typpwe9s4m2uyws8m foreign key (organization_id) references organization;
alter table user_favorite
    add constraint FKqnweyjjj9b19l9iqqem2ykiel foreign key (organization_id) references organization;
alter table user_favorite
    add constraint FK5hfsaji8a30vg63mh898jcd7v foreign key (user_id) references user_profile;
create table opportunity (opportunity_id UUID not null, created timestamp not null, description varchar(255), external_key UUID not null, name varchar(255) not null, needed_skill varchar(255), title varchar(255), organization_id UUID not null, primary key (opportunity_id));
create table organization (organization_id UUID not null, about varchar(255) not null, created timestamp not null, external_key UUID not null, latitude double not null, longitude double not null, mission varchar(255) not null, name varchar(255) not null, owner_id UUID not null, primary key (organization_id));
create table organization_volunteer (organization_id UUID not null, user_id UUID not null, primary key (organization_id, user_id));
create table user_favorite (user_id UUID not null, organization_id UUID not null, primary key (user_id, organization_id));
create table user_profile (user_profile_id UUID not null, created timestamp not null, display_name varchar(50) not null, email varchar(255), external_key UUID not null, location varchar(255), name varchar(255) not null, oauth_key varchar(30) not null, phone_number varchar(255), primary key (user_profile_id));
alter table opportunity add constraint UK_q0d701nlowkthnebng8gu8gxf unique (external_key);
alter table opportunity add constraint UK_kadvxqb91cj5r2ojbi5xpbfgu unique (name);
alter table organization add constraint UK_qcok43hxi47578qc4yhde8uu5 unique (external_key);
alter table organization add constraint UK_8j5y8ipk73yx2joy9yr653c9t unique (name);
alter table user_profile add constraint UK_j35xlx80xoi2sb176qdrtoy69 unique (display_name);
alter table user_profile add constraint UK_tcks72p02h4dp13cbhxne17ad unique (email);
alter table user_profile add constraint UK_22o8v4jg08yk7piojnowil30o unique (external_key);
alter table user_profile add constraint UK_hwj1idblnqhrrmj7w2aq9sncx unique (name);
alter table user_profile add constraint UK_6f815wi5o4jq8p1q1w63o4mhd unique (oauth_key);
alter table user_profile add constraint UK_dd0g7xm8e4gtak3ka2h89clyh unique (phone_number);
alter table opportunity add constraint FKchv5dr34ljqds5u1i0dw80ace foreign key (organization_id) references organization;
alter table organization add constraint FKdv9r2jouoamx5br23rq7a876s foreign key (owner_id) references user_profile;
alter table organization_volunteer add constraint FK28p6ne63n4vtq8fxsctsrilr1 foreign key (user_id) references user_profile;
alter table organization_volunteer add constraint FKqigbvb41typpwe9s4m2uyws8m foreign key (organization_id) references organization;
alter table user_favorite add constraint FKqnweyjjj9b19l9iqqem2ykiel foreign key (organization_id) references organization;
alter table user_favorite add constraint FK5hfsaji8a30vg63mh898jcd7v foreign key (user_id) references user_profile;
