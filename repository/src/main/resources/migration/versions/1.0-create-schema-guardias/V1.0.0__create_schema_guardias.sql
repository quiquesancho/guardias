DROP SEQUENCE IF EXISTS teaching_hours_id_seq;
DROP SEQUENCE IF EXISTS timetable_group_id_seq;

CREATE SEQUENCE teaching_hours_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE timetable_group_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

DROP TABLE IF EXISTS ABSENCE_TEACHER CASCADE;
DROP TABLE IF EXISTS REGISTRY_ABSENCE CASCADE;
DROP TABLE IF EXISTS TEACHERS CASCADE;
DROP TABLE IF EXISTS TEACHING_HOURS CASCADE;
DROP TABLE IF EXISTS TIMETABLE_GROUP CASCADE;
DROP TABLE IF EXISTS TEACHERS_AUD CASCADE;
DROP TABLE IF EXISTS TEACHING_HOURS_AUD CASCADE;
DROP TABLE IF EXISTS TIMETABLE_GROUP_AUD CASCADE;

/*==============================================================*/
/* Table: TEACHERS                                              */
/*==============================================================*/
CREATE TABLE teachers (
    teacher_id VARCHAR(9) NOT NULL,
    email VARCHAR(255),
    name VARCHAR(255),
    first_surname VARCHAR(255),
    second_surname VARCHAR(255),
/*    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,*/
    PRIMARY KEY (teacher_id)
);

/*==============================================================*/
/* Table: TEACHING_HOURS                                        */
/*==============================================================*/
CREATE TABLE teaching_hours (
    teaching_hours_id BIGINT DEFAULT nextval('teaching_hours_id_seq'::regclass),
    start_hour TIME,
    end_hour TIME,
    day_of_week VARCHAR(255),
    teacher_id VARCHAR(9),
    occupation VARCHAR(255),
/*    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,*/
    PRIMARY KEY (teaching_hours_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

/*==============================================================*/
/* Table: TIMETABLE_GROUP                                       */
/*==============================================================*/
CREATE TABLE timetable_group (
    timetable_group_id BIGINT DEFAULT nextval('timetable_group_id_seq'::regclass),
    day_of_week VARCHAR(255),
    start_hour TIME,
    end_hour TIME,
    group_id VARCHAR(255),
    classroom VARCHAR(255),
    content VARCHAR(255),
    teacher_id VARCHAR(9),
/*    cod_user_creation VARCHAR(255) NOT NULL DEFAULT 'Guardias-back-app',
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,*/
    PRIMARY KEY (timetable_group_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

/*==============================================================*/
/* Table: TEACHERS_AUD                                          */
/*==============================================================*/
CREATE TABLE teachers_aud (
    teacher_id VARCHAR(9) NOT NULL,
    email VARCHAR(255),
    name VARCHAR(255),
    first_surname VARCHAR(255),
    second_surname VARCHAR(255),
/*    cod_user_creation VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,*/
    PRIMARY KEY (teacher_id)
);

/*==============================================================*/
/* Table: TEACHING_HOURS_AUD                                    */
/*==============================================================*/
CREATE TABLE teaching_hours_aud (
    teaching_hours_id BIGINT NOT NULL,
    start_hour TIME,
    end_hour TIME,
    day_of_week VARCHAR(255),
    teacher_id VARCHAR(9),
    occupation VARCHAR(255),
/*    cod_user_creation VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,*/
    PRIMARY KEY (teaching_hours_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

/*==============================================================*/
/* Table: TIMETABLE_GROUP_AUD                                   */
/*==============================================================*/
CREATE TABLE timetable_group_aud (
    timetable_group_id BIGINT NOT NULL,
    day_of_week VARCHAR(255),
    start_hour TIME,
    end_hour TIME,
    group_id VARCHAR(255),
    classroom VARCHAR(255),
    content VARCHAR(255),
    teacher_id VARCHAR(9),
/*    cod_user_creation VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,*/
    PRIMARY KEY (timetable_group_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);