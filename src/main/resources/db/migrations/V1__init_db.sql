USE hogwarts;

CREATE TABLE IF NOT EXISTS faculties
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(128) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS teachers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL
    );

CREATE TABLE IF NOT EXISTS students
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    faculty_id INT REFERENCES faculties (id)
    );

CREATE TABLE IF NOT EXISTS faculties_teachers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    faculty_id INT REFERENCES faculties (id),
    teacher_id INT REFERENCES teachers (id)
    );
