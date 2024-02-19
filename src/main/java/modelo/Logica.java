package modelo;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class Logica {
	
	private void cargarClientes(JComboBox<String> comboBox) {
	    try {
	        modelo.ConexionBD CBD = new modelo.ConexionBD("localhost", "3306", "root", "", "bancoVigo");
	        
	        Connection conn = CBD.getCon();
	        // Query para obtener los clientes
	        String query = "SELECT clNombre FROM Cliente";

	        // Preparar la declaración SQL
	        PreparedStatement statement = conn.prepareStatement(query);

	        // Ejecutar la consulta
	        ResultSet resultSet = statement.executeQuery();

	        // Agregar los clientes al JComboBox
	        while (resultSet.next()) {
	            comboBox.addItem(resultSet.getString("clNombre"));
	        }

	        // Cerrar conexiones
	        resultSet.close();
	        statement.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void mostrarDatosCliente(String nombreCliente, JScrollPane scrollPane,DefaultTableModel tableModel) {
        try {
            modelo.ConexionBD CBD = new modelo.ConexionBD("localhost", "3306", "root", "", "bancoVigo");

            Connection conn = CBD.getCon();
            // Query para obtener las cuentas del cliente seleccionado
            String query = "SELECT cuCodSucursal, cuFechaCreacion, CuSaldo FROM Cuenta " +
                           "INNER JOIN CuentasCliente ON Cuenta.cuCodCuenta = CuentasCliente.ccCodCuenta " +
                           "INNER JOIN Cliente ON CuentasCliente.ccDNI = Cliente.clDni " +
                           "WHERE Cliente.clNombre = ?";
            System.out.println(query);
            // Preparar la declaración SQL
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nombreCliente);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Limpiar el modelo de tabla antes de agregar nuevos datos
            tableModel.setRowCount(0);

            // Agregar las filas al modelo de tabla
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getInt("cuCodSucursal"),
                        resultSet.getString("cuFechaCreacion"),
                        resultSet.getDouble("CuSaldo")
                };
                tableModel.addRow(rowData);
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
