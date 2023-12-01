package com.edu.quique.application.exceptions;

public class TeacherNotFoundException extends RuntimeException {
  public TeacherNotFoundException(String msg) {
    super(msg);
  }
}
