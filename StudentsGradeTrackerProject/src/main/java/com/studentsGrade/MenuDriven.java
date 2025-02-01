package com.studentsGrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuDriven {

  
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        while (true) {
	            System.out.println("\n----------------------------------------------");
	            System.out.println("          Student Grade Tracker Menu");
	            System.out.println("----------------------------------------------");
	            System.out.println("1. Enter student details");
	            System.out.println("2. Calculate grade metrics (average, highest, lowest) and semester grade");
	            System.out.println("3. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            scanner.nextLine();  

	            switch (choice) {
	                case 1:
	                  
	                    System.out.println("\n----------------------------------------------");
	                    System.out.println("       Enter Student Details");
	                    System.out.println("----------------------------------------------");
	                    System.out.print("Enter student ID: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine();  
	                    System.out.print("Enter student name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter course: ");
	                    String course = scanner.nextLine();
	                    System.out.print("Enter semester: ");
	                    String semester = scanner.nextLine();

	                    System.out.print("Enter number of subjects: ");
	                    int numSubjects = scanner.nextInt();
	                    scanner.nextLine();  

	                    List<subjects> subjects = new ArrayList<>();
	                    for (int i = 0; i < numSubjects; i++) {
	                        System.out.print("Enter subject name: ");
	                        String subjectName = scanner.nextLine();
	                        System.out.print("Enter grade for " + subjectName + ": ");
	                        double grade = scanner.nextDouble();
	                        scanner.nextLine();  
	                        subjects.add(new subjects(subjectName, grade));
	                    }

	                    Students student = new Students(id, name, course, semester, subjects);
	                    DatabaseUtility.insertStudent(student);
	                    System.out.println("\n----------------------------------------------");
	                    System.out.println("Student data inserted successfully!");
	                    System.out.println("----------------------------------------------");
	                    break;

	                case 2:
	                   
	                    System.out.print("\n----------------------------------------------");
	                    System.out.println("       Grade Metrics for Student");
	                    System.out.println("----------------------------------------------");
	                    System.out.print("Enter student ID to calculate metrics: ");
	                    int studentId = scanner.nextInt();
	                    Students studentFromDb = DatabaseUtility.getStudentById(studentId);
	                    if (studentFromDb != null) {
	                        System.out.println("\n----------------------------------------------");
	                        System.out.printf("Student: %s\n", studentFromDb.getName());
	                        System.out.printf("Course: %s\n", studentFromDb.getCourse());
	                        System.out.printf("Semester: %s\n", studentFromDb.getSemester());
	                        System.out.printf("Semester Grade: %s\n", studentFromDb.getSemesterGrade());
	                        System.out.println("\n----------------------------------------------");

	                      
	                        System.out.println("Subjects for student " + studentFromDb.getName() + ":");
	                        for (subjects sub : studentFromDb.getSubjects()) {
	                            System.out.printf("Subject: %-10s | Grade: %.1f\n", sub.getSubjectName(), sub.getGrade());
	                        }

	                       
	                        DatabaseUtility.calculateAndStoreGradeMetrics(studentId);
	                    } else {
	                        System.out.println("\nStudent not found!");
	                    }
	                    break;

	                case 3:
	                    System.out.println("\n----------------------------------------------");
	                    System.out.println("        Exiting the Application...");
	                    System.out.println("----------------------------------------------");
	                    scanner.close();
	                    return;
	            }
	        }
	    }
	}


