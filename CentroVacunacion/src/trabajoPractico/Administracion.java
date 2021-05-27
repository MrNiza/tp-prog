package trabajoPractico;

import java.util.ArrayList;
import java.util.HashSet;

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
	
	public void anotarPersona() {
		
	}
	
	private void estaDentro() {
		
	}
	
	private void moverPrioridad() {
		
	}
	
	
}
