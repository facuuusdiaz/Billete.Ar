package billeteAR;

import java.time.LocalDate;

public class VinculadaADivisa extends Inversiones {
	
	private double tasaInteres;

	public VinculadaADivisa(double monto, LocalDate fechaInicio, int dias, double tasaInteres) {
		super(monto, fechaInicio, dias, true);
		this.tasaInteres = tasaInteres;
		
	}

	@Override
	public double dineroGenerado(Cuenta cuenta) {
		double saldoAntes = cuenta.getSaldo();
		
		cuenta.aplicarMultiplicador(this.tasaInteres);
		
		return cuenta.getSaldo() - saldoAntes;
	}

}
