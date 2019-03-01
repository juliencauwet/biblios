-- we don't know how to generate root <with-no-name> (class Root) :(
create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to back;

create table app_role
(
  id serial not null
    constraint app_role_pkey
      primary key,
  name varchar(255)
);

alter table app_role owner to back;

create table app_user
(
  id integer not null
    constraint app_user_pkey
      primary key,
  email varchar(255) not null,
  first_name varchar(255),
  is_admin boolean,
  name varchar(255),
  password varchar(255) not null
);

alter table app_user owner to back;

create table app_role_app_user
(
  app_role_id integer not null
    constraint fki0rl707b9g0190knculbwhshs
      references app_role,
  app_user_id integer not null
    constraint fkftj0mdruxjyodpb4ay6hna1a2
      references app_user
);

alter table app_role_app_user owner to back;

create table book_entity
(
  id integer not null
    constraint book_entity_pkey
      primary key,
  author_first_name varchar(255),
  author_name varchar(100) not null,
  number integer not null,
  title varchar(300) not null
);

alter table book_entity owner to back;

create table borrowing
(
  id integer not null
    constraint borrowing_pkey
      primary key,
  due_return_date timestamp,
  is_extended boolean,
  return_date timestamp,
  start_date timestamp not null,
  status integer,
  app_user_id integer
    constraint fke9xahml51rph2xou979xgrf79
      references app_user,
  book_id integer
    constraint fkkvk1p4ptqn6n2y0rhhy292fl4
      references book_entity,
  borrowing_id integer
    constraint fkpqb9d934357s7lai67j3vdnb6
      references book_entity
);

alter table borrowing owner to back;

