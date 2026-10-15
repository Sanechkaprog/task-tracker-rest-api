CREATE TABLE users (
                       user_id BIGSERIAL,
                       name VARCHAR(30) NOT NULL,
                       surname VARCHAR(30) NOT NULL,
                       registered_at DATE,

                       CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE tasks (
                       task_id BIGSERIAL,
                       description VARCHAR(100) NOT NULL,
                       user_id INTEGER,
                       created_at DATE,
                       status BOOLEAN DEFAULT true,

                       CONSTRAINT task_pk PRIMARY KEY (task_id),
                       CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE

)

-- table: tasks, field: status, 1 - processing status, 0 - status made
