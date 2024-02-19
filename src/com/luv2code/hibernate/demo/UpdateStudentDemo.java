package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			int studentId = 1;
			
			//now get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//retrive student based on primary key: ID
			System.out.println("\nGetting Studnet with ID: "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating Student");
			
			myStudent.setFirstName("Scooby");
			
			//Commit the Transaction
			session.getTransaction().commit();
			
			//NEW Code
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Update email for all students
			System.out.println("Update email for all Students");
			
			session.createQuery("update Student set email='foo.gmail.com'").executeUpdate();
			
			//Commit the Transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
