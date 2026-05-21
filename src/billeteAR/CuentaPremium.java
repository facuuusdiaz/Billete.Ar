package billeteAR;


public class CuentaPremium extends Cuenta {
    private double saldoMinimo;

    public CuentaPremium(String dniUsuario, String cvu, String alias, double saldo, double saldoMinimo) {
        super(dniUsuario, cvu, alias, saldo);
        this.saldoMinimo = saldoMinimo;
    }

    @Override
    public String getTipo() {
        return "Premium";
    }

    // ... acá sigue tu método aplicarMultiplicador tal cual lo tenías ...

    @Override
    public void aplicarMultiplicador(double tasa) {
        // Solo aplica si el cliente tiene el saldo mínimo requerido
        if (this.saldo >= saldoMinimo) {
            this.saldo = this.saldo * tasa;
            this.cantidadTransacciones++;
            System.out.println("Multiplicador Premium aplicado. Nuevo saldo: $" + this.saldo);
        } else {
            System.out.println("El saldo actual no supera el mínimo requerido para aplicar el beneficio.");
        }
    }
}

