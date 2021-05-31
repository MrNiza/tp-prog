package trabajoPractico;

import java.util.HashMap;
import java.util.HashSet;

public class Almacen {
	private static HashMap<String, Integer> stock;
	private static HashMap<String, Integer> vencidas;
	private static HashSet<Vacuna> vacunas;
	
	public Almacen () { 
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
			if(v.getPrioridadMayores() && prioridad == 1) {
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
	
	//TODO
	public void ingresarVacuna(String nombre) {
		if (!verificarVacuna(nombre)) 
			throw new RuntimeException ("La vacuna ingresada no existe");
		else {
			if (nombre == "Sputnik")
				vacunas.add(new Vacuna3Grados(nombre,true,Fecha.hoy()));
			if (nombre == "Pfizer") 
				vacunas.add(new VacunaMenos18(nombre,true,Fecha.hoy()));
			if (nombre == "Sinopharm")
				vacunas.add(new Vacuna3Grados(nombre,false,Fecha.hoy()));
			if (nombre == "Moderna")
				vacunas.add(new VacunaMenos18(nombre,false,Fecha.hoy()));	
			if (nombre == "Astrazeneca")
				vacunas.add(new Vacuna3Grados(nombre,false,Fecha.hoy()));
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


	public static void asignarVacuna(int prioridad) {
		if (prioridad == 1) {
			for (String v : stock.keySet()) {
				if (v == "Pfizer" && stock.get(v) > 0) {
					stock.replace("Pfizer", stock.get(v)-1);
				} else if (v == "Sputnik" && stock.get(v) > 0) {
					stock.replace("Sputnik", stock.get(v)-1);
				}
			}
		} else {
			for (String v : stock.keySet()) {
				if (v == "Moderna" && stock.get(v) > 0) {
					stock.replace("Moderna", stock.get(v)-1);
				} else if (v == "Sinopharm" && stock.get(v) > 0) {
					stock.replace("Sinopharm", stock.get(v)-1);
				} else if (v == "Astrazeneca" && stock.get(v) > 0) {
					stock.replace("Astrazeneca", stock.get(v)-1);
				} else {
					throw new RuntimeException ("No hay vacunas disponibles");
				}
			}
		
		}
	}
}

