package billeteAR;

public class CuentaRegular extends Cuenta {
    private double saldoMax;

    // CONSTRUCTOR CORREGIDO: Ahora recibe y pasa el alias al super()
    public CuentaRegular(String dniUsuario, String alias, double saldo, double saldoMax) {
        super(dniUsuario, alias, saldo); 
        this.saldoMax = saldoMax;
    }

    @Override
    public String getTipo() { return "Regular"; }
    

    @Override
    public void depositar(double monto) {
        if (this.saldo + monto > this.saldoMax) {
            // CAMBIO: Ahora lanza IllegalStateException
            throw new IllegalStateException("El depósito supera el límite máximo de 5 millones.");
        }
        this.saldo += monto;
    }

    @Override
    public void aplicarMultiplicador(double tasa) {
        double nuevoSaldo = this.saldo * tasa;
        if (nuevoSaldo <= saldoMax) {
            this.saldo = nuevoSaldo;
        } else {
            this.saldo = saldoMax; 
        }
        this.cantidadTransacciones++;
        System.out.println("Multiplicador aplicado. Nuevo saldo: $" + this.saldo);
    }
}