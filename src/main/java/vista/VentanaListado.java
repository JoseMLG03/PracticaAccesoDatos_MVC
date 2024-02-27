package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
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
	Controlador controlador;
	//static VentanaListado ventanaListado = new VentanaListado();
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
	public VentanaListado() {
		controlador = new Controlador();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 396);
		JPanel contentPane = new JPanel();
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
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox comboBox = new JComboBox<>();
	    comboBox.setBounds(154, 58, 260, 22);
	    contentPane.add(comboBox);
	    controlador.cargarClientesEnComboBox(comboBox);
	    //cargarClientes(comboBox);
	    
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.listarCuentas(comboBox.getSelectedItem().toString(),table);
				//listarCuentas(comboBox.getSelectedItem().toString(),table);
			}
		});
	}																																																																	
	
}



