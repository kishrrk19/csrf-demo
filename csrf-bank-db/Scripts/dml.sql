SELECT * FROM t_users;
SELECT * FROM t_accounts;
SELECT * FROM t_user_sessions;

INSERT INTO t_users (username, password) VALUES ('Sabrina', 'Simplon'), ('Rika', 'Bonjour');

INSERT INTO t_accounts (account_number, balance, user_id) VALUES ('FR1234', 10000, 1);
INSERT INTO t_accounts (account_number, balance, user_id) VALUES ('FR5678', 0, 2);