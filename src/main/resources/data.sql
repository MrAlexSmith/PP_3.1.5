INSERT INTO users (email, password, name, surname, age) VALUES ('admin@email.com', '123', 'admin', 'admin', 35);
INSERT INTO users (email, password, name, surname, age) VALUES ('alex@email.com', '123', 'Alex', 'Smith', 50);
INSERT INTO users (email, password, name, surname, age) VALUES ('akovalev@email.com', '123', 'Александр', 'Ковалёв', 50);
INSERT INTO users (email, password, name, surname, age) VALUES ('ivan@email.com', '123', 'Иван', 'Иванов', 31);
INSERT INTO users (email, password, name, surname, age) VALUES ('piter@email.com', '123', 'Пётр', 'Петров', 26);
INSERT INTO users (email, password, name, surname, age) VALUES ('vas@email.com', '123', 'Василий', 'Васильев', 47);
INSERT INTO users (email, password, name, surname, age) VALUES ('serg@email.com', '123', 'Сергей', 'Сергеев', 39);

INSERT INTO roles (role, view) VALUES ('admin', 'Администратор');
INSERT INTO roles (role, view) VALUES ('user', 'Пользователь');

INSERT INTO users_roles (user, role) VALUES (1, 1);
INSERT INTO users_roles (user, role) VALUES (2, 1);
INSERT INTO users_roles (user, role) VALUES (3, 2);
INSERT INTO users_roles (user, role) VALUES (4, 2);
INSERT INTO users_roles (user, role) VALUES (5, 2);
INSERT INTO users_roles (user, role) VALUES (6, 2);
INSERT INTO users_roles (user, role) VALUES (7, 2);