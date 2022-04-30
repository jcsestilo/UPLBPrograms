package school;

import java.util.ArrayList;

public class Student {
	
	private String name;
	private String studentNo;
	private String course;
	// subjects
	private ArrayList<String> subjects;
	
	public Student(String name, String studentNo, String course) {
		this.name = name;
		this.studentNo = studentNo;
		this.course = course;
		
		this.subjects = new ArrayList<String>();
	}
	
	void addSubject(String subject) {
		this.subjects.add(subject);
	}
	
	String getSpecificSubject(int index) {
		return this.subjects.get(index);
	}
	
	// getters
	public String getName() {
		return this.name;
	}
	
	public String getStudentNo() {
		return this.studentNo;
	}
	
	public String getCourse() {
		return this.course;
	}
	
	public ArrayList<String> getSubjects(){
		return this.subjects;
	}
	
	public void displayState() {
		System.out.println("Name: " + this.name);
		System.out.println("Student Number: " + this.studentNo);
		System.out.println("Course: " + this.course);
		
		// if subjects is not empty, we print the subjects
		if(!this.subjects.isEmpty()) {
			for(String subj: subjects) {
				System.out.println(subj);
			}
		}
	}
}
