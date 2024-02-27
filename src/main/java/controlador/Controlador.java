package controlador;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.ConexionBD;
import modelo.Logica;
import vista.VentanaListado;
import vista.VentanaPrincipal;
import vista.VentanaTransaccion;

public class Controlador {
	private VentanaPrincipal ventanaPrincipal;
	private VentanaListado ventanaListado;
	private VentanaTransaccion ventanaTransaccion;
    private ConexionBD modeloConexionBD;
    private Logica logica;
    
    
    public void iniciar() {
    }
    
    public Controlador() {
        this.modeloConexionBD = new ConexionBD("localhost", "3306", "root", "","bancovigo");//Conexion
        this.logica = new Logica();
        
    }
    
    public void cargarClientesEnComboBox(JComboBox comboBox) {
        logica.cargarClientesEnComboBox(ventanaListado, comboBox);
    }

    public void cargarSucursalesEnComboBox(JComboBox<String> comboBox) {
        logica.cargarSucursalesEnComboBox(ventanaListado,comboBox);
    }

    public void agregarNuevaCuenta(JTextField textFieldnCuenta, JTextField textFieldSaldo,
            JComboBox comboBox_Sucursales, JComboBox comboBox_Cliente, JComboBox<Integer> comboBox_FechaDay,
            JComboBox<String> comboBox_FechaMonth, JComboBox<Integer> comboBox_FechaYear) {
        logica.agregarNuevaCuenta(ventanaListado,textFieldnCuenta, textFieldSaldo, comboBox_Sucursales, comboBox_Cliente,
                comboBox_FechaDay, comboBox_FechaMonth, comboBox_FechaYear);
    }

    public void actualizarCuenta(JTextField textField_N_Cuenta, JTextField textField_Saldo,
            JComboBox comboBox_Sucursales) {
        logica.actualizarCuenta(ventanaListado,textField_N_Cuenta, textField_Saldo, comboBox_Sucursales);
    }

    public void borrarCuenta(JTextField textField_N_Cuenta) {
        logica.borrarCuenta(ventanaListado,textField_N_Cuenta);
    }

    public void listarCuentas(String nombreCliente, JTable table) {
        logica.listarCuentas(nombreCliente, table);
    }

    public void realizarIngreso(JComboBox comboBox, JTextField DNI, JTextField Saldo) {
        logica.realizarIngreso(ventanaTransaccion,comboBox, DNI, Saldo);
    }

    public void realizarReintegro(JComboBox comboBox, JTextField DNI, JTextField Saldo) {
        logica.realizarReintegro(ventanaTransaccion,comboBox, DNI, Saldo);
    }

    public void realizarTransferencia(JComboBox comboBoxOrigen, JComboBox comboBoxDestino, JTextField montoField) {
        logica.realizarTransferencia(ventanaTransaccion,comboBoxOrigen, comboBoxDestino, montoField);
    }

    public void cuentasySaldo(JComboBox<String> comboBox) {
        logica.cuentasySaldo(ventanaTransaccion,comboBox);
    }
    
}
