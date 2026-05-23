package billeteAR;
import java.time.LocalDate;

public abstract class Actividad {
    protected LocalDate fechaHora;
    protected double monto;
    protected String dniOrigen;
    protected String cvuOrigen;
    protected boolean aprobado;

    public Actividad(double monto, String dniOrigen, String cvuOrigen, boolean aprobado) {
        this.fechaHora = Utilitarios.hoy(); // Uso obligatorio de Utilitarios
        this.monto = monto;
        this.dniOrigen = dniOrigen;
        this.cvuOrigen = cvuOrigen;
        this.aprobado = aprobado;
    }

    public abstract String obtenerTextoFormateado();
}