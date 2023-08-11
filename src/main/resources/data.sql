-- Вставка данных в таблицу roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

-- Вставка данных в таблицу users
INSERT INTO users (username, password, name, surname, age, enabled)
        VALUES ('admin',
                '$2a$12$8qy7ZuKzFN7e0WHd24Er1OylWhO0ygvB0v7OK6BEomLmfWvSNdsXC',
                'admin',
                'admin',
                35,
                true
                );
INSERT INTO users (username, password, name, surname, age, enabled)
        VALUES ('alex',
                '$2a$12$8qy7ZuKzFN7e0WHd24Er1OylWhO0ygvB0v7OK6BEomLmfWvSNdsXC',
                'Alex',
                'Smith',
                50,
                false
                );
INSERT INTO users (username, password, name, surname, age, enabled)
        VALUES ('akovalev',
                '$2a$12$8qy7ZuKzFN7e0WHd24Er1OylWhO0ygvB0v7OK6BEomLmfWvSNdsXC',
                'Александр',
                'Ковалёв',
                50,
                true
                );

-- Вставка данных в таблицу user_roles
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);
