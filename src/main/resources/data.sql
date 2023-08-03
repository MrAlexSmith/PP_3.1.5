-- Вставка данных в таблицу roles
INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role) VALUES ('ROLE_USER');

-- Вставка данных в таблицу users
INSERT INTO users (username, password, name, surname, age) VALUES ('admin', '123', 'admin', 'admin', 35);
INSERT INTO users (username, password, name, surname, age) VALUES ('alex', '123', 'Alex', 'Smith', 50);
INSERT INTO users (username, password, name, surname, age) VALUES ('akovalev', '123', 'Александр', 'Ковалёв', 50);
INSERT INTO users (username, password, name, surname, age) VALUES ('ivan', '123', 'Иван', 'Иванов', 31);
INSERT INTO users (username, password, name, surname, age) VALUES ('piter', '123', 'Пётр', 'Петров', 26);
INSERT INTO users (username, password, name, surname, age) VALUES ('vas', '123', 'Василий', 'Васильев', 47);
INSERT INTO users (username, password, name, surname, age) VALUES ('serg', '123', 'Сергей', 'Сергеев', 39);

-- Вставка данных в таблицу user_roles
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 2);