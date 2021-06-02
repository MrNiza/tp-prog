package trabajoPractico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Almacen {
	private HashMap<String, Integer> stock;
	private HashMap<String, Integer> vencidas;
	private ArrayList<Vacuna> listaVacunas;
	
	public Almacen () { 
		HashMap<String, Integer> stock = new HashMap <String,Integer>();
		HashMap<String, Integer> vencidas = new HashMap <String,Integer>();
		ArrayList<Vacuna> listaVacunas = new ArrayList <Vacuna>();
	}
		
	public HashMap<String,Integer> reporteVacunasVencidas() {
		return vencidas;
	}
	
	public void quitarVencidas() { 
		for (Vacuna v : listaVacunas) { 
			if (v.DiasVencimiento() <= 0) {
				vencidas.replace(v.getNombre(), vencidas.get(v.getNombre()) + 1 );
				listaVacunas.remove(v);
			}
		}
	}
	
	public HashSet<Vacuna> asignarVacunasEspeciales(int capacidad) {
		HashSet <Vacuna> vacunasListas = new HashSet <Vacuna>();
		Integer contador = capacidad;
		for (Vacuna v : listaVacunas) {
			if(contador > 0 && v.getPrioridadMayores()) {
				contador--;
				v.setAsignadaEnEspera();
				vacunasListas.add(v);
			}
			else { 
				break;
			}
		}
		return vacunasListas;
	}
	
	public HashSet<Vacuna> asignarVacunasGenerales(int capacidad) {
		HashSet <Vacuna> vacunasListas = new HashSet <Vacuna>();
		Integer contador = capacidad;
		for (Vacuna v : listaVacunas) {
			if(contador > 0 && !v.getPrioridadMayores()) {
				contador--;
				v.setAsignadaEnEspera();
				vacunasListas.add(v);
			}
			else { 
				break;
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
	
	public void ingresarVacuna(String nombre) {
		if (verificarVacuna(nombre)) {
			Vacuna vacuna;
			if (nombre == "Sputnik") {
				vacuna = new Vacuna3Grados(nombre,true,Fecha.hoy());
				this.listaVacunas.add(vacuna);
			}
			if (nombre == "Pfizer") {
				vacuna = new VacunaMenos18(nombre,true,Fecha.hoy());
				listaVacunas.add(vacuna);
			}
			if (nombre == "Sinopharm") {
				vacuna = new Vacuna3Grados(nombre,false,Fecha.hoy());
				listaVacunas.add(vacuna);
			}
			if (nombre == "Moderna") {
				vacuna = new VacunaMenos18(nombre,false,Fecha.hoy());
				listaVacunas.add(vacuna);
			}
			if (nombre == "Astrazeneca") {
				vacuna = new Vacuna3Grados(nombre,false,Fecha.hoy());
				listaVacunas.add(vacuna);
			}
		}
		else {
			throw new RuntimeException("La vacuna ingresada no existe");
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
