package billeteAR;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private int dni;
	private String nombre;
	private String telefono; // Agregado para la Fase 2
	private String email;    // Agregado para la Fase 2
	protected List<Cuenta> cuentas;
	protected double totalInvertido;
	
	// Constructor actualizado con teléfono y email
	public Cliente(int dni, String nombre, String telefono, String email, List<Cuenta> cuentas, double totalInvertido) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.cuentas = cuentas;
		this.totalInvertido = totalInvertido;
	}
	
	public void agregarCuenta(Cuenta c) { // Cambié la 'C' mayúscula por 'c' minúscula
		if (cuentas.contains(c)){
		    throw new RuntimeException("Esta cuenta ya existe.");
		} else {
			cuentas.add(c);
		}
	}
	
	public double obtenerTotalInvertido() {
		return this.totalInvertido;
	}
	
	public void sumarMontoInversion(double monto) {
		this.totalInvertido += monto;
	}
	
	public List<Cuenta> getCuentas() {
	    return this.cuentas; 
	}
	
	// --- GETTERS (Opcionales, pero sacan las advertencias amarillas del editor) ---
	public int getDni() { return this.dni; }
	public String getNombre() { return this.nombre; }
	public String getTelefono() { return this.telefono; }
	public String getEmail() { return this.email; }
}