package billeteAR;

public class TransaccionInversion extends Actividad {
    private String tipoInversion;
    private int plazo; 

    public TransaccionInversion(double monto, String dniOrigen, String cvuOrigen, String tipoInversion, int plazo, boolean aprobado) {
        super(monto, dniOrigen, cvuOrigen, aprobado);
        this.tipoInversion = tipoInversion;
        this.plazo = plazo;
    }

    @Override
    public String obtenerTextoFormateado() {
        return "inversion:\n" +
               "origen: " + dniOrigen + " (" + cvuOrigen + ")\n" +
               "desc: " + tipoInversion + "\n" +
               "monto: " + monto + "\n" +
               "plazo: " + plazo + "\n" +
               (aprobado ? "[Aprovado]" : "[Rechazado]");
    }
}