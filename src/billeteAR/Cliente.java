package billeteAR;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String dni; // Cambiado a String por la interfaz
    private String nombre;
    private String telefono;
    private String email;
    protected List<Cuenta> cuentas;
    protected double totalInvertido;
	
    public Cliente(String dni, String nombre, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<>();
        this.totalInvertido = 0.0;
    }
	
    public void agregarCuenta(Cuenta c) {
        if (cuentas.contains(c)){
            throw new RuntimeException("Esta cuenta ya existe.");
        }
        cuentas.add(c);
    }
    
    @Override
    public String toString() {
        String info = "Cliente: " + nombre + " (DNI: " + dni + ") | Total Invertido: $" + totalInvertido + "\n";
        for (Cuenta c : cuentas) {
            info += "    -> " + c.toString() + "\n";
        }
        return info;
    }
	
    public double obtenerTotalInvertido() { return this.totalInvertido; }
    public void sumarMontoInversion(double monto) { this.totalInvertido += monto; }
    public void restarMontoInversion(double monto) { this.totalInvertido -= monto; }
    public List<Cuenta> getCuentas() { return this.cuentas; }
    public String getDni() { return this.dni; }
}