package billeteAR;

import java.time.LocalDate;

public class FondoLiquidezEmpresarial extends Inversiones {

	private double tasaInteres;
	
	public FondoLiquidezEmpresarial(double monto, LocalDate fechaInicio, int dias, double tasaInteres) {
		super(monto, fechaInicio, dias, false);
		this.tasaInteres = tasaInteres;
	}

	@Override
	public double dineroGenerado(Cuenta cuenta) {
		double saldoAntes = cuenta.getSaldo();
		
		cuenta.aplicarMultiplicador(this.tasaInteres);
		
		return cuenta.getSaldo() - saldoAntes;
	}

}
