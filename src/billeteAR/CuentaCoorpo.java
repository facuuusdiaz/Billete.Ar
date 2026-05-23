package billeteAR;

public class CuentaCoorpo extends Cuenta {
    private String cuitEmpresa;
    private boolean certificacion;

    // CONSTRUCTOR CORREGIDO
    public CuentaCoorpo(String dniUsuario, String alias, double saldo, String cuitEmpresa, boolean certificacion) {
        super(dniUsuario, alias, saldo); 
        this.cuitEmpresa = cuitEmpresa;
        this.certificacion = certificacion;
    }

    @Override
    public String getTipo() { return "Corporativa"; }

    @Override
    public void aplicarMultiplicador(double tasa) {
        if (certificacion) {
            this.saldo = this.saldo * (tasa * 1.10); 
        } else {
            this.saldo = this.saldo * tasa;
        }
        this.cantidadTransacciones++;
        System.out.println("Multiplicador Corporativo aplicado para la empresa CUIT: " + this.cuitEmpresa + ". Nuevo saldo: $" + this.saldo);
    }
}