package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_IdProducto;
	private JTextField textField_Descripcion;
	private JTextField textField__PrecioCompra;
	private JTextField textField_Existencias;
	private JTextField textField_Proveedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormProducto frame = new FormProducto();
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
	public FormProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_IdProducto = new JLabel("Id Producto");
		lbl_IdProducto.setBounds(28, 41, 90, 14);
		contentPane.add(lbl_IdProducto);
		
		JLabel lbl_Descripcion = new JLabel("Descripcion");
		lbl_Descripcion.setBounds(28, 66, 90, 14);
		contentPane.add(lbl_Descripcion);
		
		JLabel lbl_Preciocompra = new JLabel("Precio Compra");
		lbl_Preciocompra.setBounds(28, 91, 90, 14);
		contentPane.add(lbl_Preciocompra);
		
		JLabel lbl_Existencias = new JLabel("Existencias");
		lbl_Existencias.setBounds(28, 116, 90, 14);
		contentPane.add(lbl_Existencias);
		
		JLabel lbl_Proveedor = new JLabel("Id Proveedor");
		lbl_Proveedor.setBounds(28, 141, 90, 14);
		contentPane.add(lbl_Proveedor);
		
		textField_IdProducto = new JTextField();
		textField_IdProducto.setBounds(128, 38, 126, 20);
		contentPane.add(textField_IdProducto);
		textField_IdProducto.setColumns(10);
		
		textField_Descripcion = new JTextField();
		textField_Descripcion.setColumns(10);
		textField_Descripcion.setBounds(128, 63, 126, 20);
		contentPane.add(textField_Descripcion);
		
		textField__PrecioCompra = new JTextField();
		textField__PrecioCompra.setColumns(10);
		textField__PrecioCompra.setBounds(128, 88, 126, 20);
		contentPane.add(textField__PrecioCompra);
		
		textField_Existencias = new JTextField();
		textField_Existencias.setColumns(10);
		textField_Existencias.setBounds(128, 113, 126, 20);
		contentPane.add(textField_Existencias);
		
		textField_Proveedor = new JTextField();
		textField_Proveedor.setColumns(10);
		textField_Proveedor.setBounds(128, 138, 126, 20);
		contentPane.add(textField_Proveedor);
	}
}
