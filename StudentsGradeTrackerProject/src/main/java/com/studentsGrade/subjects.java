package com.studentsGrade;

public class subjects {

    private String subjectName;
    private double grade;

    
    public subjects(String subjectName, double grade) {
        this.subjectName = subjectName;
        this.grade = grade;
    }

   
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

   
}
