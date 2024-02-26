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

import modelo.ConexionBD;

import javax.swing.ButtonGroup;

public class VentanaTransaccion extends JFrame {

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
					realizarIngreso(comboBox_2_1,textField_2,textField_1);
					cuentasySaldo(comboBox_2_1);
					cuentasySaldo(comboBox_2);
				}
				if(rdbtnReintegro.isSelected()) {
					realizarReintegro(comboBox_2_1,textField_2,textField_1);
					cuentasySaldo(comboBox_2_1);
					cuentasySaldo(comboBox_2);
				}
				if(rdbtnTransferencia.isSelected()) {
					realizarTransferencia(comboBox_2_1,comboBox_2,textField_1);
					cuentasySaldo(comboBox_2_1);
					cuentasySaldo(comboBox_2);
				}
				
			}
		});
		btnConfirmar.setBounds(323, 249, 103, 23);
		contentPane.add(btnConfirmar);
		
		cuentasySaldo(comboBox_2_1);
		cuentasySaldo(comboBox_2);
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
	private void cuentasySaldo(JComboBox<String> comboBox) {
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
            JOptionPane.showMessageDialog(this, "Error al actualizar los números de cuenta y saldos en el ComboBox.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	private void realizarIngreso(JComboBox comboBox,JTextField DNI,JTextField Saldo) {
	    try {
	        // Obtener los valores ingresados en los campos
	        String dniCliente = DNI.getText();
	        double cantidad = Double.parseDouble(Saldo.getText());
	        String cuentaDestino = comboBox.getSelectedItem().toString();

	        // Validar que todos los campos estén completos
	        if (dniCliente.isEmpty() || Saldo.getText().isEmpty() || cuentaDestino.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
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
	            JOptionPane.showMessageDialog(this, "Se ha realizado el ingreso correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "No se pudo realizar el ingreso.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        // Cerrar la conexión y las declaraciones
	        updateStatement.close();
	        conn.close();
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al realizar el ingreso.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	private void realizarReintegro(JComboBox comboBox, JTextField DNI, JTextField Saldo) {
	    try {
	        // Obtener los valores ingresados en los campos
	        String dniCliente = DNI.getText();
	        double cantidad = Double.parseDouble(Saldo.getText());
	        String cuentaDestino = comboBox.getSelectedItem().toString();

	        // Validar que todos los campos estén completos
	        if (dniCliente.isEmpty() || Saldo.getText().isEmpty() || cuentaDestino.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
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
	            JOptionPane.showMessageDialog(this, "La cuenta no tiene suficiente saldo para realizar esta transacción.", "Error", JOptionPane.ERROR_MESSAGE);
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
	            JOptionPane.showMessageDialog(this, "Se ha realizado el retiro correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            // Actualizar la lista del JComboBox
	            cuentasySaldo(comboBox);
	        } else {
	            JOptionPane.showMessageDialog(this, "No se pudo realizar el retiro.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        // Cerrar la conexión y las declaraciones
	        saldoStatement.close();
	        updateStatement.close();
	        conn.close();
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al realizar el retiro.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	private void realizarTransferencia(JComboBox comboBoxOrigen, JComboBox comboBoxDestino, JTextField montoField) {
	    try {
	        // Obtener los valores ingresados en los campos
	        String cuentaOrigenStr = comboBoxOrigen.getSelectedItem().toString();
	        String cuentaDestinoStr = comboBoxDestino.getSelectedItem().toString();
	        double monto = Double.parseDouble(montoField.getText());

	        // Validar que todos los campos estén completos
	        if (cuentaOrigenStr.isEmpty() || cuentaDestinoStr.isEmpty() || montoField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
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
	            JOptionPane.showMessageDialog(this, "La cuenta de origen no tiene suficiente saldo para realizar esta transacción.", "Error", JOptionPane.ERROR_MESSAGE);
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
	            JOptionPane.showMessageDialog(this, "Se ha realizado la transferencia correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            // Actualizar las listas de los JComboBox
	            cuentasySaldo(comboBoxOrigen);
	            cuentasySaldo(comboBoxDestino);
	        } else {
	            JOptionPane.showMessageDialog(this, "No se pudo realizar la transferencia.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        // Cerrar la conexión y las declaraciones
	        saldoStatement.close();
	        updateStatementOrigen.close();
	        updateStatementDestino.close();
	        conn.close();
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al realizar la transferencia.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
