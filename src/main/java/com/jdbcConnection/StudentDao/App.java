package com.jdbcConnection.StudentDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		char ch;
		int i;
		StudentDao sDao = new StudentDao();
		System.out.println("------MAIN MENU------");
		System.out.println("a. Register a new Student");
		System.out.println("b. View a Student");
		System.out.println("c. View all Students");
		System.out.println("d. Update a Student");
		System.out.println("e. Delete a Student");
		System.out.println("Make a choice: ");
		ch = in.next().charAt(0);
		in.nextLine();
		
		if(ch=='a') {
			Student student = getNewStudentDetails();
			i = sDao.save(student);
			if(i==1) {
				System.out.println("Student saved to the database successfully!!");
			}
			else {
				System.out.println("Student failed to be saved to the database!");
			}
			
		} else if(ch=='b') {
			System.out.println("Enter the id of the student you want to view: ");
			int id = in.nextInt();
			Student student = sDao.selectStudent(id);
			if(student!=null) {
				System.out.println(student);
			}
			else {
				System.out.println("Student not found!");
			}
		}else if(ch=='c') {
			ArrayList<Student> studentList = sDao.selectAllStudents();
			if(studentList.size()>0) {
				studentList.forEach(System.out::println);
			}
			else {
				System.out.println("No students found!");
			}
		}else if(ch=='d') {
			Student student = getUpdateStudentDetails();
			i = sDao.updateStudent(student);
			if(i==0) {
				System.out.println("Student Update Failed!");
			}
			else {
				System.out.println("Student updated successfully!");
			}
		}else if(ch=='e') {
			System.out.println("Enter the id of the student to be deleted: ");
			int id = in.nextInt();
			Student student = sDao.selectStudent(id);
			if(student!=null) {
					System.out.println(student);
					System.out.println("Confirm Delete(y/n): ");
					ch = in.next().charAt(0);
					if(ch=='y') {
						i = sDao.deleteStudent(id);
					if(i==1) {
						System.out.println("Student Deleted from the database!");
					}
					else {
						System.out.println("Student delete failed!");
					}
				}
			}
			else {
				System.out.println("Student does not exist!");
			}
		}
		
	}
	private static Student getNewStudentDetails() {
		Student student = new Student();
		System.out.println("Enter the new Student Details: ");
		System.out.println("Name: ");
		student.setName(in.nextLine());
		System.out.println("Email: ");
		student.setEmail(in.nextLine());
		System.out.println("Is Active(y/n): ");
		student.setActive(in.next().charAt(0)=='y'?true:false);
		return student;
	}
	private static Student getUpdateStudentDetails() {
		Student student = new Student();
		System.out.println("Enter the id of the student you want to update: ");
		int id = in.nextInt();
		student.setId(id);
		in.nextLine();
		System.out.println("Enter the Student Details: ");
		System.out.println("Name: ");
		student.setName(in.nextLine());
		System.out.println("Email: ");
		student.setEmail(in.nextLine());
		System.out.println("Is Active(y/n): ");
		student.setActive(in.next().charAt(0)=='y'?true:false);
		return student;
	}
}
