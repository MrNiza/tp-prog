package trabajoPractico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Administracion {

	private HashSet<Persona> listaEspera;
	private ArrayList<HashSet<Persona>> colasPrioridad; //no creo que este bn pero me parecio lo mejor de momento
	private HashSet<Turno> turnosVigentes;
	private HashSet<Persona> historialVacunados;
	
	public Administracion() {
		this.listaEspera = new HashSet<Persona>();
		this.colasPrioridad = new ArrayList<HashSet<Persona>>();
		
		for(int i = 0;i<4 ;i++) {
			this.colasPrioridad.add(new HashSet<Persona>());
		}
		
		this.turnosVigentes = new HashSet<Turno>();
		this.historialVacunados = new HashSet<Persona>();
		
	}
	
	public void ingresarPersona(int dni, Fecha nacimiento, boolean padecimientos, boolean empleadoSalud) {
		int prioridad = 0;
		
		if (nacimiento.diferenciaAnios(Fecha.hoy(), nacimiento) < 18) {
			throw new RuntimeException ("No se puede anotar un menor de edad");
		}
		if(!estaDentro(dni)) {
			throw new RuntimeException ("Esta persona ya se anoto o ya fue vacunada");
		}
		else {
			if (nacimiento.diferenciaAnios(Fecha.hoy(), nacimiento) >= 60) {
				prioridad = 1;
			}
			if (empleadoSalud && prioridad == 0) {
				prioridad = 2;
			}
			if (padecimientos && prioridad == 0) {
				prioridad = 3;
			}
			if (prioridad == 0) {
				prioridad = 4;
			}
			listaEspera.add(new Persona(dni,nacimiento,prioridad));
		}
			
		
	}
	
	private boolean estaDentro(int dni) {
		boolean ret = false;
		for(Persona p: listaEspera) {
			if(p.darDni() == dni) {
				ret = true;
			}
		}
		for(Persona p: historialVacunados) {
			if(p.darDni() == dni) {
				ret = true;
			}
		}
		return ret;
	}
	
	/**
	 * Se ordena la lista de espera en colas de prioridades.
	 * No modifica la informacion en lista de espera sino que copia dicha informacion
	 * para la seleccion de vacunas.
	 */
	private void moverPrioridad() {
		HashSet<Persona> aux;
		for(Persona p: listaEspera) {
			if(p.getPrioridad() == 1) {
				aux = colasPrioridad.get(0);
				aux.add(p);
			}
			if(p.getPrioridad() == 2) {
				aux = colasPrioridad.get(1);
				aux.add(p);
			}
			if(p.getPrioridad() == 3) {
				aux = colasPrioridad.get(2);
				aux.add(p);
			}
			if(p.getPrioridad() == 4) {
				aux = colasPrioridad.get(3);
				aux.add(p);
			}
		}
	}
	
	public void generarTurnos(Fecha fecha, int capacidad) {
		moverPrioridad();
	}
	
	public boolean verificaTurno(Persona persona) {	
		int diff =persona.fechaAcordada().compareTo(Fecha.hoy());
		return diff <= 0 && turnosVigentes.contains(persona);
	}
	
	public void vacunado() {
		
	}
	
	private void dejarEnHistorial(Persona persona) {
		listaEspera.remove(persona);
		historialVacunados.add(persona);
	}
	
	public List<Integer> enEspera() {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(Persona p: listaEspera) {
			if(!p.estaVacunado() || !p.tieneTurno()) {
				Integer dato = new Integer(p.darDni());
				ret.add(dato);
			}
		}
		return ret;
	}

	/**
	 * revisa las personas anotadas, si alguna recibio un turno entonces verifica
	 * que dicho turno este dentro de la fecha prevista. 
	 * De estar vencido y no haberse vacunado dicho turno se le borrara
	 * y la vacuna se reasigna para su posterior uso.
	 * @return 
	 */
	public void quitarTurnosVencidos(){
		for(Persona p: listaEspera) {
			if(p.tieneTurno() && p.estaVacunado()) {
				dejarEnHistorial(p);
			}
			else if(p.tieneTurno() && !verificaTurno(p)) {
				p.darVacuna().reasignar();
				p.quitarTurno();
			}
		}
	}

	
	public Map<Integer, String> reporteVacunacion() {
		HashMap<Integer, String> historial = new HashMap<Integer, String>();
		for(Persona p : historialVacunados) {
			historial.put(p.darDni(), p.darNombreVacuna());
		}
		return historial;
	}
	
}
