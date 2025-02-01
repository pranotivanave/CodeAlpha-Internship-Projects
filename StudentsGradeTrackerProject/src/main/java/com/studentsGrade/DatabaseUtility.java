package com.studentsGrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtility {	
	    private static final String URL = "jdbc:mysql://localhost:3306/StudentGradeTrackerDb";
	    private static final String USER = "pranoti"; 
	    private static final String PASSWORD = "pranoti123"; 

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }

	    public static void insertStudent(Students student) {
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            String query = "INSERT INTO students (id, name, course, semester, semester_grade) VALUES (?, ?, ?, ?, ?)";
	            try (PreparedStatement stmt = connection.prepareStatement(query)) {
	                stmt.setInt(1, student.getId());
	                stmt.setString(2, student.getName());
	                stmt.setString(3, student.getCourse());
	                stmt.setString(4, student.getSemester());
	                stmt.setString(5, student.getSemesterGrade());
	                stmt.executeUpdate();
	                System.out.println("Student data inserted successfully!");

	                if (student.getSubjects() != null) {
	                    for (subjects subject : student.getSubjects()) {
	                        insertSubject(subject, student.getId());
	                    }
	                } else {
	                    System.out.println("No subjects to insert for student: " + student.getName());
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void insertSubject(subjects subject, int studentId) {
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            String query = "INSERT INTO subjects (student_id, subject_name, grade) VALUES (?, ?, ?)";
	            try (PreparedStatement stmt = connection.prepareStatement(query)) {
	                stmt.setInt(1, studentId);
	                stmt.setString(2, subject.getSubjectName());
	                stmt.setDouble(3, subject.getGrade());
	                stmt.executeUpdate();
	                System.out.println("Subject data inserted for student ID: " + studentId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static Students getStudentById(int id) {
	        Students student = null;
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            String query = "SELECT * FROM students WHERE id = ?";
	            try (PreparedStatement stmt = connection.prepareStatement(query)) {
	                stmt.setInt(1, id);
	                try (ResultSet rs = stmt.executeQuery()) {
	                    if (rs.next()) {
	                        String name = rs.getString("name");
	                        String course = rs.getString("course");
	                        String semester = rs.getString("semester");
	                        String semesterGrade = rs.getString("semester_grade");

	                        List<subjects> subjects = new ArrayList<>();
	                        String subjectQuery = "SELECT * FROM subjects WHERE student_id = ?";
	                        try (PreparedStatement subjectStmt = connection.prepareStatement(subjectQuery)) {
	                            subjectStmt.setInt(1, id);
	                            try (ResultSet subjectRs = subjectStmt.executeQuery()) {
	                                while (subjectRs.next()) {
	                                    String subjectName = subjectRs.getString("subject_name");
	                                    double grade = subjectRs.getDouble("grade");
	                                    subjects.add(new subjects(subjectName, grade));
	                                }
	                            }
	                        }

	                        student = new Students(id, name, course, semester, subjects);
	                        student.setSemesterGrade(semesterGrade);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return student;
	    }

	    public static void calculateAndStoreGradeMetrics(int studentId) {
	        Students student = getStudentById(studentId);
	        if (student != null) {
	            double totalGrade = 0;
	            double highestGrade = Double.MIN_VALUE;
	            double lowestGrade = Double.MAX_VALUE;

	            System.out.println("Subjects for student " + student.getName() + ":");
	            for (subjects subject : student.getSubjects()) {
	                System.out.println("Subject: " + subject.getSubjectName() + " | Grade: " + subject.getGrade());

	                double grade = subject.getGrade();
	                totalGrade += grade;
	                if (grade > highestGrade) highestGrade = grade;
	                if (grade < lowestGrade) lowestGrade = grade;
	            }

	            if (student.getSubjects().size() > 0) {
	                double averageGrade = totalGrade / student.getSubjects().size();
	                System.out.println("Average Grade: " + averageGrade);
	                System.out.println("Highest Grade: " + highestGrade);
	                System.out.println("Lowest Grade: " + lowestGrade);

	                String semesterGrade = calculateSemesterGrade(averageGrade);
	                System.out.println("Semester Grade: " + semesterGrade);

	                insertGradeMetrics(studentId, averageGrade, highestGrade, lowestGrade, semesterGrade);
	            } else {
	                System.out.println("No subjects found for the student.");
	            }
	        } else {
	            System.out.println("Student not found!");
	        }
	    }

	    public static String calculateSemesterGrade(double averageGrade) {
	        if (averageGrade >= 90) {
	            return "A";  // Excellent
	        } else if (averageGrade >= 80) {
	            return "B";  // Good
	        } else if (averageGrade >= 70) {
	            return "C";  // Satisfactory
	        } else if (averageGrade >= 60) {
	            return "D";  // Needs Improvement
	        } else {
	            return "F";  // Fail
	        }
	    }

	    public static void insertGradeMetrics(int studentId, double averageGrade, double highestGrade, double lowestGrade, String semesterGrade) {
	        String query = "INSERT INTO grade_metrics (student_id, average_grade, highest_grade, lowest_grade, semester_grade) VALUES (?, ?, ?, ?, ?)";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, studentId);
	            stmt.setDouble(2, averageGrade);
	            stmt.setDouble(3, highestGrade);
	            stmt.setDouble(4, lowestGrade);
	            stmt.setString(5, semesterGrade);
	            stmt.executeUpdate();
	            System.out.println("Grade metrics inserted successfully for student ID: " + studentId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	

