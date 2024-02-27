package vista;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.ConexionBD;

import javax.swing.ButtonGroup;

public class VentanaTransaccion extends JFrame {
	Controlador controlador;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
		controlador = new Controlador();
		
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
		rdbtnTransferencia.setSelected(true);
		
		JTextField textField = new JTextField();
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
		panel_1.setBounds(41, 119, 484, 76);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(154, 20, 59, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4_1 = new JLabel("Seleccionar Cuenta:");
		lblNewLabel_4_1.setBounds(10, 47, 120, 14);
		panel_1.add(lblNewLabel_4_1);
		
		JComboBox comboBox_2_1 = new JComboBox();
		comboBox_2_1.setBounds(135, 43, 339, 22);
		panel_1.add(comboBox_2_1);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(208, 17, 266, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("DNI:");
		lblNewLabel_5.setBounds(10, 20, 46, 14);
		panel_1.add(lblNewLabel_5);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(35, 17, 109, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Destinatario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(40, 195, 474, 48);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Seleccionar Cuenta:");
		lblNewLabel_4.setBounds(10, 20, 127, 14);
		panel_2.add(lblNewLabel_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(128, 16, 325, 22);
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
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnIngreso.isSelected()) {
					controlador.realizarIngreso(comboBox_2_1,textField_2,textField_1);
					controlador.cuentasySaldo(comboBox_2_1);
					controlador.cuentasySaldo(comboBox_2);
					//realizarIngreso(comboBox_2_1,textField_2,textField_1);
					//cuentasySaldo(comboBox_2_1);
					//cuentasySaldo(comboBox_2);
				}
				if(rdbtnReintegro.isSelected()) {
					controlador.realizarReintegro(comboBox_2_1,textField_2,textField_1);
					controlador.cuentasySaldo(comboBox_2_1);
					controlador.cuentasySaldo(comboBox_2);
					//realizarReintegro(comboBox_2_1,textField_2,textField_1);
					//cuentasySaldo(comboBox_2_1);
					//cuentasySaldo(comboBox_2);
				}
				if(rdbtnTransferencia.isSelected()) {
					controlador.realizarTransferencia(comboBox_2_1,comboBox_2,textField_1);
					controlador.cuentasySaldo(comboBox_2_1);
					controlador.cuentasySaldo(comboBox_2);
					//realizarTransferencia(comboBox_2_1,comboBox_2,textField_1);
					//cuentasySaldo(comboBox_2_1);
					//cuentasySaldo(comboBox_2);
				}
				
			}
		});
		btnConfirmar.setBounds(323, 249, 103, 23);
		contentPane.add(btnConfirmar);
		
		controlador.cuentasySaldo(comboBox_2_1);
		controlador.cuentasySaldo(comboBox_2);
		//cuentasySaldo(comboBox_2_1);
		//cuentasySaldo(comboBox_2);
		ControladorVisibilidadDestinatario(rdbtnTransferencia,rdbtnReintegro,rdbtnIngreso,comboBox_2);
		
		
	}
	private void ControladorVisibilidadDestinatario(JRadioButton rdbtnTransferencia,JRadioButton rdbtnReintegro,JRadioButton rdbtnIngreso,JComboBox comboBox) {
		rdbtnTransferencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnTransferencia.isSelected()) {
                	comboBox.setEnabled(true);
                } 
            }
        });
        rdbtnReintegro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	comboBox.setEnabled(false);
            }
        });
        rdbtnIngreso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	comboBox.setEnabled(false);
            }
        });
	}
}
