package billeteAR;

import java.time.LocalDate;

public class RentaFija extends Inversiones {

	private double tasaInteres;
	
	public RentaFija(double monto, LocalDate fechaInicio, int dias, double tasaInteres) {
		super(monto, fechaInicio, dias, true);
		this.tasaInteres = tasaInteres;
	}

	@Override
	public double dineroGenerado(Cuenta cuenta) {
		// 1. Guardamos el saldo antes de tocar nada
		double saldoAntes = cuenta.getSaldo();
		
		// 2. Ejecutamos tu método tal cual lo armaste
		cuenta.aplicarMultiplicador(this.tasaInteres);
		
		// 3. Calculamos y devolvemos la diferencia generada
		return cuenta.getSaldo() - saldoAntes;

	
}
}
