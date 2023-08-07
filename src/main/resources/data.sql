-- Вставка данных в таблицу roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

-- Вставка данных в таблицу users
INSERT INTO users (username, password, name, surname, age, enabled)
        VALUES ('admin',
                '{bcrypt}$2a$12$uhNxSAmsPvykFNhY7WChjOJHdVJWGWo.XDvOTbg7d14Bog5KIwTi2',
                'admin',
                'admin',
                35,
                true
                );
INSERT INTO users (username, password, name, surname, age, enabled)
        VALUES ('alex',
                '{bcrypt}$2a$12$uhNxSAmsPvykFNhY7WChjOJHdVJWGWo.XDvOTbg7d14Bog5KIwTi2',
                'Alex',
                'Smith',
                50,
                true
                );
INSERT INTO users (username, password, name, surname, age, enabled)
        VALUES ('akovalev',
                '{bcrypt}$2a$12$uhNxSAmsPvykFNhY7WChjOJHdVJWGWo.XDvOTbg7d14Bog5KIwTi2',
                'Александр',
                'Ковалёв',
                50,
                true
                );

-- Вставка данных в таблицу user_roles
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);
