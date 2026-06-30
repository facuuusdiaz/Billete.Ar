package billeteAR;

public class CuentaPremium extends Cuenta {
    private double saldoMinimo;

    public CuentaPremium(String dniUsuario, String alias, double saldo, double saldoMinimo) {
        super(dniUsuario, alias, saldo); 
        this.saldoMinimo = saldoMinimo;
    }

    @Override
    public String getTipo() { return "Premium"; }

    @Override
    public void aplicarMultiplicador(double tasa) {
        if (this.saldo >= saldoMinimo) {
        	//Se suma el interes al saldo existente
            this.saldo += this.saldo * tasa;
            this.cantidadTransacciones++;
            System.out.println("Multiplicador Premium aplicado. Nuevo saldo: $" + this.saldo);
        } else {
            System.out.println("El saldo actual no supera el mínimo requerido para aplicar el beneficio.");
        }
    }
}