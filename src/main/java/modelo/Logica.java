package modelo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Logica {
    public static void main(String[] args) {
        // Obtenemos la sesión de Hibernate desde HibernateUtil
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        // Creamos una instancia de Cliente y configuramos sus atributos
        Cliente cliente = new Cliente();
        cliente.setClDni("145678A");
        cliente.setClNombre("Juoooooooan");
        cliente.setClApellido("Pérez");
        cliente.setClTelefono(123456789);
        
        // Iniciamos una transacción
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            // Guardamos el cliente en la base de datos
            session.persist(cliente);
            
            // Commit de la transacción
            tx.commit();
            System.out.println("Cliente guardado exitosamente en la base de datos.");
        } catch (Exception e) {
            // En caso de error, hacemos rollback de la transacción
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerramos la sesión de Hibernate
            session.close();
        }
    }
}
