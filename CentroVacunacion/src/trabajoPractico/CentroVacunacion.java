<<<<<<< HEAD
package trabajoPractico;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CentroVacunacion {
	private static String nombre;
	private static int capacidad;
	private Fecha fecha;
	private Administracion administracion;
	private Almacen almacen;
	
	public CentroVacunacion (String nombreCentro, int capacidadVacunacionDiaria) {
		
		if (capacidadVacunacionDiaria <= 0) {
			throw new RuntimeException("La capacidad no puede ser negativa");
		}
		
		this.nombre = nombreCentro;
		this.capacidad = capacidadVacunacionDiaria;
		this.fecha = Fecha.hoy();
		this.administracion = new Administracion();
		this.almacen = new Almacen();
	}	
	
	public  String toString() {
		return "Nombre: " + nombre + " Capacidad:" + capacidad;  
	}
	
	public static int getCapacidad() { 
		return capacidad; 
	}
	
	//FIXME
	public void ingresarVacunas(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		if (cantidad < 0) 
			throw new RuntimeException ("La cantidad no puede ser negativa");
		//por alguna razÛn siempre entra ac·
		if (Almacen.esValida(nombreVacuna)) {
			Almacen.ingresarVacuna(nombreVacuna, cantidad,  fechaIngreso); 
		} else { 
			throw new RuntimeException ("El nombre ingresado no es v·lido"); 
		}
	}
	
	public int vacunasDisponibles() {
		Almacen.quitarVencidas();
		return Almacen.vacunasDisponibles();
	}
	
	public int vacunasDisponibles(String nombreVacuna) {
		if (!Almacen.esValida(nombreVacuna)) 
			throw new RuntimeException ("La vacuna ingresada no es v·lida");
		return Almacen.vacunasDisponibles(nombreVacuna);
	}
	
	
	/**
	* Se inscribe una persona en lista de espera.
	* Si la persona ya se encuentra inscripta o es menor de 18 aÒos, se debe
	* generar una excepciÛn.
	* Si la persona ya fue vacunada, tambiÈn debe generar una excepciÛn.
	*/
	public void inscribirPersona(int dni, Fecha nacimiento, boolean tienePadecimientos, boolean esEmpleadoSalud) {
		Administracion.ingresarPersona(dni, nacimiento, tienePadecimientos, esEmpleadoSalud);
	}
	
	/**
	* Devuelve una lista con los DNI de todos los inscriptos que no se vacunaron
	* y que no tienen turno asignado.
	* Si no quedan inscriptos sin vacunas debe devolver una lista vacÌa.
	*/
	public List<Integer> listaDeEspera() {
		return administracion.listaDeEspera();
		}
	
	/**
	* Primero se verifica si hay turnos vencidos. En caso de haber turnos
	* vencidos, la persona que no asistiÛ al turno debe ser borrada del sistema
	* y la vacuna reservada debe volver a estar disponible.
	*
	* Segundo, se deben verificar si hay vacunas vencidas y quitarlas del sistema.
	*
	* Por ˙ltimo, se procede a asignar los turnos a partir de la fecha inicial
	* recibida seg˙n lo especificado en la 1ra parte.
	* Cada vez que se registra un nuevo turno, la vacuna destinada a esa persona
	* dejar· de estar disponible. Dado que estar· reservada para ser aplicada
	* el dÌa del turno.
	*
	*
	*/
	public void generarTurnos(Fecha fechaInicial) { 
		administracion.generarTurnos(fechaInicial);
	}
	
	/**
	* Devuelve una lista con los dni de las personas que tienen turno asignado
	* para la fecha pasada por par·metro.
	* Si no hay turnos asignados para ese dÌa, se debe devolver una lista vacÌa.
	* La cantidad de turnos no puede exceder la capacidad por dÌa de la ungs.
	*/
	public List<Integer> turnosConFecha(Fecha fecha){
		return administracion.turnosConFecha(fecha);
	}
	
	/**
	* Dado el DNI de la persona y la fecha de vacunaciÛn
	* se valida que estÈ inscripto y que tenga turno para ese dia.
	* - Si tiene turno y est· inscripto se debe registrar la persona como
	* vacunada y la vacuna se quita del depÛsito.
	* - Si no est· inscripto o no tiene turno ese dÌa, se genera una Excepcion.
	*/
	public void vacunarInscripto(int dni, Fecha fechaVacunacion) { 
		Administracion.vacunarInscripto(dni, fechaVacunacion);
	}
	
	/**
	* Devuelve un Diccionario donde
	* - la clave es el dni de las personas vacunadas
	* - Y, el valor es el nombre de la vacuna aplicada.
	*/
	public Map<Integer, String> reporteVacunacion(){
		return administracion.reporteVacunacion();
	}
	
	/**
	* Devuelve en O(1) un Diccionario:
	* - clave: nombre de la vacuna
	* - valor: cantidad de vacunas vencidas conocidas hasta el momento.
	*/
	public Map<String, Integer> reporteVacunasVencidas(){
		return Almacen.reporteVacunasVencidas();
	}

}
=======
package trabajoPractico;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CentroVacunacion {
	private static String nombre;
	private static int capacidad;
	private Fecha fecha;
	private Administracion administracion;
	private Almacen almacen;
	
	public CentroVacunacion (String nombreCentro, int capacidadVacunacionDiaria) {
		
		if (capacidadVacunacionDiaria <= 0) {
			throw new RuntimeException("La capacidad no puede ser negativa");
		}
		
		this.nombre = nombreCentro;
		this.capacidad = capacidadVacunacionDiaria;
		this.fecha = Fecha.hoy();
		this.administracion = new Administracion();
		this.almacen = new Almacen();
	}	
	
	public  String toString() {
		return "Nombre: " + nombre + " Capacidad:" + capacidad;  
	}
	
	public static int getCapacidad() { 
		return capacidad; 
	}
	
	public void ingresarVacunas(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		if (cantidad <= 0) 
			throw new RuntimeException ("La cantidad no puede ser negativa");
		//por alguna raz√≥n siempre entra ac√°
		if (Almacen.esValida(nombreVacuna)) {
			Almacen.ingresarVacuna(nombreVacuna, cantidad,  fechaIngreso); 
		} else { 
			throw new RuntimeException ("El nombre ingresado no es v√°lido"); 
		}
	}
	
	public int vacunasDisponibles() {
		Almacen.quitarVencidas();
		return Almacen.vacunasDisponibles();
	}
	
	public int vacunasDisponibles(String nombreVacuna) {

		if(!Almacen.esValida(nombreVacuna)) {
			throw new RuntimeException("La vacuna ingresada no existe");
		}
		Almacen.quitarVencidas();
		return Almacen.vacunasDisponibles(nombreVacuna);
	}
	
	
	/**
	* Se inscribe una persona en lista de espera.
	* Si la persona ya se encuentra inscripta o es menor de 18 a√±os, se debe
	* generar una excepci√≥n.
	* Si la persona ya fue vacunada, tambi√©n debe generar una excepci√≥n.
	*/
	public void inscribirPersona(int dni, Fecha nacimiento, boolean tienePadecimientos, boolean esEmpleadoSalud) {
		Administracion.ingresarPersona(dni, nacimiento, tienePadecimientos, esEmpleadoSalud);
	}
	
	/**
	* Devuelve una lista con los DNI de todos los inscriptos que no se vacunaron
	* y que no tienen turno asignado.
	* Si no quedan inscriptos sin vacunas debe devolver una lista vac√≠a.
	*/
	public List<Integer> listaDeEspera() {
		return administracion.listaDeEspera();
		}
	
	/**
	* Primero se verifica si hay turnos vencidos. En caso de haber turnos
	* vencidos, la persona que no asisti√≥ al turno debe ser borrada del sistema
	* y la vacuna reservada debe volver a estar disponible.
	*
	* Segundo, se deben verificar si hay vacunas vencidas y quitarlas del sistema.
	*
	* Por √∫ltimo, se procede a asignar los turnos a partir de la fecha inicial
	* recibida seg√∫n lo especificado en la 1ra parte.
	* Cada vez que se registra un nuevo turno, la vacuna destinada a esa persona
	* dejar√° de estar disponible. Dado que estar√° reservada para ser aplicada
	* el d√≠a del turno.
	*
	*
	*/
	public void generarTurnos(Fecha fechaInicial) { 
		if (fecha.hoy().posterior(fechaInicial))
			throw new RuntimeException ("No se pueden generar turnos para una fecha pasada");
		administracion.asignarPersonas(this.capacidad);
		
		HashSet<Vacuna> vacunasListas = almacen.asignarVacunasEspeciales(this.capacidad);
	}
	
	/**
	* Devuelve una lista con los dni de las personas que tienen turno asignado
	* para la fecha pasada por par√°metro.
	* Si no hay turnos asignados para ese d√≠a, se debe devolver una lista vac√≠a.
	* La cantidad de turnos no puede exceder la capacidad por d√≠a de la ungs.
	*/
	public List<Integer> turnosConFecha(Fecha fecha){
		return administracion.turnosConFecha(fecha);
	}
	
	/**
	* Dado el DNI de la persona y la fecha de vacunaci√≥n
	* se valida que est√© inscripto y que tenga turno para ese dia.
	* - Si tiene turno y est√° inscripto se debe registrar la persona como
	* vacunada y la vacuna se quita del dep√≥sito.
	* - Si no est√° inscripto o no tiene turno ese d√≠a, se genera una Excepcion.
	*/
	public void vacunarInscripto(int dni, Fecha fechaVacunacion) { 
		Administracion.vacunarInscripto(dni, fechaVacunacion);
	}
	
	/**
	* Devuelve un Diccionario donde
	* - la clave es el dni de las personas vacunadas
	* - Y, el valor es el nombre de la vacuna aplicada.
	*/
	public Map<Integer, String> reporteVacunacion(){
		return administracion.reporteVacunacion();
	}
	
	/**
	* Devuelve en O(1) un Diccionario:
	* - clave: nombre de la vacuna
	* - valor: cantidad de vacunas vencidas conocidas hasta el momento.
	*/
	public Map<String, Integer> reporteVacunasVencidas(){
		return Almacen.reporteVacunasVencidas();
	}

}
>>>>>>> Mica
