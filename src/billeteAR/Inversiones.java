package billeteAR;
import java.time.LocalDate;

public abstract class Inversiones {
    private static int idGlobal = 1; // Generador único secuencial
    protected int id;
    protected double monto;
    protected LocalDate fechaInicio;
    protected int dias;
    protected boolean esPrecancelable;
	
    public Inversiones(double monto, int dias, boolean esPrecancelable) {
        this.id = idGlobal++;
        this.monto = monto;
        this.fechaInicio = Utilitarios.hoy();
        this.dias = dias;
        this.esPrecancelable = esPrecancelable;
    }

    public abstract double dineroGenerado(Cuenta cuenta);
    public abstract String getTipoInversion();
	
    public double cancelar(Cuenta cuenta) {
        if (!this.esPrecancelable) {
            throw new RuntimeException("Esta inversion No es Precancelable");
        }
        return this.monto + (this.dineroGenerado(cuenta) / 2.0);
    }

    public int getId() { return this.id; }
    public double getMonto() { return this.monto; }
}