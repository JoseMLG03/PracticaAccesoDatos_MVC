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
	Controlador controlador;
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
	                Controlador controlador = new Controlador();
	                controlador.iniciar();
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
		controlador = new Controlador();
		
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
		controlador.cargarClientesEnComboBox(comboBox_Cliente);                		
		//CargarClientesEnComboBox(comboBox_Cliente);
		                		 
		JComboBox comboBox_Sucursales = new JComboBox();
		comboBox_Sucursales.setBounds(103, 86, 286, 22);
		panel.add(comboBox_Sucursales);
		controlador.cargarSucursalesEnComboBox(comboBox_Sucursales);
		//cargarSucursalesEnComboBox(comboBox_Sucursales);
		
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
				controlador.borrarCuenta(textField_N_Cuenta);
				//borrarCuenta(textField_N_Cuenta, joptionpane);
			}
		});
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.actualizarCuenta(textField_N_Cuenta, textField_Saldo, comboBox_Sucursales);
				//actualizarCuenta(textField_N_Cuenta, textField_Saldo, comboBox_Sucursales);
			}
		});
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.agregarNuevaCuenta(textField_N_Cuenta,textField_Saldo,comboBox_Sucursales,comboBox_Cliente,comboBox_FechaDay,comboBox_FechaMonth,comboBox_FechaYear);
				//agregarNuevaCuenta(textField_N_Cuenta,textField_Saldo,comboBox_Sucursales,comboBox_Cliente,comboBox_FechaDay,comboBox_FechaMonth,comboBox_FechaYear);
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
