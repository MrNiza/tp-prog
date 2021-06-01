package trabajoPractico;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Almacen {
	private static HashMap<String, Integer> stock;
	private static HashMap<String, Integer> vencidas;
	private static HashSet<Vacuna> listaVacunas;
	
	public Almacen () { 
		this.stock = new HashMap <String,Integer>();
		this.vencidas = new HashMap <String,Integer>();
		this.listaVacunas = new HashSet <Vacuna>();
	}
	
	
	public static HashMap<String,Integer> reporteVacunasVencidas() {
		return vencidas;
	}
	
	public static boolean esValida(String nombreVacuna) {
		if (nombreVacuna.equals("Sputnik") || nombreVacuna.equals("Sinopharm") || nombreVacuna.equals("Pfizer") || nombreVacuna.equals("Moderna") || nombreVacuna.equals("Astrazeneca"))   
			return true;
		return false; 
	}
	
	public static void ingresarVacuna(String nombre, int Cantidad, Fecha fechaIngreso) {
		if (nombre.equals("Sputnik"))
				listaVacunas.add(new Vacuna3Grados(nombre,true,fechaIngreso));
		if (nombre.equals("Pfizer")) 
				listaVacunas.add(new VacunaMenos18(nombre,true,fechaIngreso));
		if (nombre.equals("Sinopharm"))
				listaVacunas.add(new Vacuna3Grados(nombre,false,fechaIngreso));
		if (nombre.equals("Moderna")) 
				listaVacunas.add(new VacunaMenos18(nombre,false,fechaIngreso));	
		if (nombre.equals("Astrazeneca"))
				listaVacunas.add(new Vacuna3Grados(nombre,false,fechaIngreso));
		}
	
	//FIXME
	public static void quitarVencidas() { 
		//error de nullpointer, recorrer de otra forma?
		for (Vacuna v : listaVacunas) { 
			if (v != null && v.DiasVencimiento() <= 0) {
				vencidas.replace(v.getNombre(), vencidas.get(v.getNombre()) + 1 );
				v= null;
			}
		}
	}
	
	public HashSet<Vacuna> asignarVacunas(int prioridad, int cantidad) {
		HashSet <Vacuna> vacunasListas = new HashSet <Vacuna>();
		Integer contador = 0;
		for (Vacuna v : listaVacunas) {
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

