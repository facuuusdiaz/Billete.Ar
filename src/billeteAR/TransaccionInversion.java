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
        StringBuilder sb = new StringBuilder();
        sb.append("Inversion:\n")
          .append("fecha: ").append(this.fechaHora).append("\n")
          .append("origen: ").append(dniOrigen).append(" (").append(cvuOrigen).append(")\n")
          .append("desc: ").append(tipoInversion).append("\n")
          .append("monto: ").append(monto).append("\n")
          .append("plazo: ").append(plazo).append("\n")
          .append(aprobado ? "[Aprobado]" : "[Rechazado]");
          
        return sb.toString();
    }
}