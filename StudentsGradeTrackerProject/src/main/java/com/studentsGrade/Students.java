package com.studentsGrade;

import java.util.List;

public class Students {
    private int id;
    private String name;
    private String course;
    private String semester;
    private List<subjects> subjects;
    private String semesterGrade;

    
    public Students(int id, String name, String course, String semester, List<subjects> subjects) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.semester = semester;
        this.subjects = subjects;
        this.semesterGrade = calculateSemesterGrade();  
    }

   
    public String calculateSemesterGrade() {
        double totalGrade = 0.0;
        for (subjects subject : subjects) {
            totalGrade += subject.getGrade();
        }
        double averageGrade = totalGrade / subjects.size();

        if (averageGrade >= 90) {
            return "A";
        } else if (averageGrade >= 80) {
            return "B";
        } else if (averageGrade >= 70) {
            return "C";
        } else if (averageGrade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<subjects> subjects) {
        this.subjects = subjects;
    }

    public String getSemesterGrade() {
        return semesterGrade;
    }

    public void setSemesterGrade(String semesterGrade) {
        this.semesterGrade = semesterGrade;
    }

    @Override
    public String toString() {
        return "Students [id=" + id + ", name=" + name + ", course=" + course + ", semester=" + semester
                + ", semesterGrade=" + semesterGrade + "]";
    }
}
