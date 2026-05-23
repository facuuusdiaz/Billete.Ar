package billeteAR;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class BilleteAr {

    private Map<String, Cliente> clientes;
    private List<Actividad> historialGlobal; 

    public BilleteAr() {
        this.clientes = new HashMap<>();
        this.historialGlobal = new ArrayList<>();
    }

    // ACTUALIZADO: Pide teléfono y email para la Segunda Parte
    public void crearCliente(int dni, String nombre, String telefono, String email) {
        String dniKey = String.valueOf(dni);
        
        if (!clientes.containsKey(dniKey)) {
            // ATENCIÓN: Vas a tener que agregar telefono y email al constructor de tu clase Cliente
            Cliente nuevoCliente = new Cliente(dni, nombre, telefono, email, new ArrayList<>(), 0.0);
            clientes.put(dniKey, nuevoCliente);
            System.out.println("Cliente " + nombre + " creado con éxito.");
        } else {
            System.out.println("El cliente con DNI " + dni + " ya existe.");
        }
    }

    public void abrirCuentaRegular(int dni) {
        String dniKey = String.valueOf(dni);
        Cliente cliente = clientes.get(dniKey);
        
        if (cliente != null) {
            // El saldo máximo para la regular según el enunciado es 5 millones
            CuentaRegular nuevaCuenta = new CuentaRegular(dniKey, 0.0, 5000000.0); 
            cliente.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta Regular abierta para DNI: " + dni);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void abrirCuentaPremium(int dni) {
        String dniKey = String.valueOf(dni);
        Cliente cliente = clientes.get(dniKey);
        
        if (cliente != null) {
            // El saldo mínimo para la premium según el enunciado es 500 mil
            CuentaPremium nuevaCuenta = new CuentaPremium(dniKey, 0.0, 500000.0);
            cliente.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta Premium abierta para DNI: " + dni);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void abrirCuentaCoorpo(int dni) {
        String dniKey = String.valueOf(dni);
        Cliente cliente = clientes.get(dniKey);
        
        if (cliente != null) {
            // Valores por defecto (luego los ajustaremos con la lógica de empresas)
            String cuitFalso = "30-12345678-9"; 
            boolean certificada = false;       
            
            CuentaCoorpo nuevaCuenta = new CuentaCoorpo(dniKey, 0.0, cuitFalso, certificada);
            cliente.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta Corporativa abierta para DNI: " + dni);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public List<Actividad> consultarHistorialGlobal() {
        return this.historialGlobal;
    }

    public List<Cuenta> obtenerCuentaMayorVolumen() {
        List<Cuenta> cuentasMaxVolumen = new ArrayList<>();
        int maxTransacciones = -1;

        for (Cliente cliente : clientes.values()) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                int transacciones = cuenta.getCantidadTransacciones();
                
                if (transacciones > maxTransacciones) {
                    maxTransacciones = transacciones;
                    cuentasMaxVolumen.clear();
                    cuentasMaxVolumen.add(cuenta);
                } else if (transacciones == maxTransacciones && maxTransacciones > -1) {
                    cuentasMaxVolumen.add(cuenta);
                }
            }
        }
        return cuentasMaxVolumen;
    }
}