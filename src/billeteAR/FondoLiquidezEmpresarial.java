package billeteAR;

import java.time.temporal.ChronoUnit;

public class FondoLiquidezEmpresarial extends Inversiones {

	private double tasaInteres = 0.08; // 8% fijo según el TP
	
	public FondoLiquidezEmpresarial(double monto, int dias) {
		super(monto, dias, false); // false = no es precancelable
	}

	@Override
	public double dineroGenerado(Cuenta cuenta) {
		long diasPasados = ChronoUnit.DAYS.between(this.fechaInicio, Utilitarios.hoy());
		return this.monto * (this.tasaInteres / 365.0) * diasPasados;
	}

	@Override
	public String getTipoInversion() {
		return "Fondo de Liquidez Empresarial (FLE)";
	}
}