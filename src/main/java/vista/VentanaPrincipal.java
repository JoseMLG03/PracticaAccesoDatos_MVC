package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_N_Cuenta;
	private JTextField textField_Saldo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 288);
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
		lblCliente.setBounds(38, 72, 46, 14);
		contentPane.add(lblCliente);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(38, 150, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(279, 149, 46, 14);
		contentPane.add(lblSaldo);
		
		textField_Saldo = new JTextField();
		textField_Saldo.setColumns(10);
		textField_Saldo.setBounds(335, 146, 86, 20);
		contentPane.add(textField_Saldo);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(38, 109, 46, 14);
		contentPane.add(lblSucursal);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(38, 175, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(335, 177, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnListado = new JButton("Listado");
		btnListado.setBounds(335, 211, 89, 23);
		contentPane.add(btnListado);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(38, 209, 89, 23);
		contentPane.add(btnEliminar);
		
		JComboBox comboBox_FechaDay = new JComboBox();
		comboBox_FechaDay.setBounds(95, 146, 46, 22);
		contentPane.add(comboBox_FechaDay);
		
		JComboBox comboBox_FechaMonth = new JComboBox();
		comboBox_FechaMonth.setBounds(145, 146, 46, 22);
		contentPane.add(comboBox_FechaMonth);
		
		JComboBox comboBox_FechaYear = new JComboBox();
		comboBox_FechaYear.setBounds(195, 146, 46, 22);
		contentPane.add(comboBox_FechaYear);
		
		JComboBox comboBox_Cliente = new JComboBox();
		comboBox_Cliente.setBounds(135, 66, 286, 22);
		contentPane.add(comboBox_Cliente);
		
		JComboBox comboBox_Sucursales = new JComboBox();
		comboBox_Sucursales.setBounds(135, 105, 286, 22);
		contentPane.add(comboBox_Sucursales);
	}
}
