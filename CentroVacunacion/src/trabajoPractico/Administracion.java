package trabajoPractico;

import java.util.List;
import java.util.Set;

public class Administracion {

	private Set<Persona> listaEspera;
	private List<Set<Persona>> colasPrioridad; //no creo que este bn pero me parecio lo mejor de momento
	private Set<Turno> turnosVigentes;
	private Set<Persona> historialVacunados;
	
	public Administracion() {
		this.listaEspera = new Set<Persona>();
		this.colasPrioridad = new List<Set<Persona>>();
		
		for(int i = 0;i<4 ;i++) {
			this.colasPrioridad.add(new Set<Persona>());
		}
		
		this.turnosVigentes = new Set<Turno>();
		this.historialVacunados = new Set<Persona>();
		
	}
	
	public void anotarPersona(int dni, Fecha nacimiento, boolean padecimientos, boolean empleadoSalud) {
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
		ret = false;
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
	
	private void moverPrioridad() {
		
	}
	
	public void generarTurnos() {
		
	}
	
	public boolean verificaTurno() {
		return false;
	}
	
	public void vacunado() {
		
	}
	
	private void dejarEnHistorial() {
		
	}
	
	public List<Integer> enEspera() {
		List<Integer> ret = new List<Integer>();
		for(Persona p: listaEspera) {
			if(!p.estaVacunado() || !p.tieneTurno()) {
				ret.add(New Integer(p.darDni()));
			}
		}
		return ret;
	}

	public void quitarTurnos
}
