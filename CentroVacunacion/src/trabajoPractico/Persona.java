package trabajoPractico;

public class Persona {

	private int dni;
	private int edad;
	private int prioridad;
	private Turno turno;
	private boolean vacunado;

	public Persona(int dni, int edad) {
		this.dni = dni;
		this.edad = edad;
		this.prioridad = 0;
		this.vacunado = false;
	}
	
}
