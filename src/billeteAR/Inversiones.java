package billeteAR;

import java.time.LocalDate;

public abstract class Inversiones {

	protected double monto;
	protected LocalDate fechaInicio;
	protected int dias;
	protected boolean esPrecancelable;
	
	public Inversiones (double monto, LocalDate fechaInicio, int dias, boolean esPrecancelable) {
		
		this.monto = monto;
		this.fechaInicio = Utilitarios.hoy();
		this.dias = dias;
		this.esPrecancelable = esPrecancelable;
	}
	

	public abstract double dineroGenerado(Cuenta cuenta);{
		
	}
	
	public double cancelar(Cuenta cuenta) {
		if (!this.esPrecancelable) {
			throw new RuntimeException("Esta inversion No es Precancelable");
		}
		
		return this.monto + (this.dineroGenerado(cuenta) / 2.0);
	}
}
