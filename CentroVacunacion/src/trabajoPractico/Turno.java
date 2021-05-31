package trabajoPractico;

public class Turno {

	private Vacuna tipoDeVacuna;
	private Fecha fecha;

	public String getNombreVacuna() {
		return this.tipoDeVacuna.getNombre();
	}
	
	public Fecha darFecha() {
		return this.fecha;
	}

	public Vacuna getVacuna() {
		return this.tipoDeVacuna;
	}

}
