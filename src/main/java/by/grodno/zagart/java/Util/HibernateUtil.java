package by.grodno.zagart.java.util;

import by.grodno.zagart.java.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Class which combines classes of Hibernate and use them.
 */
public class HibernateUtil {

    private static final SessionFactory factory;

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

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void closeFactory() { factory.close(); }

}
