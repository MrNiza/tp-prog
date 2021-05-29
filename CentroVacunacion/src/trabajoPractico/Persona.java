package trabajoPractico;

public class Persona {

	private int dni;
	private int edad;
	private int prioridad;
	private Turno turno;
	private boolean vacunado;

	public Persona(int dni, int edad, int prioridad) {
		this.dni = dni;
		this.edad = edad;
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
}
