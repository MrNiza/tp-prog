package trabajoPractico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
	
	public static void ingresarPersona(int dni, Fecha nacimiento, boolean tienePadecimientos, boolean esTrabajadorSalud) {
		if (listaEspera.contains(dni))
			throw new RuntimeException ("Esta persona ya est� inscripta");
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
	private static void asignarColaPrioridad() {
		//hay que agregarlas en orden
		for (Persona p : listaEspera) {
			colaPrioridad.add(p); 
		}
	}
	
	//FIXME fecha 
	public void generarTurnos(Fecha fechaInicial) {
		
		for (Persona p : listaEspera) {
			//if (p.getTurno() < fechaInicial) 
				//p = null;
		}

		Almacen.quitarVencidas();
		
		for (int i= 0;  i < CentroVacunacion.getCapacidad(); i++) { //hasta cubrir capacidad
			for (Persona p : colaPrioridad) { //recorro lista de espera ya ordenada 
					turnosGenerados.put(p.getDni(), fechaInicial);
				}
			}
		}
	
	public List<Integer> turnosConFecha(Fecha fecha){
		List <Integer> turnosConFecha = new LinkedList <Integer>();
		for (Integer clave: turnosGenerados.keySet()) { 
			if (turnosGenerados.get(clave) == fecha) {
				turnosConFecha.add(clave);
			}
		}
		return turnosConFecha;
	}
  
	public boolean verificaTurno(Persona persona) {	
		int diff =persona.fechaAcordada().compareTo(Fecha.hoy());
		return diff <= 0 && turnosVigentes.contains(persona);
	}
	
	public void vacunarInscripto(int dni, Fecha fecha) {
    
  }
		
	public static void vacunarInscripto(int dni, Fecha fecha) {
		boolean encontrado = false; 
		 for (Integer clave : turnosGenerados.keySet()){
			 if (clave == dni && turnosGenerados.get(clave) == fecha) {
				encontrado = true; 
				for (Persona p : colaPrioridad) {
					if (p.getDni() == clave)
						p.setVacunado();
						Almacen.asignarVacuna(p.getPrioridad());
				}
		 	}
		 }
		 if (!encontrado) 
			 throw new RuntimeException ("La persona no se encuentra registrada o no tiene fecha para ese d�a");
		}
	
	
	public HashMap <Integer, String> reporteVacunacion() {
		return historialVacunados;
	}

	public List<Integer> listaDeEspera() {
		List <Integer> lista = new LinkedList <Integer>();
		for (Persona p : colaPrioridad) {
			lista.add(p.getDni());
		}
		return lista;
	}
	
	public Map<Integer, String> reporteVacunacion() {
		HashMap<Integer, String> historial = new HashMap<Integer, String>();
		for(Persona p : historialVacunados) {
			historial.put(p.darDni(), p.darNombreVacuna());
		}
		return historial;
	}
	
	/**
	 * aqui se selecciona a las personas que recibiran la vacuna,
	 * devolviendo una lista con los datos de las personas cuya cantidad 
	 * esta determinada por la capacidad de vacunacion
	 */
	public ArrayList<Persona> asignarPersonas(int capacidad){
		ArrayList<Persona> asignados = new ArrayList<Persona>();
		moverPrioridad();
		int cont = capacidad;
		HashSet<Persona> aux;
				
		for(int i = 0; i < 4; i++) {
			aux = colasPrioridad.get(i);
			for(Persona p: aux) {
				if(cont >= 0) {
				asignados.add(p);
				}
				else {
					break;
				}
			}
					
		}
		return asignados;
	}


	public List<Integer> turnosConFecha(Fecha fecha) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
