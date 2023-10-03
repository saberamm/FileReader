create table course
(
    course_id   serial
        primary key,
    course_name varchar(255) not null
);

alter table course
    owner to postgres;

create table student
(
    student_id   serial
        primary key,
    student_name varchar(255) not null
);

alter table student
    owner to postgres;

create table studentcourserating
(
    rating_id  serial
        primary key,
    student_id integer          not null
        references student,
    course_id  integer          not null
        references course,
    rating     double precision not null,
    comment    text,
    timestamp  timestamp
);

alter table studentcourserating
    owner to postgres;