CREATE SEQUENCE teaching_hours_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE SEQUENCE timetable_group_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;


/*==============================================================*/
/* Table: TEACHERS                                              */
/*==============================================================*/
CREATE TABLE teachers (
    teacher_id VARCHAR(9) NOT NULL,
    email VARCHAR(255),
    name VARCHAR(255),
    first_surname VARCHAR(255),
    second_surname VARCHAR(255),
    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    PRIMARY KEY (teacher_id)
);

/*==============================================================*/
/* Table: TEACHING_HOURS                                        */
/*==============================================================*/
CREATE TABLE teaching_hours (
    teaching_hours_id BIGINT DEFAULT teaching_hours_id_seq.nextval,
    start_hour TIME,
    end_hour TIME,
    day_of_week VARCHAR(255),
    teacher_id VARCHAR(9),
    occupation VARCHAR(255),
    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    PRIMARY KEY (teaching_hours_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

/*==============================================================*/
/* Table: TIMETABLE_GROUP                                       */
/*==============================================================*/
CREATE TABLE timetable_group (
    timetable_group_id BIGINT DEFAULT timetable_group_id_seq.nextval,
    day_of_week VARCHAR(255),
    start_hour TIME,
    end_hour TIME,
    group_id VARCHAR(255),
    classroom VARCHAR(255),
    content VARCHAR(255),
    teacher_id VARCHAR(9),
    cod_user_creation VARCHAR(255) NOT NULL DEFAULT 'Guardias-back-app',
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    PRIMARY KEY (timetable_group_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);