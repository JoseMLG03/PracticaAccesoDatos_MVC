package modelo;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.EventQueue;
import java.awt.TextField;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Logica {
	//---------------------------------------Ventana Principal------------------------------------------------------------//
	public void cargarClientesEnComboBox(JFrame parent,JComboBox comboBox) {
	    // Accede al método cargarClientes de la instancia ventanaListadoframe
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
	
    public void cargarSucursalesEnComboBox(JFrame parent,JComboBox<String> comboBox) {
    	try {
            // Crear una conexión a la base de datos bancovigo
    		modelo.ConexionBD CBD = new modelo.ConexionBD("localhost", "3306", "root", "", "bancoVigo");
	        
	        Connection conn = CBD.getCon();

            // Crear una declaración SQL para obtener las sucursales
            String query = "SELECT suCiudad FROM Sucursales";
            PreparedStatement statement = conn.prepareStatement(query);

            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            // Limpiar el JComboBox antes de agregar nuevas sucursales
            comboBox.removeAllItems();

            // Agregar las sucursales obtenidas al JComboBox
            while (resultSet.next()) {
                String sucursal = resultSet.getString("suCiudad");
                comboBox.addItem(sucursal);
            }

            // Cerrar la conexión y las declaraciones
            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void agregarNuevaCuenta(JFrame parent,
    		JTextField textFieldnCuenta,JTextField textFieldSaldo,JComboBox comboBox_Sucursales,
    		JComboBox comboBox_Cliente,JComboBox<Integer> comboBox_FechaDay,JComboBox<String> comboBox_FechaMonth,
    		JComboBox<Integer> comboBox_FechaYear
    		) {
        try {
            // Obtener el número de cuenta del ComboBox
            String numeroCuenta = textFieldnCuenta.getText();
            String sucursalNombre = comboBox_Sucursales.getSelectedItem().toString();
            String saldo = textFieldSaldo.getText();
            String clienteNombre = comboBox_Cliente.getSelectedItem().toString();
            int dia = Integer.parseInt(comboBox_FechaDay.getSelectedItem().toString());
            String mes = comboBox_FechaMonth.getSelectedItem().toString();
            int año = Integer.parseInt(comboBox_FechaYear.getSelectedItem().toString());

            // Validar que todos los campos estén completos
            if (numeroCuenta.isEmpty() || sucursalNombre.isEmpty() || saldo.isEmpty() || clienteNombre.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Conectar a la base de datos
            modelo.ConexionBD CBD = new modelo.ConexionBD("localhost", "3306", "root", "", "bancoVigo");
            Connection conn = CBD.getCon();

            // Obtener el código de la sucursal
            String query = "SELECT suCodSucursal FROM sucursales WHERE suCiudad = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, sucursalNombre);
            ResultSet resultSet = statement.executeQuery();

            int sucursalCodigo = 0;
            if (resultSet.next()) {
                sucursalCodigo = resultSet.getInt("suCodSucursal");
            } else {
                JOptionPane.showMessageDialog(parent, "La sucursal seleccionada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Formar la fecha de creación en el formato adecuado para MySQL
            String fechaCreacion = String.format("%04d-%02d-%02d", año, obtenerNumeroMes(parent, mes), dia);

            // Insertar la nueva cuenta en la tabla cuenta
            String insertQuery = "INSERT INTO cuenta (cuCodCuenta, cuCodSucursal, cuFechaCreacion, CuSaldo) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, numeroCuenta);
            insertStatement.setInt(2, sucursalCodigo);
            insertStatement.setString(3, fechaCreacion);
            insertStatement.setString(4, saldo);
            int rowsAffected = insertStatement.executeUpdate();

            // Verificar si se insertó correctamente
            if (rowsAffected > 0) {
                // Obtener el DNI del cliente seleccionado
                String dniClienteQuery = "SELECT clDni FROM cliente WHERE clNombre = ?";
                PreparedStatement dniStatement = conn.prepareStatement(dniClienteQuery);
                dniStatement.setString(1, clienteNombre);
                ResultSet dniResultSet = dniStatement.executeQuery();
                String dniCliente = "";
                if (dniResultSet.next()) {
                    dniCliente = dniResultSet.getString("clDni");
                    // Insertar el DNI del cliente y el número de cuenta en la tabla cuentascliente
                    String cuentasClienteQuery = "INSERT INTO cuentascliente (ccDNI, ccCodCuenta) VALUES (?, ?)";
                    PreparedStatement cuentasClienteStatement = conn.prepareStatement(cuentasClienteQuery);
                    cuentasClienteStatement.setString(1, dniCliente);
                    cuentasClienteStatement.setString(2, numeroCuenta);
                    cuentasClienteStatement.executeUpdate();
                    JOptionPane.showMessageDialog(parent, "Se agregó correctamente la nueva cuenta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parent, "No se pudo obtener el DNI del cliente seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dniResultSet.close();
                dniStatement.close();
            } else {
                JOptionPane.showMessageDialog(parent, "No se pudo agregar la nueva cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Cerrar la conexión y las declaraciones
            resultSet.close();
            statement.close();
            insertStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parent, "Error al agregar la nueva cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarCuenta(JFrame parent,JTextField textField_N_Cuenta,JTextField textField_Saldo,JComboBox comboBox_Sucursales) {
    try {
        // Obtener el número de cuenta original
        String numeroCuentaOriginal = textField_N_Cuenta.getText();

        // Validar que el número de cuenta original no esté vacío
        if (numeroCuentaOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Por favor, introduzca el número de cuenta original.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Conectar a la base de datos
        modelo.ConexionBD CBD = new modelo.ConexionBD("localhost", "3306", "root", "", "bancoVigo");
        Connection conn = CBD.getCon();

        // Verificar si el número de cuenta original existe en la base de datos
        String verificarQuery = "SELECT COUNT(*) AS count FROM cuenta WHERE cuCodCuenta = ?";
        PreparedStatement verificarStatement = conn.prepareStatement(verificarQuery);
        verificarStatement.setString(1, numeroCuentaOriginal);
        ResultSet verificarResultSet = verificarStatement.executeQuery();
        verificarResultSet.next();
        int count = verificarResultSet.getInt("count");
        if (count == 0) {
            JOptionPane.showMessageDialog(parent, "El número de cuenta original no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de los campos de texto y combo box
        String sucursalNombre = comboBox_Sucursales.getSelectedItem().toString();
        String saldo = textField_Saldo.getText();

        // Validar que todos los campos estén completos
        if (sucursalNombre.isEmpty() || saldo.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el código de la sucursal
        String query = "SELECT suCodSucursal FROM sucursales WHERE suCiudad = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, sucursalNombre);
        ResultSet resultSet = statement.executeQuery();

        int sucursalCodigo = 0;
        if (resultSet.next()) {
            sucursalCodigo = resultSet.getInt("suCodSucursal");
        } else {
            JOptionPane.showMessageDialog(parent, "La sucursal seleccionada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar la cuenta en la tabla cuenta
        String updateQuery = "UPDATE cuenta SET cuCodSucursal = ?, CuSaldo = ? WHERE cuCodCuenta = ?";
        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        updateStatement.setInt(1, sucursalCodigo);
        updateStatement.setString(2, saldo);
        updateStatement.setString(3, numeroCuentaOriginal);
        int rowsAffected = updateStatement.executeUpdate();

        // Verificar si se actualizó correctamente
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(parent, "Se actualizó correctamente la cuenta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parent, "No se pudo actualizar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cerrar la conexión y las declaraciones
        resultSet.close();
        statement.close();
        updateStatement.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
     //   JOptionPane.showMessageDialog(this, "Error al actualizar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public void borrarCuenta(JFrame parent,JTextField textField_N_Cuenta) {
    try {
        // Obtener el número de cuenta original
        String numeroCuentaOriginal = textField_N_Cuenta.getText();

        // Validar que el número de cuenta original no esté vacío
        if (numeroCuentaOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Por favor, introduzca el número de cuenta original.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Conectar a la base de datos
        modelo.ConexionBD CBD = new modelo.ConexionBD("localhost", "3306", "root", "", "bancoVigo");
        Connection conn = CBD.getCon();

        // Eliminar las filas relacionadas en la tabla cuentascliente
        String deleteCuentasClienteQuery = "DELETE FROM cuentascliente WHERE ccCodCuenta = ?";
        PreparedStatement deleteCuentasClienteStatement = conn.prepareStatement(deleteCuentasClienteQuery);
        deleteCuentasClienteStatement.setString(1, numeroCuentaOriginal);
        int cuentasClienteRowsAffected = deleteCuentasClienteStatement.executeUpdate();

        // Verificar si se eliminaron correctamente las filas relacionadas
        if (cuentasClienteRowsAffected > 0) {
            // Si se eliminaron correctamente, eliminar la cuenta de la tabla cuenta
            String deleteCuentaQuery = "DELETE FROM cuenta WHERE cuCodCuenta = ?";
            PreparedStatement deleteCuentaStatement = conn.prepareStatement(deleteCuentaQuery);
            deleteCuentaStatement.setString(1, numeroCuentaOriginal);
            int cuentaRowsAffected = deleteCuentaStatement.executeUpdate();

            // Verificar si se eliminó correctamente la cuenta
            if (cuentaRowsAffected > 0) {
                JOptionPane.showMessageDialog(parent, "Se eliminó correctamente la cuenta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(parent, "No se pudo eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(parent, "No se pudo eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cerrar la conexión y las declaraciones
        deleteCuentasClienteStatement.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(parent, "Error al eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public int obtenerNumeroMes(JFrame parent,String nombreMes) {
        switch (nombreMes) {
            case "Enero":
                return 1;
            case "Febrero":
                return 2;
            case "Marzo":
                return 3;
            case "Abril":
                return 4;
            case "Mayo":
                return 5;
            case "Junio":
                return 6;
            case "Julio":
                return 7;
            case "Agosto":
                return 8;
            case "Septiembre":
                return 9;
            case "Octubre":
                return 10;
            case "Noviembre":
                return 11;
            case "Diciembre":
                return 12;
            default:
                return 1; 
        }
    }
   
    //------------------------------------------Ventana Listado-----------------------------------------------------------//
	public void listarCuentas(String nombreCliente,JTable table) {
	    try {
	        modelo.ConexionBD CBD = new modelo.ConexionBD("localhost", "3306", "root", "", "bancoVigo");
	        Connection con = CBD.getCon();

	        String query = "SELECT cuCodCuenta, cuCodSucursal, cuFechaCreacion, CuSaldo " +
	                       "FROM cuenta " +
	                       "JOIN cuentascliente ON cuenta.cuCodCuenta = cuentascliente.ccCodCuenta " +
	                       "JOIN cliente ON cuentascliente.ccDNI = cliente.clDni " +
	                       "WHERE cliente.clNombre = ?";

	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setString(1, nombreCliente); 

	        ResultSet rs = preparedStatement.executeQuery();
	        ResultSetMetaData rsmd = rs.getMetaData();

	        DefaultTableModel tableModel = new DefaultTableModel();

	        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
	            tableModel.addColumn(rsmd.getColumnName(i));
	        }

	        while (rs.next()) {
	            Object[] rowData = new Object[rsmd.getColumnCount()];
	            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
	                try {
	                    rowData[i - 1] = rs.getObject(i);
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }
	            }

	            tableModel.addRow(rowData);
	        }

	        table.setModel(tableModel);
	        tableModel.fireTableDataChanged();

	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }
	}
    
    //---------------------------------------Ventana Transaccion----------------------------------------------------------//
	public void realizarIngreso(JFrame parent,JComboBox comboBox,JTextField DNI,JTextField Saldo) {
	    try {
	        // Obtener los valores ingresados en los campos
	        String dniCliente = DNI.getText();
	        double cantidad = Double.parseDouble(Saldo.getText());
	        String cuentaDestino = comboBox.getSelectedItem().toString();

	        // Validar que todos los campos estén completos
	        if (dniCliente.isEmpty() || Saldo.getText().isEmpty() || cuentaDestino.isEmpty()) {
	            JOptionPane.showMessageDialog(parent, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Conectar a la base de datos
	        ConexionBD CBD = new ConexionBD("localhost", "3306", "root", "", "bancoVigo");
	        Connection conn = CBD.getCon();

	        // Obtener el ID de la cuenta destino
	        String[] parts = cuentaDestino.split(" ");
	        int cuentaDestinoID = Integer.parseInt(parts[2]);

	        // Actualizar el saldo en la base de datos
	        String updateQuery = "UPDATE cuenta SET CuSaldo = CuSaldo + ? WHERE cuCodCuenta = ?";
	        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	        updateStatement.setDouble(1, cantidad);
	        updateStatement.setInt(2, cuentaDestinoID);
	        int rowsAffected = updateStatement.executeUpdate();

	        // Verificar si se actualizó correctamente
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(parent, "Se ha realizado el ingreso correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(parent, "No se pudo realizar el ingreso.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        // Cerrar la conexión y las declaraciones
	        updateStatement.close();
	        conn.close();
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(parent, "Error al realizar el ingreso.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void realizarReintegro(JFrame parent,JComboBox comboBox, JTextField DNI, JTextField Saldo) {
	    try {
	        // Obtener los valores ingresados en los campos
	        String dniCliente = DNI.getText();
	        double cantidad = Double.parseDouble(Saldo.getText());
	        String cuentaDestino = comboBox.getSelectedItem().toString();

	        // Validar que todos los campos estén completos
	        if (dniCliente.isEmpty() || Saldo.getText().isEmpty() || cuentaDestino.isEmpty()) {
	            JOptionPane.showMessageDialog(parent, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Conectar a la base de datos
	        ConexionBD CBD = new ConexionBD("localhost", "3306", "root", "", "bancoVigo");
	        Connection conn = CBD.getCon();

	        // Obtener el ID de la cuenta destino
	        String[] parts = cuentaDestino.split(" ");
	        int cuentaDestinoID = Integer.parseInt(parts[2]);

	        // Verificar si hay suficiente saldo en la cuenta
	        String saldoQuery = "SELECT CuSaldo FROM cuenta WHERE cuCodCuenta = ?";
	        PreparedStatement saldoStatement = conn.prepareStatement(saldoQuery);
	        saldoStatement.setInt(1, cuentaDestinoID);
	        ResultSet saldoResult = saldoStatement.executeQuery();
	        saldoResult.next();
	        double saldoActual = saldoResult.getDouble("CuSaldo");
	        if (saldoActual < cantidad) {
	            JOptionPane.showMessageDialog(parent, "La cuenta no tiene suficiente saldo para realizar esta transacción.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Actualizar el saldo en la base de datos
	        String updateQuery = "UPDATE cuenta SET CuSaldo = CuSaldo - ? WHERE cuCodCuenta = ?";
	        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	        updateStatement.setDouble(1, cantidad);
	        updateStatement.setInt(2, cuentaDestinoID);
	        int rowsAffected = updateStatement.executeUpdate();

	        // Verificar si se actualizó correctamente
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(parent, "Se ha realizado el retiro correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            // Actualizar la lista del JComboBox
	            cuentasySaldo(parent,comboBox);
	        } else {
	            JOptionPane.showMessageDialog(parent, "No se pudo realizar el retiro.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        // Cerrar la conexión y las declaraciones
	        saldoStatement.close();
	        updateStatement.close();
	        conn.close();
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(parent, "Error al realizar el retiro.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void realizarTransferencia(JFrame parent,JComboBox comboBoxOrigen, JComboBox comboBoxDestino, JTextField montoField) {
	    try {
	        // Obtener los valores ingresados en los campos
	        String cuentaOrigenStr = comboBoxOrigen.getSelectedItem().toString();
	        String cuentaDestinoStr = comboBoxDestino.getSelectedItem().toString();
	        double monto = Double.parseDouble(montoField.getText());

	        // Validar que todos los campos estén completos
	        if (cuentaOrigenStr.isEmpty() || cuentaDestinoStr.isEmpty() || montoField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(parent, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Obtener los IDs de las cuentas origen y destino
	        String[] partsOrigen = cuentaOrigenStr.split(" ");
	        int cuentaOrigenID = Integer.parseInt(partsOrigen[2]);

	        String[] partsDestino = cuentaDestinoStr.split(" ");
	        int cuentaDestinoID = Integer.parseInt(partsDestino[2]);

	        // Conectar a la base de datos
	        ConexionBD CBD = new ConexionBD("localhost", "3306", "root", "", "bancoVigo");
	        Connection conn = CBD.getCon();

	        // Verificar si hay suficiente saldo en la cuenta de origen
	        String saldoQuery = "SELECT CuSaldo FROM cuenta WHERE cuCodCuenta = ?";
	        PreparedStatement saldoStatement = conn.prepareStatement(saldoQuery);
	        saldoStatement.setInt(1, cuentaOrigenID);
	        ResultSet saldoResult = saldoStatement.executeQuery();
	        saldoResult.next();
	        double saldoOrigen = saldoResult.getDouble("CuSaldo");
	        if (saldoOrigen < monto) {
	            JOptionPane.showMessageDialog(parent, "La cuenta de origen no tiene suficiente saldo para realizar esta transacción.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Actualizar el saldo de las cuentas en la base de datos
	        String updateQueryOrigen = "UPDATE cuenta SET CuSaldo = CuSaldo - ? WHERE cuCodCuenta = ?";
	        PreparedStatement updateStatementOrigen = conn.prepareStatement(updateQueryOrigen);
	        updateStatementOrigen.setDouble(1, monto);
	        updateStatementOrigen.setInt(2, cuentaOrigenID);
	        int rowsAffectedOrigen = updateStatementOrigen.executeUpdate();

	        String updateQueryDestino = "UPDATE cuenta SET CuSaldo = CuSaldo + ? WHERE cuCodCuenta = ?";
	        PreparedStatement updateStatementDestino = conn.prepareStatement(updateQueryDestino);
	        updateStatementDestino.setDouble(1, monto);
	        updateStatementDestino.setInt(2, cuentaDestinoID);
	        int rowsAffectedDestino = updateStatementDestino.executeUpdate();

	        // Verificar si se realizaron correctamente ambas actualizaciones
	        if (rowsAffectedOrigen > 0 && rowsAffectedDestino > 0) {
	            JOptionPane.showMessageDialog(parent, "Se ha realizado la transferencia correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            // Actualizar las listas de los JComboBox
	            cuentasySaldo(parent,comboBoxOrigen);
	            cuentasySaldo(parent,comboBoxDestino);
	        } else {
	            JOptionPane.showMessageDialog(parent, "No se pudo realizar la transferencia.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        // Cerrar la conexión y las declaraciones
	        saldoStatement.close();
	        updateStatementOrigen.close();
	        updateStatementDestino.close();
	        conn.close();
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(parent, "Error al realizar la transferencia.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void cuentasySaldo(JFrame parent,JComboBox<String> comboBox) {
        // Conectar a la base de datos
        ConexionBD CBD = new ConexionBD("localhost", "3306", "root", "", "bancoVigo");
        try (Connection conn = CBD.getCon()) {
            // Consulta para obtener los números de cuenta y los saldos
            String query = "SELECT cuenta.cuCodCuenta, cuenta.CuSaldo, cliente.clNombre, cliente.clApellido " + 
                           "FROM cuenta " +
                           "INNER JOIN cuentascliente ON cuenta.cuCodCuenta = cuentascliente.ccCodCuenta " +
                           "INNER JOIN cliente ON cuentascliente.ccDNI = cliente.clDni";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Crear una lista para almacenar los números de cuenta y los saldos concatenados
            ArrayList<String> cuentasSaldos = new ArrayList<>();

            // Iterar sobre los resultados y concatenar los números de cuenta y los saldos
            while (resultSet.next()) {
                int codigoCuenta = resultSet.getInt("cuCodCuenta");
                double saldo = resultSet.getDouble("CuSaldo");
                String nombreCliente = resultSet.getString("clNombre");
                String apellidoCliente = resultSet.getString("clApellido");
                cuentasSaldos.add("ID cuenta: "+codigoCuenta + " | Cliente: " + nombreCliente + " " + apellidoCliente + " | Saldo: " + saldo +" $");
            }

            // Convertir la lista en un arreglo
            String[] cuentasSaldosArray = cuentasSaldos.toArray(new String[0]);

            // Establecer los números de cuenta y los saldos en el comboBox
            comboBox.removeAllItems();
            for (String cuentaSaldo : cuentasSaldosArray) {
                comboBox.addItem(cuentaSaldo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parent, "Error al actualizar los números de cuenta y saldos en el ComboBox.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
