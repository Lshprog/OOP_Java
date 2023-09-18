package org.example.common.dboper;

import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
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

    public static void executeTransactions(List<Consumer<Session>> databaseOperations) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                for (Consumer<Session> operation : databaseOperations) {
                    operation.accept(session);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public static <T> List<T> executeQueries(List<Function<Session, T>> databaseOperations) {
        List<T> results = new ArrayList<>();
        try (Session session = HibernateUtil.openSession()) {
            for (Function<Session, T> operation : databaseOperations) {
                T result = operation.apply(session);
                results.add(result);
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
