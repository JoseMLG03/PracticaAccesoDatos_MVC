package controlador;

import modelo.ConexionBD;
import vista.VentanaPrincipal;

public class Controlador {
	private VentanaPrincipal vista;
    private ConexionBD modelo;
    
    public void iniciar() {
        //vista.setVisible(true);
    }
    
    public Controlador() {
        this.modelo = new ConexionBD("localhost", "3306", "root", "");//Conexion
    }
}
