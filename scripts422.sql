CREATE TABLE human
(
    id        bigserial primary key,
    name      varchar(125) NOT NULL,
    age       int          NOT NULL,
    is_driver boolean DEFAULT false,
    car_id    int CHECK ( is_driver = true ),
    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE car
(
    id    bigserial primary key,
    brand varchar(125) NOT NULL,
    model varchar(125) NOT NULL,
    price decimal
);

SELECT f.name, s.name, s.age
FROM faculty f
         LEFT JOIN student s ON f.id = s.faculty_id
WHERE f.name = 'hogwarts';

SELECT *
FROM student s
         LEFT JOIN avatar a on a.id = s.id;