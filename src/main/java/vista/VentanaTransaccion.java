package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;

public class VentanaTransaccion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//VentanaTransaccion frame = new VentanaTransaccion();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaTransaccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transacciones");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(222, 11, 103, 33);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Seleccionar Operacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(61, 50, 417, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnReintegro = new JRadioButton("Reintegro");
		buttonGroup.add(rdbtnReintegro);
		rdbtnReintegro.setBounds(155, 16, 109, 23);
		panel.add(rdbtnReintegro);
		
		JRadioButton rdbtnIngreso = new JRadioButton("Ingreso");
		buttonGroup.add(rdbtnIngreso);
		rdbtnIngreso.setBounds(6, 16, 109, 23);
		panel.add(rdbtnIngreso);
		
		JRadioButton rdbtnTransferencia = new JRadioButton("Transferencia");
		buttonGroup.add(rdbtnTransferencia);
		rdbtnTransferencia.setBounds(302, 16, 109, 23);
		panel.add(rdbtnTransferencia);
		
		
		
		textField = new JTextField();
		textField.setBounds(37, 17, 109, 20);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(6, 20, 38, 14);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccionar Cuenta:");
		lblNewLabel_2.setBounds(156, 20, 109, 14);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(255, 16, 157, 22);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 119, 515, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(392, 20, 59, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4_1 = new JLabel("Seleccionar Cuenta:");
		lblNewLabel_4_1.setBounds(148, 20, 120, 14);
		panel_1.add(lblNewLabel_4_1);
		
		JComboBox comboBox_2_1 = new JComboBox();
		comboBox_2_1.setBounds(273, 16, 109, 22);
		panel_1.add(comboBox_2_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(446, 17, 59, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("DNI:");
		lblNewLabel_5.setBounds(10, 20, 46, 14);
		panel_1.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(35, 17, 109, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Destinatario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(20, 182, 474, 48);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Seleccionar Cuenta Destinatario:");
		lblNewLabel_4.setBounds(6, 20, 192, 14);
		panel_2.add(lblNewLabel_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(200, 16, 253, 22);
		panel_2.add(comboBox_2);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal VP = new VentanaPrincipal();
				
				VentanaPrincipal vp = VP.getVentanaPrincipalframe();
				VentanaTransaccion VT = VP.getVentanaTransaccionFrame();
				VT.setVisible(false);
				vp.setVisible(true);
			}
		});
		btnSalir.setBounds(436, 249, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(323, 249, 103, 23);
		contentPane.add(btnConfirmar);
	}
}
