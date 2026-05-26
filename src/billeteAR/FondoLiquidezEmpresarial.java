package billeteAR;

import java.time.temporal.ChronoUnit;

public class FondoLiquidezEmpresarial extends Inversiones {
	
	public FondoLiquidezEmpresarial(double monto, int dias) {
		super(monto, dias, false); // false = no es precancelable
	}

	@Override
	public double dineroGenerado(Cuenta cuenta) {
		long diasPasados = ChronoUnit.DAYS.between(this.fechaInicio, Utilitarios.hoy());
		//Consultamos la tasa dinamicamente cumpliendo el requerimiento
		double tasaInteres = Utilitarios.consultarCotizacion("FLE");
		return this.monto * (tasaInteres / 365.0) * diasPasados;
	}

	@Override
	public String getTipoInversion() {
		return "Fondo de Liquidez Empresarial (FLE)";
	}
}