package com.example.coursework_kbk_19isp3;

import java.io.Serializable;

public class dbLesson implements Serializable {

    public String name;
    public String teacher;

    dbLesson(){
        name = "lesson";
        teacher = "teacher";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
