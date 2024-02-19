package vista;

import java.awt.EventQueue;
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
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_N_Cuenta;
	private JTextField textField_Saldo;
	static VentanaPrincipal VentanaPrincipalframe = new VentanaPrincipal();
	static VentanaListado ventanaListadoframe = new VentanaListado();
	static VentanaTransaccion ventanaTransaccionFrame = new VentanaTransaccion();
	public JOptionPane joptionpane;
	JComboBox comboBox_Sucursales;
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
		
		Controlador controlador = new Controlador();
		controlador.iniciar();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_N_Cuenta = new JTextField();
		textField_N_Cuenta.setBounds(135, 35, 286, 20);
		contentPane.add(textField_N_Cuenta);
		textField_N_Cuenta.setColumns(10);
		
		JLabel lblN_Cuenta = new JLabel("N de cuenta:");
		lblN_Cuenta.setBounds(38, 41, 87, 14);
		contentPane.add(lblN_Cuenta);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(38, 72, 70, 14);
		contentPane.add(lblCliente);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(38, 150, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(345, 149, 46, 14);
		contentPane.add(lblSaldo);
		
		textField_Saldo = new JTextField();
		textField_Saldo.setColumns(10);
		textField_Saldo.setBounds(389, 147, 100, 20);
		contentPane.add(textField_Saldo);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(38, 109, 70, 14);
		contentPane.add(lblSucursal);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				agregarNuevaCuenta();
				
			}
		});
		btnNuevo.setBounds(38, 175, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(389, 177, 100, 23);
		contentPane.add(btnActualizar);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListadoframe.setVisible(true);
				VentanaPrincipalframe.setVisible(false);
				
			}
		});
		btnListado.setBounds(389, 211, 100, 23);
		contentPane.add(btnListado);
		
		JButton btnEliminar = new JButton("Eliminar ");
		btnEliminar.setBounds(38, 209, 89, 23);
		contentPane.add(btnEliminar);
		
		JComboBox<Integer> comboBox_FechaDay = new JComboBox<>();
        comboBox_FechaDay.setBounds(95, 146, 46, 22);
        IntStream.rangeClosed(1, 31).forEach(comboBox_FechaDay::addItem);
        contentPane.add(comboBox_FechaDay);

        JComboBox<String> comboBox_FechaMonth = new JComboBox<>();
        comboBox_FechaMonth.setBounds(145, 146, 100, 22);
        String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
                "Octubre", "Noviembre", "Diciembre" };
        for (String mes : meses) {
            comboBox_FechaMonth.addItem(mes);
        }	
        contentPane.add(comboBox_FechaMonth);

        JComboBox<Integer> comboBox_FechaYear = new JComboBox<>();
        comboBox_FechaYear.setBounds(250, 146, 66, 22);
        int currentYear = LocalDate.now().getYear();
        IntStream.rangeClosed(1900, currentYear).forEach(comboBox_FechaYear::addItem);
        contentPane.add(comboBox_FechaYear);

		
		JComboBox comboBox_Cliente = new JComboBox();
		comboBox_Cliente.setBounds(135, 66, 286, 22);
		contentPane.add(comboBox_Cliente);
		
		comboBox_Sucursales = new JComboBox();
		comboBox_Sucursales.setBounds(135, 105, 286, 22);
		contentPane.add(comboBox_Sucursales);
		
		 cargarClientesEnComboBox(comboBox_Cliente);
		 cargarSucursalesEnComboBox(comboBox_Sucursales);
		 
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
    
    private void agregarNuevaCuenta() {
    try {
        // Obtener los datos de los campos de texto
        String numeroCuenta = textField_N_Cuenta.getText();
        String sucursalNombre = comboBox_Sucursales.getSelectedItem().toString();
        String saldo = textField_Saldo.getText();

        // Validar que todos los campos estén completos
        if (numeroCuenta.isEmpty() || sucursalNombre.isEmpty() || saldo.isEmpty()) {
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

        // Insertar la nueva cuenta en la tabla cuenta
        String insertQuery = "INSERT INTO cuenta (cuCodSucursal, cuFechaCreacion, CuSaldo) VALUES (?, CURDATE(), ?)";
        PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
        insertStatement.setInt(1, sucursalCodigo);
        insertStatement.setString(2, saldo);
        int rowsAffected = insertStatement.executeUpdate();

        // Verificar si se insertó correctamente
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Se agregó correctamente la nueva cuenta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
