package billeteAR;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class BilleteAr {

    // Atributos según el UML
    private Map<String, Cliente> clientes;
    private List<Actividad> historialGlobal; // Adaptado al nombre de tu clase Actividad

    // Constructor
    public BilleteAr() {
        this.clientes = new HashMap<>();
        this.historialGlobal = new ArrayList<>();
    }

    // Método para generar un CVU (simulado)
    public String crearCVU() {
        StringBuilder cvu = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            cvu.append((int) (Math.random() * 10));
        }
        return cvu.toString();
    }

    // Método para generar un Alias (simulado)
    public String crearAlias() {
        String[] palabras = {"sol", "luna", "rio", "mate", "pampa", "tango"};
        String p1 = palabras[(int) (Math.random() * palabras.length)];
        String p2 = palabras[(int) (Math.random() * palabras.length)];
        String p3 = palabras[(int) (Math.random() * palabras.length)];
        return p1 + "." + p2 + "." + p3;
    }

    
    
    
    
    
 // Método para crear un nuevo cliente
    public void crearCliente(int dni, String nombre) {
        String dniKey = String.valueOf(dni);
        
        if (!clientes.containsKey(dniKey)) {
            // Le pasamos la lista vacía y 0.0 como total invertido inicial
            Cliente nuevoCliente = new Cliente(dni, nombre, new ArrayList<>(), 0.0);
            clientes.put(dniKey, nuevoCliente);
            System.out.println("Cliente " + nombre + " creado con éxito.");
        } else {
            System.out.println("El cliente con DNI " + dni + " ya existe.");
        }
    }
    
    
    

    // Método para abrir Cuenta Regular
    public void abrirCuentaRegular(int dni) {
        String dniKey = String.valueOf(dni);
        Cliente cliente = clientes.get(dniKey);
        
        if (cliente != null) {
            String cvu = crearCVU();
            String alias = crearAlias();
            double saldoInicial = 0.0;
            double saldoMax = 500000.0; // VALOR POR DEFECTO: Podés cambiarlo
            
            // Usando tu constructor exacto
            CuentaRegular nuevaCuenta = new CuentaRegular(dniKey, cvu, alias, saldoInicial, saldoMax); 
            cliente.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta Regular abierta para DNI: " + dni);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Método para abrir Cuenta Premium
    public void abrirCuentaPremium(int dni) {
        String dniKey = String.valueOf(dni);
        Cliente cliente = clientes.get(dniKey);
        
        if (cliente != null) {
            String cvu = crearCVU();
            String alias = crearAlias();
            double saldoInicial = 0.0;
            double saldoMinimo = 10000.0; // VALOR POR DEFECTO: Podés cambiarlo
            
            CuentaPremium nuevaCuenta = new CuentaPremium(dniKey, cvu, alias, saldoInicial, saldoMinimo);
            cliente.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta Premium abierta para DNI: " + dni);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Método para abrir Cuenta Coorpo
    public void abrirCuentaCoorpo(int dni) {
        String dniKey = String.valueOf(dni);
        Cliente cliente = clientes.get(dniKey);
        
        if (cliente != null) {
            String cvu = crearCVU();
            String alias = crearAlias();
            double saldoInicial = 0.0;
            String cuitFalso = "30-12345678-9"; // VALOR POR DEFECTO
            boolean certificada = false;       // VALOR POR DEFECTO
            
            CuentaCoorpo nuevaCuenta = new CuentaCoorpo(dniKey, cvu, alias, saldoInicial, cuitFalso, certificada);
            cliente.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta Corporativa abierta para DNI: " + dni);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Método para consultar el historial global
    public List<Actividad> consultarHistorialGlobal() {
        return this.historialGlobal;
    }

    // Método para obtener la cuenta con mayor volumen
    public List<Cuenta> obtenerCuentaMayorVolumen() {
        List<Cuenta> cuentasMaxVolumen = new ArrayList<>();
        int maxTransacciones = -1;

        for (Cliente cliente : clientes.values()) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                // Requiere agregar un getter en tu clase Cuenta
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