package com.edu.quique.application.exceptions;

public class AbsenceCannotBeModifiedOrDeletedException extends RuntimeException{
    public AbsenceCannotBeModifiedOrDeletedException(String msg) {
        super(msg);
    }
}
