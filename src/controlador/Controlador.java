/**
 * 
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.CentroDAO;
import dao.DepartamentoDAO;
import modelo.Centro;
import modelo.Departamento;
import vista.DialogoAnadirCentro;
import vista.DialogoAnadirDepartamento;
import vista.VentanaMostrarCentros;
import vista.VentanaMostrarDepartamentos;
import vista.VentanaPpal;

/**
 * @author David
 *
 */
public class Controlador {

	// Ventanas del sistema
	private VentanaPpal ventanaPpal;
	private VentanaMostrarCentros ventanaMostrarCentros;
	private DialogoAnadirCentro dialogoAnadirCentro;
	private VentanaMostrarDepartamentos ventanaMostrarDepartamentos;
	private DialogoAnadirDepartamento dialogoAnadirDepartamento;
	
	// Objetos DAO o CRUD de la base de datos
	private CentroDAO centroDAO;
	private DepartamentoDAO departamentoDAO;

	
	
	public Controlador() {
		// Creamos las ventanas de la aplicacinn
		ventanaPpal = new VentanaPpal();
		ventanaMostrarCentros = new VentanaMostrarCentros();
		dialogoAnadirCentro = new DialogoAnadirCentro();
		ventanaMostrarDepartamentos=new VentanaMostrarDepartamentos();
		dialogoAnadirDepartamento= new DialogoAnadirDepartamento();
		
		// Dando acceso al controlador desde las vistas
		ventanaPpal.setControlador(this);
		ventanaMostrarCentros.setControlador(this);
		dialogoAnadirCentro.setControlador(this);
		ventanaMostrarDepartamentos.setControlador(this);
		dialogoAnadirDepartamento.setControlador(this);

		
		// Creamos los objetos DAO
		centroDAO = new CentroDAO();
		departamentoDAO= new DepartamentoDAO();
	}
	
	
	/**
	 * Mntodo que arranca el programa principal
	 */
	public void inciarPrograma() {
		ventanaPpal.setVisible(true);
	}
	
	public void mostrarListarCentros() {
		ArrayList<Centro> lista = centroDAO.obtenerCentros();
		ventanaMostrarCentros.setListaCentros(lista);
		ventanaMostrarCentros.setVisible(true);
	}
	
	public void mostrarListaDepartamentos() {
		ArrayList<Departamento>listaDepartamentos=departamentoDAO.obtenerDepartamentos();
		ventanaMostrarDepartamentos.setListaDepartamentos(listaDepartamentos);
		ventanaMostrarDepartamentos.setVisible(true);
	}
	
	public void mostrarInsertarCentros() {
		dialogoAnadirCentro.setVisible(true);
	}
	
	public void mostrarInsertarDepartamentos() {
		dialogoAnadirDepartamento.setVisible(true);
	}
	


	/** 
	 * Mntodo del controlador que anade un nuevo centro a la tabla de centros
	 * @param centro
	 */
	public void insertaCentro(Centro centro) {
		// Invocando a centroDAO
		int resultado = centroDAO.insertarCentro(centro);
		if (resultado ==0) {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Error. no se ha podido insertar.");
		} else {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Insercion del centro correcta");
			dialogoAnadirCentro.setVisible(false);
		}
	}


	public void insertaDepartamento(Departamento departamento) {
		int resultado=0;
		try {
			resultado = departamentoDAO.insertarDepartamento(departamento);
			
			if (resultado==0) {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Error. no se ha podido insertar.");
		} else {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Insercion del departamento correcta");
			dialogoAnadirCentro.setVisible(false);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
