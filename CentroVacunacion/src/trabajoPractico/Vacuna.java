package trabajoPractico;

public abstract class Vacuna {
	private String nombre;
	private Integer temperatura;
	private Fecha vencimiento;
	private Boolean vencida;
	private Boolean asignadaEnEspera;
	private Boolean utilizada;

	public Vacuna (String nombre, Integer temperatura, Fecha vencimiento) {
		this.nombre = nombre;
		this.temperatura = temperatura;
		this.vencimiento = vencimiento;
		this.vencida= false;
		this.asignadaEnEspera= false;
		this.utilizada = false;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getTemperatura() { 
		return this.temperatura;
	}
	
	public Integer DiasVencimiento() {
		return vencimiento.compareTo(Fecha.hoy());
		}
	
	public void setVencida() {
		vencida= true;
	}
	
	public void setAsignadaEnEspera() { 
		asignadaEnEspera = true;
	}
	
	public void setUtilizada() { 
		if (vencida = true)  
			throw new RuntimeException ("No se puede utilizar una vacuna vencida");
		else 
			utilizada = true; 
	}

	protected abstract boolean getPrioridadMayores();

	public Fecha getFecha() {
		return this.vencimiento;
	}

	public void reasignar() {
		asignadaEnEspera = false;
		
	}
}
