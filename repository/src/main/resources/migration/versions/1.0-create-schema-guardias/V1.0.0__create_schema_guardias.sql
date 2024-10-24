DROP TABLE IF EXISTS REGISTRY_ABSENCE CASCADE;
DROP TABLE IF EXISTS REGISTRY_ABSENCE_AUD CASCADE;
DROP TABLE IF EXISTS ABSENCE CASCADE;
DROP TABLE IF EXISTS TEACHERS CASCADE;
DROP TABLE IF EXISTS TEACHING_HOURS CASCADE;
DROP TABLE IF EXISTS TIMETABLE_GROUP CASCADE;
DROP TABLE IF EXISTS ABSENCE_AUD CASCADE;
DROP TABLE IF EXISTS TEACHERS_AUD CASCADE;
DROP TABLE IF EXISTS TEACHING_HOURS_AUD CASCADE;
DROP TABLE IF EXISTS TIMETABLE_GROUP_AUD CASCADE;

DROP SEQUENCE IF EXISTS teaching_hours_id_seq;
DROP SEQUENCE IF EXISTS timetable_group_id_seq;
DROP SEQUENCE IF EXISTS absence_id_seq;
DROP SEQUENCE IF EXISTS registry_absence_id_seq;

CREATE SEQUENCE teaching_hours_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE timetable_group_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE absence_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;
CREATE SEQUENCE registry_absence_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;


/*==============================================================*/
/* Table: TEACHERS                                              */
/*==============================================================*/
CREATE TABLE teachers (
    teacher_id VARCHAR(9) NOT NULL,
    email VARCHAR(255),
    name VARCHAR(255),
    first_surname VARCHAR(255),
    second_surname VARCHAR(255),
    creation_date TIMESTAMP DEFAULT now() NOT NULL,
    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    modification_date TIMESTAMP NULL,
    cod_user_modification VARCHAR(255),
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
    creation_date TIMESTAMP DEFAULT now() NOT NULL,
    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    modification_date TIMESTAMP NULL,
    cod_user_modification VARCHAR(255),
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
    creation_date TIMESTAMP DEFAULT now() NOT NULL,
    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    modification_date TIMESTAMP NULL,
    cod_user_modification VARCHAR(255),
    PRIMARY KEY (timetable_group_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

/*==============================================================*/
/* Table: ABSENCE                                               */
/*==============================================================*/
CREATE TABLE absence (
    absence_id BIGINT DEFAULT nextval('absence_id_seq'::regclass),
    day_of_week VARCHAR(1),
    absence_date DATE,
    start_hour TIME,
    end_hour TIME,
    teacher_id VARCHAR(9),
    timetable_group_id BIGINT,
    is_assigned BOOLEAN DEFAULT FALSE,
    assigned_time TIMESTAMP,
    creation_date TIMESTAMP DEFAULT now() NOT NULL,
    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    modification_date TIMESTAMP NULL,
    cod_user_modification VARCHAR(255),
    PRIMARY KEY (absence_id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id),
    FOREIGN KEY (timetable_group_id) REFERENCES timetable_group (timetable_group_id),
    CONSTRAINT absence_unq UNIQUE (absence_date, start_hour, end_hour, teacher_id)
);

/*==============================================================*/
/* Table: REGISTRY_ABSENCE                                      */
/*==============================================================*/
CREATE TABLE REGISTRY_ABSENCE (
    registry_absence_id BIGINT DEFAULT nextval('registry_absence_id_seq'::regclass),
    absence_id BIGINT,
    guard_teacher_id VARCHAR(9),
    observation TEXT,
    creation_date TIMESTAMP DEFAULT now() NOT NULL,
    cod_user_creation VARCHAR(255) DEFAULT 'Guardias-back-app' NOT NULL,
    modification_date TIMESTAMP NULL,
    cod_user_modification VARCHAR(255),
    PRIMARY KEY (registry_absence_id),
    FOREIGN KEY (guard_teacher_id) REFERENCES teachers (teacher_id)
);

/*==============================================================*/
/* Table: REVINFO                                               */
/*==============================================================*/
CREATE TABLE REVINFO (
    REV integer generated by default as identity,
    REVTSTMP bigint,
    primary key (REV)
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
    cod_user_creation VARCHAR(255),
    creation_date TIMESTAMP,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    REV INTEGER NOT NULL,
    REVTYPE INTEGER NOT NULL,
    CONSTRAINT TEACHERS_AUD_PKEY PRIMARY KEY (teacher_id, REV),
    FOREIGN KEY (REV) REFERENCES REVINFO (REV)
);

COMMENT
  ON COLUMN TEACHERS_AUD.REV IS 'Identificador, Ejemplo: 1';
COMMENT
  ON COLUMN TEACHERS_AUD.REVTYPE IS 'Tipo de operación realizada por el usuario, Ejemplo: 0-Creado, 1-Modificado, 2-Eliminado';

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
    cod_user_creation VARCHAR(255),
    creation_date TIMESTAMP,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    REV INTEGER NOT NULL,
    REVTYPE INTEGER NOT NULL,
    CONSTRAINT TEACHING_HOURS_AUD_PKEY PRIMARY KEY (teaching_hours_id, REV),
    FOREIGN KEY (REV) REFERENCES REVINFO (REV)
);

COMMENT
  ON COLUMN TEACHING_HOURS_AUD.REV IS 'Identificador, Ejemplo: 1';
COMMENT
  ON COLUMN TEACHING_HOURS_AUD.REVTYPE IS 'Tipo de operación realizada por el usuario, Ejemplo: 0-Creado, 1-Modificado, 2-Eliminado';

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
    cod_user_creation VARCHAR(255),
    creation_date TIMESTAMP,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    REV INTEGER NOT NULL,
    REVTYPE INTEGER NOT NULL,
    CONSTRAINT TIMETABLE_GROUP_AUD_PKEY PRIMARY KEY (timetable_group_id, REV),
    FOREIGN KEY (REV) REFERENCES REVINFO (REV)
);

COMMENT
  ON COLUMN TIMETABLE_GROUP_AUD.REV IS 'Identificador, Ejemplo: 1';
COMMENT
  ON COLUMN TIMETABLE_GROUP_AUD.REVTYPE IS 'Tipo de operación realizada por el usuario, Ejemplo: 0-Creado, 1-Modificado, 2-Eliminado';

/*==============================================================*/
/* Table: ABSENCE_AUD                                           */
/*==============================================================*/
CREATE TABLE absence_aud (
    absence_id BIGINT NOT NULL,
    day_of_week VARCHAR(1),
    absence_date DATE,
    start_hour TIME,
    end_hour TIME,
    teacher_id VARCHAR(9),
    timetable_group_id BIGINT,
    is_assigned BOOLEAN DEFAULT FALSE,
    assigned_time TIMESTAMP,
    cod_user_creation VARCHAR(255),
    creation_date TIMESTAMP,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    REV INTEGER NOT NULL,
    REVTYPE INTEGER NOT NULL,
    CONSTRAINT ABSENCE_AUD_PKEY PRIMARY KEY (absence_id , REV),
    FOREIGN KEY (REV) REFERENCES REVINFO (REV)
);

COMMENT
  ON COLUMN absence_aud.REV IS 'Identificador, Ejemplo: 1';
COMMENT
  ON COLUMN absence_aud.REVTYPE IS 'Tipo de operación realizada por el usuario, Ejemplo: 0-Creado, 1-Modificado, 2-Eliminado';


/*==============================================================*/
/* Table: REGISTRY_ABSENCE_AUD                                  */
/*==============================================================*/
CREATE TABLE REGISTRY_ABSENCE_AUD (
    registry_absence_id BIGINT,
    absence_id BIGINT,
    guard_teacher_id VARCHAR(9),
    observation TEXT,
    cod_user_creation VARCHAR(255),
    creation_date TIMESTAMP,
    cod_user_modification VARCHAR(255),
    modification_date TIMESTAMP,
    REV INTEGER NOT NULL,
    REVTYPE INTEGER NOT NULL,
    CONSTRAINT REGISTRY_ABSENCE_AUD_PKEY PRIMARY KEY (registry_absence_id, REV),
    FOREIGN KEY (REV) REFERENCES REVINFO (REV)
);

COMMENT
  ON COLUMN REGISTRY_ABSENCE_AUD.REV IS 'Identificador, Ejemplo: 1';
COMMENT
  ON COLUMN REGISTRY_ABSENCE_AUD.REVTYPE IS 'Tipo de operación realizada por el usuario, Ejemplo: 0-Creado, 1-Modificado, 2-Eliminado';

INSERT INTO teachers (teacher_id,email,"name",first_surname,second_surname,creation_date,cod_user_creation,modification_date,cod_user_modification)
VALUES ('12345678C','admin@admin.com','Admin','Admin','Admin',current_date,'anonymousUser',current_date,'anonymousUser');