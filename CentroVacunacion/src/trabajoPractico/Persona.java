package trabajoPractico;

public class Persona implements Comparable<Persona> {

	private static int dni;
	private static int edad;
	private static int prioridad;
	private static Fecha turno;
	private static boolean vacunado;
	
	public Persona(int dni, Fecha nacimiento, int prioridad) {
		this.dni = dni;
		this.edad = nacimiento.diferenciaAnios(Fecha.hoy(), nacimiento);
		this.prioridad = prioridad;
		this.vacunado = false;
	}

	public int getDni () {
		return dni; 
	}

	public void setVacunado() { 
		vacunado = true; 
	}
	
	public Fecha getTurno() {
		return turno; 
	}
	
	public void setTurno(Fecha fecha) {
		turno = fecha; 
	}

	@Override
	public int compareTo(Persona otra) {
		if (this.prioridad == otra.prioridad) {
			return 0;
		} else if (this.prioridad < otra.prioridad) {
			return 1;
		} else {
			return -1; 
		}
		
	}
	
}
