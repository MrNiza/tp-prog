package trabajoPractico;

public abstract class Vacuna {
	private String nombre;
	private int temperatura;
	private Fecha fechaIngreso;
	private boolean prioridadMayores;
	private boolean asignadaEnEspera;
	private boolean utilizada;

	public Vacuna (String nombre, int temperatura, Fecha fechaIngreso, boolean prioridadMayores) {
		this.nombre = nombre;
		this.temperatura = temperatura;
		this.fechaIngreso = fechaIngreso;
		this.prioridadMayores = prioridadMayores;
		this.asignadaEnEspera= false;
		this.utilizada = false;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getTemperatura() { 
		return this.temperatura;
	}
	
	//FIXME
	//Esto no va a lanzar los dias de vencimiento, solo retorna, 1, 0 o -1
	public Integer DiasVencimiento() {
		return fechaIngreso.compareTo(Fecha.hoy());
		}
	
	
	public void setAsignadaEnEspera() { 
		asignadaEnEspera = true;
	}
	
	public void setUtilizada() { 
			utilizada = true; 
	}

	protected boolean getPrioridadMayores() {
		return prioridadMayores; 
	}
	
	public String toString() { 
		return "Nombre: " + this.nombre; 

	public void reasignar() {
		asignadaEnEspera = false;
  
	}
}
