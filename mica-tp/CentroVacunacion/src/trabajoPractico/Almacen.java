package trabajoPractico;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
		if (nombreVacuna.equals("Sputnik") || nombreVacuna.equals("Sinopharm") || nombreVacuna.equals("Pfizer") || nombreVacuna.equals("Moderna") || nombreVacuna.equals("AstraZeneca"))   {
			return true;
		}
		return false; 
	}
	
	public static void ingresarVacuna(String nombre, int Cantidad, Fecha fechaIngreso) {
		if (nombre.equals("Sputnik"))
			for(int i = 0; i<Cantidad; i++)
				listaVacunas.add(new Vacuna3Grados(nombre,true,fechaIngreso));
		if (nombre.equals("Pfizer"))
			for(int i = 0; i<Cantidad; i++)
				listaVacunas.add(new VacunaMenos18(nombre,true,fechaIngreso));
		if (nombre.equals("Sinopharm"))
			for(int i = 0; i<Cantidad; i++)
				listaVacunas.add(new Vacuna3Grados(nombre,false,fechaIngreso));
		if (nombre.equals("Moderna")) 
			for(int i = 0; i<Cantidad; i++)
				listaVacunas.add(new VacunaMenos18(nombre,false,fechaIngreso));	
		if (nombre.equals("AstraZeneca"))
			for(int i = 0; i<Cantidad; i++)
				listaVacunas.add(new Vacuna3Grados(nombre,false,fechaIngreso));
		}
	
	public static void quitarVencidas() { 
		Iterator it = listaVacunas.iterator();
		
		while(it.hasNext()){
			Vacuna v = (Vacuna) it.next();
			if(v.DiasVencimiento() <= 0) {
				it.remove();
			}
			it.next();
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
		int contador = 0;
		for (int i=0; i< listaVacunas.size(); i++) { 
			contador += 1;
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
				} else if (v == "AstraZeneca" && stock.get(v) > 0) {
					stock.replace("AstraZeneca", stock.get(v)-1);
				} else {
					throw new RuntimeException ("No hay vacunas disponibles");
				}
			}
		
		}
	}

}

