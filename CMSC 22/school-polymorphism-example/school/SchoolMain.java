package school;

import java.util.ArrayList;

public class SchoolMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student ken = new Student("Kendall", "2009-18222", "BSCS");
		Student mi = new Student("Miyah", "2008-19119", "BSIE");
		Student bet = new Student("Betel", "2008-18818", "BSCS");
		Student jim = new Student("Jim", "2008-23818", "BSChem");
		
		ken.addSubject("CMSC 22");
		ken.addSubject("CMSC 21");
		
		mi.addSubject("CMSC 22");
		mi.addSubject("CMSC 150");
		
		bet.addSubject("CMSC 125");
		
		ken.displayState();
		mi.displayState();
		bet.displayState();
		
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(ken);	
		students.add(mi);
		students.add(bet);
		students.add(jim);
		
		System.out.println("\nARRAYLIST OF STUDENTS:");
		for(Student s: students) {
			s.displayState();
		}
		
		// the course CMSC 150 of mi
		// mi -> 1
		// "CMSC 150" -> 1
		System.out.println("Miyah's Subject: " + students.get(1).getSpecificSubject(1));
		
		StudentSorter ss = new StudentSorter();
		ss.tallyCourses(students);
		ss.tallySubjects(students);
		ss.groupStudentsByCourse(students);
		ss.groupStudentsBySubject(students);
	}

}
