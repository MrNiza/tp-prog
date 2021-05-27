package trabajoPractico;

public class VacunaPrioridad extends Vacuna{
	private boolean prioridadMayores;
	
	public VacunaPrioridad (String nombre, Integer temperatura, Fecha vencimiento) {
		super (nombre,temperatura,vencimiento);
		this.prioridadMayores= true;
	}

	@Override
	protected boolean getPrioridadMayores() {
		return prioridadMayores;
	}
}
