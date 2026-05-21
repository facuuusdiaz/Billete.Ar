package billeteAR;

import java.time.LocalDate;

public abstract class Actividad {
    protected LocalDate fechaHora;
    protected double monto;
    protected String dniOrigen;
    protected String cvuOrigen;
    protected boolean aprobado; // true = Aprobado, false = Rechazado

    public Actividad(double monto, String dniOrigen, String cvuOrigen, boolean aprobado) {
        this.fechaHora = LocalDate.now();
        this.monto = monto;
        this.dniOrigen = dniOrigen;
        this.cvuOrigen = cvuOrigen;
        this.aprobado = aprobado;
    }

    public abstract void mostrarDetalles();
}