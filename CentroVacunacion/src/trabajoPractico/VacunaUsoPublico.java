package trabajoPractico;

public class VacunaUsoPublico extends Vacuna{
	
		private boolean prioridadMayores;
		
		public VacunaUsoPublico (String nombre, Integer temperatura, Fecha vencimiento) {
			super (nombre,temperatura,vencimiento);
			this.prioridadMayores= false;
		}
		
		@Override
		protected boolean getPrioridadMayores() {
			return prioridadMayores;
		}
	}



