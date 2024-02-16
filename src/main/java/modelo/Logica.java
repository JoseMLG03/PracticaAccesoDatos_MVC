package modelo;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class Logica {
	
	private void cargarClientesJcomboBoxModelo(JComboBox<String> comboBox) {
        try {
            // Establecer conexión con la base de datos (debes modificar la URL, usuario y contraseña según tu configuración)
        	ConexionBD CBD = new ConexionBD("localhost", "3306", "root", "");
            
            Connection conn = CBD.getCon();
            // Query para obtener los clientes
            String query = "SELECT nombre FROM clientes";

            // Preparar la declaración SQL
            PreparedStatement statement = conn.prepareStatement(query);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Agregar los clientes al JComboBox
            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString("nombre"));
            }

            // Cerrar conexiones
            resultSet.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
