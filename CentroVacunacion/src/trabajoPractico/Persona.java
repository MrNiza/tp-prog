package trabajoPractico;

public class Persona {

	private int dni;
	private int edad;
	private int prioridad;
	private Turno turno;
	private boolean vacunado;

	public Persona(int dni,Fecha nacimiento, int prioridad) {
		this.dni = dni;
		this.edad = nacimiento.diferenciaAnios(Fecha.hoy(),nacimiento);
		this.prioridad = prioridad;
		this.vacunado = false;
	}
	
	public int darDni() {
		return this.dni;
	}
	
	public boolean estaVacunado() {
		return this.vacunado;
	}
	
	public boolean tieneTurno() {
		return turno == null;
	}

	public String darNombreVacuna() {
		String vacuna = turno.getNombreVacuna();
		return vacuna;
	}
	
	public void asignarTurno(Turno turno) {
		this.turno = turno;
	}
	
	public void quitarTurno() {
		this.turno = null;
	}
	
	public Fecha fechaAcordada() {
		return this.turno.darFecha();
	}
	
	public Vacuna darVacuna(){
		return this.turno.getVacuna();
	}

	
	public int getPrioridad() {
		return this.prioridad;
	}
	
}
