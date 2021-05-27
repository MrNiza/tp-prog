package trabajoPractico;

import java.util.HashMap;
import java.util.HashSet;

public class Almacen {
	private static HashMap<String, Integer> stock;
	private static HashMap<String, Integer> vencidas;
	private static HashSet<Vacuna> vacunas;
	
	private Almacen () { 
		HashMap<String, Integer> stock = new HashMap <String,Integer>();
		HashMap<String, Integer> vencidas = new HashMap <String,Integer>();
		HashSet<Vacuna> vacunas = new HashSet <Vacuna>();
	}
	
	
	public static HashMap<String,Integer> reporteVacunasVencidas() {
		return vencidas;
	}
	
	public static void quitarVencidas() { 
		for (Vacuna v : vacunas) { 
			if (v.DiasVencimiento() <= 0) {
				vencidas.replace(v.getNombre(), vencidas.get(v.getNombre()) + 1 );
				v= null;
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

	public static boolean verificarVacuna (String nombre) { 
		if (nombre == "Sputnik" || nombre == "Pfizer" || nombre == "Sinopharm" || nombre == "Moderna" || nombre == "Astrazeneca") 
			return true;
		return false;
	}
	
	public void ingresarVacuna(String nombre) {
		if (verificarVacuna(nombre) == true) {
			if (nombre == "Sputnik")
				vacunas.add(new Sputnik(Fecha.hoy()));
			if (nombre == "Pfizer") 
				vacunas.add(new Pfizer(Fecha.hoy()));
			if (nombre == "Sinopharm")
				vacunas.add(new Sinopharm(Fecha.hoy()));
			if (nombre == "Moderna")
				vacunas.add(new Moderna(Fecha.hoy()));	
			if (nombre == "Astrazeneca")
				vacunas.add(new Astrazeneca(Fecha.hoy()));
		}
	}
		
	public static int vacunasDisponibles() { 
		Integer contador = 0;
		for (int i=0; i< stock.size(); i++) { 
			contador += stock.get(i); 
		}
		return contador;
	}


	public static int vacunasDisponibles(String nombreVacuna) {
		return stock.get(nombreVacuna);
	}
}
