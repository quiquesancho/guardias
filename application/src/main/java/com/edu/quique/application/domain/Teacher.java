package com.edu.quique.application.domain;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String teacherId;
    private String email;
    private String name;
    private String firstSurname;
    private String secondSurname;

    public boolean equals(Object o) {
        if(this == o) return true;
        if ((o instanceof Teacher teacher)){
            return this.teacherId.equals(teacher.getTeacherId());
        }
        return false;
    }
    
}