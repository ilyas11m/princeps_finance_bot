create table if not exists transaction (
    transaction_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    type VARCHAR(30) NOT NULL,
    amount DECIMAL NOT NULL,
    date_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tg_user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE
)