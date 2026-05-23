package billeteAR;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected String dniUsuario; 
    protected String cvu;
    protected String alias;
    protected double saldo;
    protected List<Actividad> historial; 
    protected int cantidadTransacciones;

    // CONSTRUCTOR ACTUALIZADO: Ya no pide CVU ni Alias por parámetro
    public Cuenta(String dniUsuario, double saldo) {
        this.dniUsuario = dniUsuario;
        this.cvu = generarCVU();       // Se autogenera al nacer
        this.alias = generarAlias();   // Se autogenera al nacer
        this.saldo = saldo;
        this.historial = new ArrayList<>();
        this.cantidadTransacciones = 0;
    }

    // --- MÉTODOS PRIVADOS PARA GENERAR CVU Y ALIAS ---
    private String generarCVU() {
        StringBuilder nuevoCvu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            nuevoCvu.append((int) (Math.random() * 10));
        }
        return nuevoCvu.toString();
    }

    private String generarAlias() {
        String[] palabras = {"sol", "luna", "rio", "mate", "pampa", "tango"};
        String p1 = palabras[(int) (Math.random() * palabras.length)];
        String p2 = palabras[(int) (Math.random() * palabras.length)];
        String p3 = palabras[(int) (Math.random() * palabras.length)];
        return p1 + "." + p2 + "." + p3;
    }

    // --- MÉTODOS PÚBLICOS DE LA CUENTA ---

    public String obtenerFormatoLista() {
        return this.getTipo() + ": " + this.alias + " (" + this.cvu + ")";
    }

    public abstract String getTipo();

    public void transferirDinero(String dniDestino, String cvuDestino, double monto) {
        boolean aprobado = (this.saldo >= monto);
        if (aprobado) {
            this.saldo -= monto;
        }
        this.cantidadTransacciones++;
        
        Transferencia nuevaTransferencia = new Transferencia(monto, this.dniUsuario, this.cvu, dniDestino, cvuDestino, aprobado);
        this.historial.add(nuevaTransferencia);
    }

    public void invertir(Inversiones inversion, int plazo, double monto) {
        boolean aprobado = (this.saldo >= monto);
        if (aprobado) {
            this.saldo -= monto;
        }
        this.cantidadTransacciones++;
        
        TransaccionInversion nuevaInversion = new TransaccionInversion(monto, this.dniUsuario, this.cvu, inversion, plazo, aprobado);
        this.historial.add(nuevaInversion);
    }

    public void mostrarActividad() {
        for (Actividad act : historial) {
            act.mostrarDetalles(); 
        }
    }
    
    public abstract void aplicarMultiplicador(double tasa);

    public double getSaldo() {
        return this.saldo;
    }
    
    public int getCantidadTransacciones() {
        return this.cantidadTransacciones;
    }
}