package billeteAR;

import java.time.temporal.ChronoUnit;

public class RentaFija extends Inversiones {

	private double tasaInteres;
	
	public RentaFija(double monto, int dias, double tasaInteres) {
		super(monto, dias, true); 
		this.tasaInteres = tasaInteres;
	}

	@Override
	public double dineroGenerado(Cuenta cuenta) {
		long diasPasados = ChronoUnit.DAYS.between(this.fechaInicio, Utilitarios.hoy());
		return this.monto * (this.tasaInteres / 365.0) * diasPasados;
	}

	@Override
	public String getTipoInversion() {
		return "Renta Fija";
    }
}