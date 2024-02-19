package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;




public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			//start the transaction
			session.beginTransaction();
			
			//Query Student
			
			List<Student> theStudents = session.createQuery("from Student").list();
			
			//Display the student
			
			dispalyStudents(theStudents);
			
			//Query Student last name = k
			
		    theStudents = session.createQuery("from Student s where s.lastName = 'k'").list();
		    
		    System.out.println("/n/n Student who's last name of k");
		    
		    dispalyStudents(theStudents);
		    
		    //who email with gmail.com
            theStudents = session.createQuery("from Student s where " +
		                                           "s.email LIKE '%gmail.com'").list();
		    
		    System.out.println("/n/n Student who's with gmail");
			
		    dispalyStudents(theStudents);
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void dispalyStudents(List<Student> theStudents) {
		for(Student tempStudent:theStudents) {
			System.out.println(tempStudent);
		}
	}

}
