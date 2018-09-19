CREATE SEQUENCE hibernate_sequence;

CREATE TABLE app_user
(
  id         INTEGER      NOT NULL
    CONSTRAINT app_user_pkey
    PRIMARY KEY,
  email      VARCHAR(255) NOT NULL,
  first_name VARCHAR(255),
  is_admin   BOOLEAN,
  name       VARCHAR(255),
  password   VARCHAR(30)  NOT NULL
);

CREATE TABLE book_entity
(
  id                INTEGER      NOT NULL
    CONSTRAINT book_entity_pkey
    PRIMARY KEY,
  author_first_name VARCHAR(255),
  author_name       VARCHAR(100) NOT NULL,
  number            INTEGER      NOT NULL,
  status            INTEGER,
  title             VARCHAR(300) NOT NULL
);

CREATE TABLE borrowing
(
  id              INTEGER   NOT NULL
    CONSTRAINT borrowing_pkey
    PRIMARY KEY,
  due_return_date TIMESTAMP,
  is_extended     BOOLEAN,
  return_date     TIMESTAMP,
  start_date      TIMESTAMP NOT NULL,
  app_user_id     INTEGER
    CONSTRAINT fke9xahml51rph2xou979xgrf79
    REFERENCES app_user,
  book_id         INTEGER
    CONSTRAINT fkkvk1p4ptqn6n2y0rhhy292fl4
    REFERENCES book_entity,
  borrowing_id    INTEGER
    CONSTRAINT fkpqb9d934357s7lai67j3vdnb6
    REFERENCES book_entity
);


