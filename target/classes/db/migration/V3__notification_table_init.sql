create table if not exists notification (
    notif_id SERIAL PRIMARY KEY,
    notif_message VARCHAR(50) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tg_user (user_id) ON DELETE CASCADE
)