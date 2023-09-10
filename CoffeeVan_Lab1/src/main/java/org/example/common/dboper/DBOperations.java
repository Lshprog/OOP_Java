package org.example.common.dboper;

import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class DBOperations {

    public static void executeTransaction(Consumer<Session> databaseOperation) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                databaseOperation.accept(session);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public static <T> T executeQuery(Function<Session, T> databaseOperation) {
        try (Session session = HibernateUtil.openSession()) {
            return databaseOperation.apply(session);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
