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
        this.cvu = generarCVU(); // El CVU sigue autogenerándose de forma segura
        this.saldo = saldo;
        this.historial = new ArrayList<>();
        this.cantidadTransacciones = 0;
    }

    private String generarCVU() {
        StringBuilder nuevoCvu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            nuevoCvu.append((int) (Math.random() * 10));
        }
        return nuevoCvu.toString();
    }

    public String obtenerFormatoLista() {
        return this.getTipo() + ": " + this.alias + " (" + this.cvu + ")";
    }

    public abstract String getTipo();

    public void agregarActividad(Actividad act) {
        this.historial.add(act);
        this.cantidadTransacciones++;
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