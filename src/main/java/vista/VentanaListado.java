package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.ConexionBD;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class VentanaListado extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	JComboBox comboBox;
	//static VentanaListado ventanaListado = new VentanaListado();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//ventanaListado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Listado Cuentas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(210, 11, 104, 36);
		contentPane.add(lblNewLabel);
		
		
		
		

	    
		JLabel lblNewLabel_1 = new JLabel("Seleccionar Cliente:");
		lblNewLabel_1.setBounds(30, 62, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btn_Salir = new JButton("Salir");
		btn_Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal VP = new VentanaPrincipal();
				
				VentanaPrincipal vp = VP.getVentanaPrincipalframe();
				VentanaListado VL = VP.getVentanaListadoframe();
				VL.setVisible(false);
				vp.setVisible(true);
			}
		});
		btn_Salir.setBounds(436, 58, 74, 23);
		contentPane.add(btn_Salir);

        

        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 500, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox<>();
	    comboBox.setBounds(154, 58, 260, 22);
	    contentPane.add(comboBox);
	    cargarClientes(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarCuentas(comboBox.getSelectedItem().toString());
			}
		});
	}																																																																	
	
	public void cargarClientes(JComboBox<String> comboBox) {
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
	public void listarCuentas(String nombreCliente) {
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

	        tableModel = new DefaultTableModel();

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
}



