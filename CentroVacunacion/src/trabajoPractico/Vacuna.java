package trabajoPractico;

public abstract class Vacuna {
	private String nombre;
	private Integer temperatura;
	private Fecha fechaIngreso;
	private Boolean asignadaEnEspera;
	private Boolean utilizada;

	public Vacuna (String nombre, Integer temperatura, Fecha fechaIngreso) {
		this.nombre = nombre;
		this.temperatura = temperatura;
		this.fechaIngreso = fechaIngreso;
		this.asignadaEnEspera= false;
		this.utilizada = false;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getTemperatura() { 
		return this.temperatura;
	}
	
	
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

	protected abstract boolean getPrioridadMayores();
}
