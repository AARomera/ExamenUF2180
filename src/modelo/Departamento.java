package modelo;

public class Departamento {
	
	private int codDepartamento;
	private int codCentro;
	private String tipoDir;
	private int presupuesto;
	private String nombre;
	
	
	public Departamento(int codDepartamento, int codCentro, String tipoDir, int presupuesto, String nombre) {
		super();
		this.codDepartamento = codDepartamento;
		this.codCentro = codCentro;
		this.tipoDir = tipoDir;
		this.presupuesto = presupuesto;
		this.nombre = nombre;
	}
	
	public Departamento() {
		super();
		this.codDepartamento = 0;
		this.codCentro = 0;
		this.tipoDir = "";
		this.presupuesto = 0;
		this.nombre = "";
	}

	public int getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public int getCodCentro() {
		return codCentro;
	}

	public void setCodCentro(int codCentro) {
		this.codCentro = codCentro;
	}

	public String getTipoDir() {
		return tipoDir;
	}

	public void setTipoDir(String tipoDir) {
		this.tipoDir = tipoDir;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codDepartamento;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (codDepartamento != other.codDepartamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "codDepartamento: " + codDepartamento + "\n codCentro: " + codCentro + "\n tipoDir: " + tipoDir
				+ "\n presupuesto: " + presupuesto + "\n nombre: " + nombre;
	}
	

}
