package billeteAR;

public class TransaccionInversion extends Actividad {
    private Inversiones inversionAsociada;
    private int plazo; 

    public TransaccionInversion(double monto, String dniOrigen, String cvuOrigen, Inversiones inversionAsociada, int plazo, boolean aprobado) {
        super(monto, dniOrigen, cvuOrigen, aprobado);
        this.inversionAsociada = inversionAsociada;
        this.plazo = plazo;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Inversion:");
        System.out.println("  fecha: " + this.fechaHora);
        System.out.println("  origen: " + this.dniOrigen + " (" + this.cvuOrigen + ")");
        // Usamos el toString de la inversión o un texto por defecto para 'desc'
        System.out.println("  desc: " + (inversionAsociada != null ? inversionAsociada.toString() : "Inversión General"));
        System.out.println("  monto: " + this.monto);
        System.out.println("  plazo: " + this.plazo);
        System.out.println("  " + (this.aprobado ? "[Aprobado]" : "[Rechazado]"));
    }
}