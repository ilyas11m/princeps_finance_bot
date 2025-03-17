create table if not exists budget (
    budget_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    balance DECIMAL NOT NULL DEFAULT 0,
    limit_amount DECIMAL DEFAULT 0,
    period_begin TIMESTAMP,
    period_end TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES tg_user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE
)