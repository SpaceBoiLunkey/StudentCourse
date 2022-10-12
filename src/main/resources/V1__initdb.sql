CREATE TABLE "aws_s3".STUDENT(
ID INT PRIMARY KEY NOT NULL,
NAME VARCHAR,
CREATED_AT TIMESTAMP NOT NULL DEFAULT NOW(),
IS_DELETED BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE "aws_s3".COURSE(
ID INT PRIMARY KEY NOT NULL,
NAME VARCHAR,
CREATED_AT TIMESTAMP NOT NULL DEFAULT NOW(),
IS_DELETED BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE SEQUENCE course_sequence;
CREATE SEQUENCE student_sequence;

CREATE TABLE "aws_s3".student_course(
ID INT PRIMARY KEY NOT NULL,
STUDENT_ID INT NOT NULL REFERENCES "aws_s3".student(id) ON DELETE CASCADE ON UPDATE CASCADE,
COURSE_ID INT NOT NULL REFERENCES "aws_s3".course(id) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE "aws_s3".STUDENT RENAME TO _STUDENT;
CREATE VIEW STUDENT AS SELECT * FROM "aws_s3"._STUDENT WHERE is_deleted=false;
SELECT * FROM STUDENT ORDER BY id;

ALTER TABLE "aws_s3".COURSE RENAME TO _COURSE;
CREATE VIEW COURSE AS SELECT * FROM "aws_s3"._COURSE WHERE is_deleted=false;
SELECT * FROM COURSE ORDER BY id;

INSERT INTO "aws_s3".STUDENT
    (ID, NAME, CREATED_AT, IS_DELETED)
    VALUES (1, 'John Smithley', DEFAULT, FALSE),
           (2, 'Ethan Nelson', DEFAULT, FALSE),
           (3, 'Mary Edwards', DEFAULT, FALSE),
           (4, 'Alex Dismukes', DEFAULT, FALSE),
           (5, 'Harambe Drumph', DEFAULT, TRUE);

INSERT INTO "aws_s3".COURSE
    (ID, NAME, CREATED_AT, IS_DELETED)
    VALUES (1, 'Computer Science', DEFAULT, FALSE),
           (2, 'Geology', DEFAULT, FALSE),
           (3, 'Marketing', DEFAULT, FALSE),
           (4, 'Algebra II', DEFAULT, FALSE),
           (5, 'World History', DEFAULT, FALSE),
           (6, 'Underwater Basketweaving', DEFAULT, TRUE);

INSERT INTO "aws_s3".STUDENT_COURSE
    (ID, STUDENT_ID, COURSE_ID)
    VALUES(1, 1, 1),
          (2, 1, 2),
          (3, 1, 5),
          (4, 2, 1),
          (5, 2, 3),
          (6, 2, 4),
          (7, 3, 2),
          (8, 3, 4),
          (9, 4, 1),
          (10, 4, 2),
          (11, 4, 3),
          (12, 5, 1),
          (13, 5, 6);
