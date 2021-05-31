package trabajoPractico;

import java.util.HashMap;
import java.util.HashSet;

public class Almacen {
	private HashMap<String, Integer> stock;
	private HashMap<String, Integer> vencidas;
	private HashSet<Vacuna> vacunas;
	
	public Almacen () { 
		HashMap<String, Integer> stock = new HashMap <String,Integer>();
		HashMap<String, Integer> vencidas = new HashMap <String,Integer>();
		HashSet<Vacuna> vacunas = new HashSet <Vacuna>();
	}
		
	public HashMap<String,Integer> reporteVacunasVencidas() {
		return vencidas;
	}
	
	public void quitarVencidas() { 
		for (Vacuna v : vacunas) { 
			if (v.DiasVencimiento() <= 0) {
				vencidas.replace(v.getNombre(), vencidas.get(v.getNombre()) + 1 );
				vacunas.remove(v);
			}
		}
	}
	
	public HashSet<Vacuna> asignarVacunas(int prioridad, int cantidad) {
		HashSet <Vacuna> vacunasListas = new HashSet <Vacuna>();
		Integer contador = 0;
		for (Vacuna v : vacunas) {
			if(v.getPrioridadMayores() == true && prioridad == 1) {
				contador++;
				v.setAsignadaEnEspera();
				vacunasListas.add(v);
			}
			if (contador == cantidad) { 
			return vacunasListas;
			}
		}
		return vacunasListas;
	}

	public boolean verificarVacuna (String nombre) { 
		if (nombre == "Sputnik" || nombre == "Pfizer" || nombre == "Sinopharm" || nombre == "Moderna" || nombre == "Astrazeneca") {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void ingresarVacuna(String nombre, Fecha fecha) {
		if (verificarVacuna(nombre)) {
			if (nombre == "Sputnik")
				vacunas.add(new Sputnik(fecha));
			if (nombre == "Pfizer") 
				vacunas.add(new Pfizer(fecha));
			if (nombre == "Sinopharm")
				vacunas.add(new Sinopharm(fecha));
			if (nombre == "Moderna")
				vacunas.add(new Moderna(fecha));	
			if (nombre == "Astrazeneca")
				vacunas.add(new Astrazeneca(fecha));
		}
	}
		
	public int vacunasDisponibles() { 
		int contador = 0;
		for (int i=0; i< stock.size(); i++) { 
			contador += stock.get(i); 
		}
		return contador;
	}

	public int vacunasDisponibles(String nombreVacuna) {
		return stock.get(nombreVacuna);
	}
}
