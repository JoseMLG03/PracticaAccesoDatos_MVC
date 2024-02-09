package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Creamos una configuración de Hibernate desde el archivo hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();

            // Construimos el registro de servicios estándar
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // Construimos la sesión de fábrica a partir de la configuración y el registro de servicios
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Manejamos cualquier excepción
            System.err.println("Error al iniciar la sesión de fábrica de Hibernate: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
