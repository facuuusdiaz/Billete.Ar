package billeteAR;


public class CuentaRegular extends Cuenta {
    private double saldoMax;

    public CuentaRegular(String dniUsuario,  double saldo, double saldoMax) {
        super(dniUsuario,  saldo);
        this.saldoMax = saldoMax;
    }

    @Override
    public String getTipo() {
        return "Regular";
    }
    
    // ... acá sigue tu método aplicarMultiplicador tal cual lo tenías ...

    @Override
    public void aplicarMultiplicador(double tasa) {
        double nuevoSaldo = this.saldo * tasa;
        
        // Verifica que no se pase del tope máximo
        if (nuevoSaldo <= saldoMax) {
            this.saldo = nuevoSaldo;
        } else {
            this.saldo = saldoMax; 
        }
        
        this.cantidadTransacciones++;
        System.out.println("Multiplicador aplicado. Nuevo saldo: $" + this.saldo);
    }
}