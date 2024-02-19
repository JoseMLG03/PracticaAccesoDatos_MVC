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

public class VentanaTransaccion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(222, 51, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBounds(73, 51, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_2.setBounds(369, 51, 109, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		textField = new JTextField();
		textField.setBounds(73, 113, 109, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(42, 116, 38, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccionar Cuenta Cliente:");
		lblNewLabel_2.setBounds(192, 116, 171, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(350, 112, 157, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(24, 156, 59, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(90, 152, 109, 22);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Seleccionar Cuenta Destinatario:");
		lblNewLabel_4.setBounds(42, 235, 173, 14);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(224, 231, 254, 22);
		contentPane.add(comboBox_2);
		
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
		btnSalir.setBounds(418, 16, 89, 23);
		contentPane.add(btnSalir);
	}
}
