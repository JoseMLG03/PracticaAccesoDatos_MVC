package modelo;


import modelo.*;
import vista.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class Logica {
	
	public static void main(String[] args) {
		 // Crea una instancia de SessionFactory usando la configuración del archivo hibernate.cfg.xml
	    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    
	    // Abre una nueva sesión de Hibernate
	    Session session = sessionFactory.openSession();
	    
	    // Inicializa una transacción
	    Transaction transaction = null;
	    
	    try {
	        // Inicia la transacción
	        transaction = session.beginTransaction();
	        
	        // Crea y configura una entidad Clientes
	        /*Clientes cliente = new Clientes();
	        cliente.setCodCliente("7769");
	        cliente.setNombre("Federico");
	        cliente.setCodRepresentante("106");
	        cliente.setLimiteCredito(1050.0);*/
	        
	        
	        
	        
	        
	        
	        // Guarda la entidad en la base de datos usando el método persist
	        session.persist(/*cliente*/);
	        
	        // Compromete la transacción
	        transaction.commit();
	        
	    } catch (RuntimeException e) {
	        // Si ocurre una excepción durante la transacción, realiza un rollback
	        if (transaction != null) transaction.rollback();
	        
	        // Imprime la excepción
	        e.printStackTrace();
	        
	    } finally {
	        // Cierra la sesión de Hibernate
	        session.close();
	    }
	}
}
