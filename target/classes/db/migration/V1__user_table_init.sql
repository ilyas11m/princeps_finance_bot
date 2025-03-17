create table if not exists tg_user (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    tg_user_id VARCHAR(50) NOT NULL,
    time_registration TIMESTAMP NOT NULL default CURRENT_TIMESTAMP
);
