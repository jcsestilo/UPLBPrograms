package school;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentSorter {
	
	// "BSCS" -> 2
	// "BSIE" -> 1
	void tallyCourses(ArrayList<Student> students) {
		HashMap<String, Integer> courseTally = new HashMap<String, Integer>();
		System.out.println("\nSUBJECT TALLY");System.out.println("\nSUBJECT TALLY");
		
		// for each student
		for(Student s: students) {
			String course = s.getCourse();
			
			// if course exists in the hashmap, increment the value of course by 1
			// else put course in the hashmap with 1 as value
			if(courseTally.containsKey(course)) {
				// courseTally.put(course, 3)
//				Integer cnt = courseTally.get(course) + 1;
				courseTally.put(course, courseTally.get(course) + 1);
			}else {
				courseTally.put(course, 1);
			}
		}
		
		for(String c: courseTally.keySet()) {
			System.out.println(c + " -> " + courseTally.get(c));
		}
	}
	
	
		// OTHER EXAMPLES THAT USE HASHMAPS
		// NOT DISCUSSED DURING SYNC MTG
		// study and analyze the methods below
	
		public void tallySubjects(ArrayList<Student> students) {
			System.out.println("\nSUBJECT TALLY");
			HashMap<String, Integer> subjectTally = new HashMap<String, Integer>();
			
			for(Student s: students) {
				for(String subject: s.getSubjects()) {
					if(subjectTally.containsKey(subject)) {
						subjectTally.put(subject, subjectTally.get(subject) + 1);
					} else subjectTally.put(subject, 1);
				}
			}
			
			for(String subject: subjectTally.keySet()) {
				System.out.println(subject + " -> " + subjectTally.get(subject));
			}
		}
		
		public void groupStudentsByCourse(ArrayList<Student> students) {
			System.out.println("\nSTUDENTS PER COURSE:");
			HashMap<String, ArrayList<Student>> courseStudentList = new HashMap<String, ArrayList<Student>>();
			
			for(Student s: students) {
				if(courseStudentList.containsKey(s.getCourse()))
					courseStudentList.get(s.getCourse()).add(s);
				else {
					ArrayList<Student> courseStudents = new ArrayList<Student>();
					courseStudents.add(s);
					courseStudentList.put(s.getCourse(), courseStudents);
				}
			}
			
			for(String course: courseStudentList.keySet()) {
				System.out.println(course + ":");
				for(Student s: courseStudentList.get(course))
					System.out.println(">> " + s.getName());
			}
		}
		
		public void groupStudentsBySubject(ArrayList<Student> students) {
			System.out.println("\nSTUDENTS PER SUBJECT:");
			HashMap<String, ArrayList<Student>> subjectStudentList = new HashMap<String, ArrayList<Student>>();
			
			for(Student s: students) {
				for(String subject: s.getSubjects()) {
					if(subjectStudentList.containsKey(subject))
						subjectStudentList.get(subject).add(s);
					else {
						ArrayList<Student> subjectStudents = new ArrayList<Student>();
						subjectStudents.add(s);
						subjectStudentList.put(subject, subjectStudents);
					}
				}
			}
			
			for(String subject: subjectStudentList.keySet()) {
				System.out.println(subject + ":");
				for(Student s: subjectStudentList.get(subject))
					System.out.println(">> " + s.getName());
			}
		}
}
