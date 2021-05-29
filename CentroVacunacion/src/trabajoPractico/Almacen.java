package trabajoPractico;

import java.util.Map;
import java.util.Set;

public class Almacen {
	private Map<String, Integer> stock;
	private Map<String, Integer> vencidas;
	private Set<Vacuna> vacunas;
	
	public Almacen () { 
		Map<String, Integer> stock = new Map <String,Integer>();
		Map<String, Integer> vencidas = new Map <String,Integer>();
		Set<Vacuna> vacunas = new Set <Vacuna>();
	}
		
	public Map<String,Integer> reporteVacunasVencidas() {
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
		
	public static int vacunasDisponibles() { 
		int contador = 0;
		for (int i=0; i< stock.size(); i++) { 
			contador += stock.get(i); 
		}
		return contador;
	}

	public static int vacunasDisponibles(String nombreVacuna) {
		return stock.get(nombreVacuna);
	}
}
