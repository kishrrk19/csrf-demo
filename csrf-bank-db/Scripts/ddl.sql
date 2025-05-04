DROP TABLE IF EXISTS t_users, t_accounts, t_user_sessions;

CREATE TABLE t_users(
	id INT GENERATED ALWAYS AS IDENTITY,
	username varchar(255),
	password varchar(60),
	CONSTRAINT t_users_pkey PRIMARY KEY (id),
	CONSTRAINT t_users_ukey UNIQUE (username)
);

CREATE TABLE t_accounts (
    id INT GENERATED ALWAYS AS IDENTITY,
    account_number VARCHAR(255) NOT NULL,
    balance INT NOT NULL,
    user_id INT,
    CONSTRAINT t_accounts_pkey PRIMARY KEY (id),
    CONSTRAINT t_accounts_users_fkey FOREIGN KEY (user_id) REFERENCES t_users(id)
);

CREATE TABLE t_user_sessions (
    id INT GENERATED ALWAYS AS IDENTITY,
    session_id VARCHAR(255) UNIQUE NOT NULL,
    user_id int,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT t_user_sessions_pkey PRIMARY KEY (id),
    CONSTRAINT t_user_sessions_users_fkey FOREIGN KEY (user_id) REFERENCES t_users(id)
);
