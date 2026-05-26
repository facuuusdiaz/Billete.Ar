package billeteAR;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected String dniUsuario; 
    protected String cvu;
    protected String alias; // Recibido por parámetro desde la interfaz
    protected double saldo;
    protected List<Actividad> historial; 
    protected int cantidadTransacciones;

    public Cuenta(String dniUsuario, String alias, double saldo) {
        this.dniUsuario = dniUsuario;
        this.alias = alias;
        this.cvu = Utilitarios.generarSiguienteCvu();
        this.saldo = saldo;
        this.historial = new ArrayList<>();
        this.cantidadTransacciones = 0;
    }

    public String obtenerFormatoLista() {
        return this.getTipo() + ": " + this.alias + " (" + this.cvu + ")";
    }

    public abstract String getTipo();

    public void agregarActividad(Actividad act) {
        this.historial.add(act);
        this.cantidadTransacciones++;
    }
    
    @Override
    public String toString() {
        return "[" + getTipo() + "] Alias: " + alias + " | CVU: " + cvu + " | Saldo: $" + saldo;
    }

    public void depositar(double monto) { this.saldo += monto; }
    public void debitar(double monto) { this.saldo -= monto; }
    public double getSaldo() { return this.saldo; }
    public String getCvu() { return this.cvu; }
    public String getAlias() { return this.alias; }
    public String getDniUsuario() { return this.dniUsuario; }
    public List<Actividad> getHistorial() { return this.historial; }
    public int getCantidadTransacciones() { return this.cantidadTransacciones; }
    public abstract void aplicarMultiplicador(double tasa);
}