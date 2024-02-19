package controlador;

import modelo.ConexionBD;
import modelo.Logica;
import vista.VentanaPrincipal;

public class Controlador {
	private VentanaPrincipal vista;
    private ConexionBD modeloConexionBD;
    private Logica  logica;
    
    public void iniciar() {
        //vista.setVisible(true);
    }
    
    public Controlador() {
        this.modeloConexionBD = new ConexionBD("localhost", "3306", "root", "","bancovigo");//Conexion
    }
    
}
