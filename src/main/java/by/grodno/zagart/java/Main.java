package by.grodno.zagart.java;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static by.grodno.zagart.java.Util.CommonUtil.createOrders;
import static by.grodno.zagart.java.Util.HibernateUtil.closeFactory;
import static by.grodno.zagart.java.Util.HibernateUtil.getSession;

/**
 * Main class with entry point.
 */
public class Main {

    public static void main(String[] args ) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        createOrders(10, session);
        transaction.commit();
        session.close();
        closeFactory();
    }

}
