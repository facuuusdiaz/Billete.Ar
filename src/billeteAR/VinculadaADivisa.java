package billeteAR;

import java.time.temporal.ChronoUnit;

public class VinculadaADivisa extends Inversiones {
	
	private double tasaInteres;
	private String divisa;
	private double cotizacionInicial; // Guardamos a cuánto estaba la moneda el día 1

	public VinculadaADivisa(double monto, int dias, double tasaInteres, String divisa) {
		super(monto, dias, true); 
		this.tasaInteres = tasaInteres;
		this.divisa = divisa;
		this.cotizacionInicial = Utilitarios.consultarCotizacion(divisa);
	}

	@Override
	public double dineroGenerado(Cuenta cuenta) {
		// Calculamos cuántos días pasaron desde que se creó
		long diasPasados = ChronoUnit.DAYS.between(this.fechaInicio, Utilitarios.hoy());
		
		// Hacemos la matemática exacta que pide el TP
		double divisasCompradas = this.monto / this.cotizacionInicial;
		double interesEnDivisas = divisasCompradas * (this.tasaInteres / 365.0) * diasPasados;
		double cotizacionActual = Utilitarios.consultarCotizacion(this.divisa);
		
		return ((divisasCompradas + interesEnDivisas) * cotizacionActual) - this.monto;
	}

	
	
	@Override
	public double cancelar(Cuenta cuenta) {
		if (!this.esPrecancelable) {
			throw new RuntimeException("Esta inversion No es Precancelable");
		}
		
		long diasPasados = ChronoUnit.DAYS.between(this.fechaInicio, Utilitarios.hoy());
		double divisasCompradas = this.monto / this.cotizacionInicial;
		
		// Calculamos el interés y lo dividimos a la mitad por cancelar antes
		double interesEnDivisas = divisasCompradas * (this.tasaInteres / 365.0) * diasPasados;
		double interesPenalizado = interesEnDivisas / 2.0;
		
		double cotizacionActual = Utilitarios.consultarCotizacion(this.divisa);
		
		// Devolvemos los dólares comprados (completos) + el interés (a la mitad), pasados a pesos
		return (divisasCompradas + interesPenalizado) * cotizacionActual;
	}
	
	@Override
	public String getTipoInversion() {
		return "Vinculada a Divisa: " + this.divisa;
	}
}