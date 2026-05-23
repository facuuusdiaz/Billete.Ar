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
        return "transferencia:\n" +
               "origen: " + dniOrigen + " (" + cvuOrigen + ")\n" +
               "destino: " + dniDestino + " (" + cvuDestino + ")\n" +
               "monto: " + monto + "\n" +
               (aprobado ? "[Aprovado]" : "[Rechazado]"); // Nota: Usé la 'v' que figura en el comentario del docente
    }
}