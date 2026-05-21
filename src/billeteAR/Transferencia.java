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
    public void mostrarDetalles() {
        System.out.println("Transferencia:");
        System.out.println("  fecha: " + this.fechaHora);
        System.out.println("  origen: " + this.dniOrigen + " (" + this.cvuOrigen + ")");
        System.out.println("  destino: " + this.dniDestino + " (" + this.cvuDestino + ")");
        System.out.println("  monto: " + this.monto);
        System.out.println("  " + (this.aprobado ? "[Aprobado]" : "[Rechazado]"));
    }
}