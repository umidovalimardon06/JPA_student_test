package Day01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class StudentTest {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // Create session
        Session session = sessionFactory.openSession();

        try {
            // Begin transaction
            session.beginTransaction();

            // Create student objects
            Student student1 = new Student("Ahmad", "Karimov", new Date(), Gender.MALE);
            Student student2 = new Student("Fatima", "Saidova", new Date(), Gender.FEMALE);
            Student student3 = new Student("Alimardon", "Umidov", new Date(), Gender.MALE);


            // Save students
            session.save(student1);
            session.save(student2);
            session.save(student3);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Students saved successfully!");
            System.out.println("Student 1: " + student1);
            System.out.println("Student 2: " + student2);
            System.out.println("Student 3: " + student3);

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}