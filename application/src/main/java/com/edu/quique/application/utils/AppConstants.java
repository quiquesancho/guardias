package com.edu.quique.application.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppConstants {
  private AppConstants() {
    throw new IllegalStateException("Utility class not meant to be instantiated.");
  }

  public static final String TEACHER = "docente";
  public static final String TEACHING_HOURS = "horario_ocupacion";
  public static final String TIMETABLE_GROUP = "horario_grupo";
  public static final String DOCUMENT = "documento";
  public static final String EMAIL = "correo";
  public static final String NAME = "nombre";
  public static final String FIRST_SURNAME = "apellido1";
  public static final String SECOND_SURNAME = "apellido2";
  public static final String DAY_OF_WEEK = "dia_semana";
  public static final String START_HOUR = "hora_desde";
  public static final String END_HOUR = "hora_hasta";
  public static final String OCCUPATION = "ocupacion";
  public static final String CLASSROOM = "aula";
  public static final String CONTENT = "contenido";
  public static final String GROUP = "grupo";
  public static final DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern("H:mm");
  public static final LocalTime BREAK_START_HOUR = LocalTime.parse("11:00", FORMAT_TIME);
  public static final LocalTime BREAK_END_HOUR = LocalTime.parse("11:30", FORMAT_TIME);
  public static final Long INTERVAL_MINUTES = 55L;
  public static final String OCCUPATION_GUARD = "3249454";
}
