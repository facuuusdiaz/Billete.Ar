package billeteAR;

public class Transferencia extends Actividad {
    private String dniDestino;
    private String cvuDestino;

    public Transferencia(double monto, String dniOrigen, String cvuOrigen, String dniDestino, String cvuDestino, boolean aprobado) {
        super(monto, dniOrigen, cvuOrigen, aprobado);
        this.dniDestino = dniDestino;
        this.cvuDestino = cvuDestino;
    }

    @Override
    public String obtenerTextoFormateado() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ransferencia:\n") 
          .append("■fecha: ").append(this.fechaHora).append("\n")
          .append("origen: ").append(dniOrigen).append(" (").append(cvuOrigen).append(")\n")
          .append("destino: ").append(dniDestino).append(" (").append(cvuDestino).append(")\n")
          .append("monto: ").append(monto).append("\n")
          .append(aprobado ? "[Aprobado]" : "[Rechazado]");
          
        return sb.toString();
    }
}