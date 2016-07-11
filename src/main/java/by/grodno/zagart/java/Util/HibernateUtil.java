package by.grodno.zagart.java.Util;

import by.grodno.zagart.java.Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Class which combines classes of Hibernate and use them.
 */
public class HibernateUtil {

    private static Session session = init();

    private HibernateUtil() {}

    public static Session getSession() {
        if (session.isOpen()) {
            return session;
        } else {
            session = init();
            return session;
        }
    }

    private static Session init() {
        ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderProduct.class)
                .buildMetadata();
        return metadata.buildSessionFactory().openSession();
    }

    public static void closeFactory() { session.getSessionFactory().close(); }

}
