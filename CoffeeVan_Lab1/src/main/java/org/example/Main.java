package org.example;

import org.example.entities.Pack;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Create an entity and save it
        //Pack entity = new Pack(3,3,3,"dasda"); // Create your entity object
        //session.persist(entity);

        // Commit the transaction and close the session
        session.getTransaction().commit();
        session.close();
    }
}