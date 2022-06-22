CREATE TABLE account_book
(
    account_book_id BIGINT NOT NULL AUTO_INCREMENT,
    created_at      DATETIME,
    updated_at      DATETIME,
    active          TINYINT(1) DEFAULT 1,
    memo            VARCHAR(255),
    money           BIGINT NOT NULL,
    user_id         BIGINT,
    PRIMARY KEY (account_book_id)
);


CREATE TABLE user
(
    user_id    BIGINT NOT NULL AUTO_INCREMENT,
    created_at timestamp,
    updated_at timestamp,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);