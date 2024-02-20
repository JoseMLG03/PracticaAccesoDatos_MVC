package vista;

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

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	static VentanaPrincipal VentanaPrincipalframe = new VentanaPrincipal();
	static VentanaListado ventanaListadoframe = new VentanaListado();
	static VentanaTransaccion ventanaTransaccionFrame = new VentanaTransaccion();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
						VentanaPrincipalframe.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		JOptionPane joptionpane = null;
		
		Controlador controlador = new Controlador();
		controlador.iniciar();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 299);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cuentas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(32, 8, 463, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextField textField_N_Cuenta = new JTextField();
		textField_N_Cuenta.setBounds(103, 16, 286, 20);
		panel.add(textField_N_Cuenta);
		textField_N_Cuenta.setColumns(10);
		
		JLabel lblN_Cuenta = new JLabel("N de cuenta:");
		lblN_Cuenta.setBounds(10, 21, 87, 14);
		panel.add(lblN_Cuenta);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 52, 70, 14);
		panel.add(lblCliente);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 130, 46, 14);
		panel.add(lblFecha);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(294, 132, 46, 14);
		panel.add(lblSaldo);
		
		JTextField textField_Saldo = new JTextField();
		textField_Saldo.setBounds(338, 130, 100, 20);
		panel.add(textField_Saldo);
		textField_Saldo.setColumns(10);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(10, 89, 70, 14);
		panel.add(lblSucursal);
		
		JComboBox<Integer> comboBox_FechaDay = new JComboBox<>();
		comboBox_FechaDay.setBounds(63, 127, 46, 22);
		panel.add(comboBox_FechaDay);
		
		JComboBox<String> comboBox_FechaMonth = new JComboBox<>();
		comboBox_FechaMonth.setBounds(113, 127, 100, 22);
		panel.add(comboBox_FechaMonth);
		        
		JComboBox<Integer> comboBox_FechaYear = new JComboBox<>();
		comboBox_FechaYear.setBounds(218, 127, 66, 22);
		panel.add(comboBox_FechaYear);
		                
		                		
		JComboBox comboBox_Cliente = new JComboBox();
		comboBox_Cliente.setBounds(103, 47, 286, 22);
		panel.add(comboBox_Cliente);
		                		
		cargarClientesEnComboBox(comboBox_Cliente);
		                		 
		JComboBox comboBox_Sucursales = new JComboBox();
		comboBox_Sucursales.setBounds(103, 86, 286, 22);
		panel.add(comboBox_Sucursales);
		cargarSucursalesEnComboBox(comboBox_Sucursales);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Modificacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(32, 166, 115, 90);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(6, 16, 103, 15);
		panel_1.add(btnNuevo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(6, 67, 103, 15);
		panel_1.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar ");
		btnEliminar.setBounds(6, 41, 103, 15);
		panel_1.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCuenta(textField_N_Cuenta, joptionpane);
			}
		});
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarCuenta(textField_N_Cuenta, textField_Saldo, comboBox_Sucursales);
			}
		});
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarNuevaCuenta(textField_N_Cuenta,textField_Saldo,comboBox_Sucursales,comboBox_Cliente,comboBox_FechaDay,comboBox_FechaMonth,comboBox_FechaYear);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Gestion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(360, 168, 135, 82);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnListado = new JButton("Listado");
		btnListado.setBounds(6, 53, 123, 23);
		panel_2.add(btnListado);
		
				JButton btnTransaccion = new JButton("Transaccion");
				btnTransaccion.setBounds(6, 16, 123, 23);
				panel_2.add(btnTransaccion);
				btnTransaccion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VentanaPrincipalframe.setVisible(false);
						ventanaTransaccionFrame.setVisible(true);
					}
				});
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListadoframe.setVisible(true);
				VentanaPrincipalframe.setVisible(false);
				
			}
		});
        IntStream.rangeClosed(1, 31).forEach(comboBox_FechaDay::addItem);
        String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre","Octubre", "Noviembre", "Diciembre" };
        for (String mes : meses) {
            comboBox_FechaMonth.addItem(mes);
        }
        int currentYear = LocalDate.now().getYear();
        IntStream.rangeClosed(1900, currentYear).forEach(comboBox_FechaYear::addItem);
		 
	}
	
	
	private void cargarClientesEnComboBox(JComboBox comboBox) {
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
	
    private void cargarSucursalesEnComboBox(JComboBox<String> comboBox) {
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
    
    private void agregarNuevaCuenta(
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
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, "La sucursal seleccionada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Formar la fecha de creación en el formato adecuado para MySQL
            String fechaCreacion = String.format("%04d-%02d-%02d", año, obtenerNumeroMes(mes), dia);

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
                    JOptionPane.showMessageDialog(this, "Se agregó correctamente la nueva cuenta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo obtener el DNI del cliente seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dniResultSet.close();
                dniStatement.close();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar la nueva cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Cerrar la conexión y las declaraciones
            resultSet.close();
            statement.close();
            insertStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar la nueva cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCuenta(JTextField textField_N_Cuenta,JTextField textField_Saldo,JComboBox comboBox_Sucursales) {
    try {
        // Obtener el número de cuenta original
        String numeroCuentaOriginal = textField_N_Cuenta.getText();

        // Validar que el número de cuenta original no esté vacío
        if (numeroCuentaOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduzca el número de cuenta original.", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "El número de cuenta original no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos de los campos de texto y combo box
        String sucursalNombre = comboBox_Sucursales.getSelectedItem().toString();
        String saldo = textField_Saldo.getText();

        // Validar que todos los campos estén completos
        if (sucursalNombre.isEmpty() || saldo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "La sucursal seleccionada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Se actualizó correctamente la cuenta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cerrar la conexión y las declaraciones
        resultSet.close();
        statement.close();
        updateStatement.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al actualizar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void borrarCuenta(JTextField textField_N_Cuenta,JOptionPane joptionpane) {
    try {
        // Obtener el número de cuenta original
        String numeroCuentaOriginal = textField_N_Cuenta.getText();

        // Validar que el número de cuenta original no esté vacío
        if (numeroCuentaOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduzca el número de cuenta original.", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, "Se eliminó correctamente la cuenta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cerrar la conexión y las declaraciones
        deleteCuentasClienteStatement.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private int obtenerNumeroMes(String nombreMes) {
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
    
    
	public static VentanaListado getVentanaListadoframe() {
		return ventanaListadoframe;
	}
	
	public static void setVentanaListadoframe(VentanaListado ventanaListadoframe) {
		VentanaPrincipal.ventanaListadoframe = ventanaListadoframe;
	}
	
	public static VentanaTransaccion getVentanaTransaccionFrame() {
		return ventanaTransaccionFrame;
	}
	
	public static void setVentanaTransaccionFrame(VentanaTransaccion ventanaTransaccionFrame) {
		VentanaPrincipal.ventanaTransaccionFrame = ventanaTransaccionFrame;
	}
	
	public static VentanaPrincipal getVentanaPrincipalframe() {
		return VentanaPrincipalframe;
	}
	
	public static void setVentanaPrincipalframe(VentanaPrincipal ventanaPrincipalframe) {
		VentanaPrincipalframe = ventanaPrincipalframe;
	}
}
