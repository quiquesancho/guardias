package com.edu.quique.application.utils;


import java.time.format.DateTimeFormatter;

public class AppConstants {
    private AppConstants() {
        throw new IllegalStateException("Utility class not meant to be instantiated.");
    }
    public final static String TEACHER = "docente";
    public final static String TEACHING_HOURS = "horario_ocupacion";
    public final static String TIMETABLE_GROUP = "horario_grupo";
    public final static String DOCUMENT = "documento";
    public final static String EMAIL = "correo";
    public final static String NAME = "nombre";
    public final static String FIRST_SURNAME = "apellido1";
    public final static String SECOND_SURNAME = "apellido2";
    public final static String DAY_OF_WEEK = "dia_semana";
    public final static String START_HOUR = "hora_desde";
    public final static String END_HOUR = "hora_hasta";
    public final static String OCCUPATION = "ocupacion";
    public final static String CLASSROOM = "aula";
    public final static String CONTENT = "contenido";
    public final static String GROUP = "grupo";
    public static final DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern("H:mm");
}
