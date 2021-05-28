package trabajoPractico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Administracion {

	private static HashSet<Persona> listaEspera;
	private static HashSet<Persona> colaPrioridad;
	private static HashMap<Integer, String> historialVacunados;
	private static HashMap<Integer, Fecha> turnosGenerados; 
	
	public Administracion() {
		this.listaEspera = new HashSet<Persona>();
		this.colaPrioridad = new HashSet<Persona>();
		this.historialVacunados = new HashMap<Integer, String>();
		this.turnosGenerados = new HashMap <Integer, Fecha>();
	}
	
	//FIXME 
	public static void ingresarPersona(int dni, Fecha nacimiento, boolean tienePadecimientos, boolean esTrabajadorSalud) {
		if (nacimiento.diferenciaAnios(Fecha.hoy(), nacimiento) < 18) {
			throw new RuntimeException ("No se puede ingresar un menor de edad");
		} 
		else if (nacimiento.diferenciaAnios(Fecha.hoy(), nacimiento) > 60) {
			listaEspera.add(new Persona(dni, nacimiento, 1));
		} else if (esTrabajadorSalud) {
			listaEspera.add(new Persona(dni, nacimiento, 2));
		} else if (tienePadecimientos) {
			listaEspera.add(new Persona(dni, nacimiento, 3));
		} else { 
			listaEspera.add(new Persona(dni, nacimiento, 4));
		}
		
		asignarColaPrioridad();
	}
	
	//FIXME
	private void asignarColaPrioridad() {
		/*
		for (Persona p : listaEspera) {
		compareTO
		}
		*/
	}
	
	public void generarTurnos(Fecha fechaInicial) {  
		for (Persona p : listaEspera) {
			if (p.getTurno() < fechaInicial) 
				p = null;
		}

		Almacen.quitarVencidas();
		
		for (int i= 0;  i < CentroVacunacion.getCapacidad(); i++) { //hasta cubrir capacidad
			for (Persona p : colaPriodidad) { //recorro lista de espera ya ordenada 
					turnosGenerados.put(p.getDni(), fechaInicial);
				}
			}
		}
	
	//TODO
	public List<Integer> turnosConFecha(Fecha fecha){
		
	}
		
	public static void vacunarInscripto(int dni, Fecha fecha) {
		//Recorre la lista de turnos y verifica que el dni y la fecha esten. 
		/* for (Persona p : //listaTurnos){
			if (p.getDni() == dni && p.getFecha() == fecha) 
				p.setVacunado();
			}
		*/
	}
	
	public HashMap <Integer, String> reporteVacunacion() {
		return historialVacunados;
	}
	
}
