package billeteAR;


public class CuentaCoorpo extends Cuenta {
    private String cuitEmpresa;
    private boolean certificacion;

    public CuentaCoorpo(String dniUsuario,  double saldo, String cuitEmpresa, boolean certificacion) {
        super(dniUsuario,  saldo);
        this.cuitEmpresa = cuitEmpresa;
        this.certificacion = certificacion;
    }

    @Override
    public String getTipo() {
        return "Corporativa"; // Cambiado a "Corporativa" para el formato del String
    }

    // ... acá sigue tu método aplicarMultiplicador tal cual lo tenías ...

    @Override
    public void aplicarMultiplicador(double tasa) {
        // Le damos un pequeño plus a la tasa si la empresa está certificada
        if (certificacion) {
            this.saldo = this.saldo * (tasa * 1.10); // 10% de bonus sobre la tasa // DUDA PQ NOSE CUANTO SON LAS OTRAS CUENTASSSSSSSSSSSSSSSSSSS
        } else {
            this.saldo = this.saldo * tasa;
        }
        
        this.cantidadTransacciones++;
        // ACÁ ESTÁ EL CAMBIO: Agregamos el cuitEmpresa al mensaje
        System.out.println("Multiplicador Corporativo aplicado para la empresa CUIT: " + this.cuitEmpresa + ". Nuevo saldo: $" + this.saldo);
    }
}