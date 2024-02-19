package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
            int studentId = 3;
			
			//now get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//retrive student based on primary key: ID
			System.out.println("\nGetting Studnet with ID: "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//Delete Studnet
			System.out.println("Delete Student");
			
			session.delete(myStudent);
			//delete stuent id 3002
			
			System.out.println("Delete Student ID 3003");
			session.createQuery("delete from Student where id = 3003").executeUpdate();
			//Commit the Transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
