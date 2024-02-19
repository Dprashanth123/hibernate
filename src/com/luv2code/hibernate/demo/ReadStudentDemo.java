package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create a student object
			System.out.println("Craeting new student object...");
			Student tempStudent = new Student("Duffy","Duck","paul@luv2code");
			//start the transaction
			session.beginTransaction();
			//save the student object
			System.out.println("Saving the student....");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//MY New code 
			//Find out the student ID primary key
			System.out.println("Saved Student Generated id: "+tempStudent.getId());
			
			//now get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//retrive student based on primary key: ID
			System.out.println("\nGetting Studnet with ID: "+tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("get Complete " + myStudent);
			//Commit the Transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
