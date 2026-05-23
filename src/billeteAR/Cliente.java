package billeteAR;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private int dni;
	private String nombre;
	protected List <Cuenta> cuentas;
	protected double totalInvertido;
	
	public Cliente(int dni, String nombre, List <Cuenta> cuenta, double totalInvertido) {
		
		this.dni = dni;
		this.nombre = nombre;
		this.cuentas = cuenta;
		this.totalInvertido = totalInvertido;
		
	}
	
	public void agregarCuenta (Cuenta C) {
		
		if (cuentas.contains(C)){
		 throw new RuntimeException("Esta cuenta ya existe.");
		}
		else {
			cuentas.add(C);
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
	
}
