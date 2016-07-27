package by.grodno.zagart.java.util;

import by.grodno.zagart.java.entities.Order;
import by.grodno.zagart.java.entities.OrderProduct;
import by.grodno.zagart.java.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Class which combines classes of Hibernate and use them.
 */
public class HibernateUtil {

    public static final int BATCH_SIZE = 20;

    private static final SessionFactory factory;
    private static Session currentSession;
    private static Transaction currentTransaction;

    private static int batch = 0;

    private HibernateUtil() {}

    static {
        ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderProduct.class)
                .buildMetadata();
        factory = metadata.buildSessionFactory();
    }

    public static Session getCurrentSession() {
        return currentSession;
    }

    public static Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public static Session openCurrentSession() {
        currentSession = factory.openSession();
        return currentSession;
    }

    public static Transaction openCurrentSessionWithTransaction() {
        currentSession = factory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentTransaction;
    }

    public static void closeCurrentSession() {
        if (++batch % BATCH_SIZE == 0) {
            currentSession.flush();
            currentSession.clear();
            batch = 1;
        }
        currentSession.close();
    }

    public static void closeCurrentSessionWithTransaction() {
        if (++batch % BATCH_SIZE == 0) {
            currentSession.flush();
            currentSession.clear();
            batch = 1;
        }
        currentTransaction.commit();
        currentSession.close();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void closeFactory() { factory.close(); }

}
