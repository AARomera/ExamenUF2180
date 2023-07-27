package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Departamento;

public class DepartamentoDAO {
	
	private ConexionBD conexion;
	
	public DepartamentoDAO(){
		this.conexion= new ConexionBD();
	}
	
	
	public ArrayList<Departamento> obtenerDepartamentos(){
		
		Connection con =conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		ArrayList<Departamento> listaDepartamento = new ArrayList<Departamento>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("SELECT * FROM departamentos");
			
			while (resultado.next()) {
				int codDepartamento=resultado.getInt("cod_departamento");
				int codCentro=resultado.getInt("cod_centro");
				String tipoDir=resultado.getString("tipo_dir");
				int presupuesto=resultado.getInt("presupuesto");
				String nombre = resultado.getString("nombre");
				
				Departamento departamento = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);
				listaDepartamento.add(departamento);
			}
		} catch(SQLException e) {
			System.out.println("Error al realizar la consulta sobre departamentos: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return listaDepartamento;
	}
	
	public Departamento obtenerDepartamento(int cod_dep) {
		
		Connection con=conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Departamento d=null;
		
		try {
			consulta = con.prepareStatement("SELECT * FROM departamentos "
					+ "WHERE cod_departamento=?");
			
			consulta.setInt(1, cod_dep);
			resultado=consulta.executeQuery();
			
			if (resultado.next()) {
				int codCentro=resultado.getInt("cod_centro");
				String tipoDir=resultado.getString("tipo_dir");
				int presupuesto=resultado.getInt("presupuesto");
				String nombre=resultado.getString("nombre");
				
				d= new Departamento(cod_dep, codCentro, tipoDir, presupuesto, nombre);
			}
			
		} catch(SQLException e) {
			System.out.println("Error al realizar la consulta sobre centros: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return d;
	}
	
	public int insertarDepartamento(Departamento departamento) throws SQLException {
		
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta=con.prepareStatement("INSERT INTO departamentos "
					+ "VALUES(?,?,?,?,?)");
			
			consulta.setInt(1, departamento.getCodDepartamento());
			consulta.setInt(2, departamento.getCodCentro());
			consulta.setString(3, departamento.getTipoDir());
			consulta.setInt(4, departamento.getPresupuesto());
			consulta.setString(5, departamento.getNombre());
			
			resultado=consulta.executeUpdate();
		}catch (SQLException e) {
			throw new SQLException();
			} finally {
				try {
					consulta.close();
					conexion.desconectar();
				} catch (SQLException e) {
					System.out.println("Error al liberar recursos: "+e.getMessage());
				} catch (Exception e) {
					
				}
			}
			return resultado;
		 
	}
}
