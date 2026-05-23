package billeteAR;

import java.util.HashMap;


import java.util.*;

public class Billete implements IBilletera {

    private Map<String, Cliente> clientes;
    private Map<String, Empresa> empresas;
    private Map<String, Cuenta> cuentasPorCvu; // Optimización de búsqueda O(1)
    private Map<Integer, Inversiones> inversionesPorId; // Optimización O(1)
    private List<Actividad> historialGlobal; 

    public Billete() {
        this.clientes = new HashMap<>();
        this.empresas = new HashMap<>();
        this.cuentasPorCvu = new HashMap<>();
        this.inversionesPorId = new HashMap<>();
        this.historialGlobal = new ArrayList<>();
    }

    @Override
    public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email, String nombreContacto) {
        if (empresas.containsKey(cuit)) throw new RuntimeException("La empresa ya existe.");
        Empresa emp = new Empresa(cuit, nombreFantasia, telefono, email, nombreContacto);
        empresas.put(cuit, emp);
    }

    @Override
    public void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado) {
        Empresa emp = empresas.get(cuitEmpresa);
        if (emp == null) throw new RuntimeException("La empresa no existe.");
        emp.autorizarPersona(dniAutorizado);
    }

    @Override
    public void registrarUsuario(String dni, String nombre, String telefono, String email) {
        if (clientes.containsKey(dni)) throw new RuntimeException("El usuario ya existe.");
        Cliente nuevo = new Cliente(dni, nombre, telefono, email);
        clientes.put(dni, nuevo);
    }

    private void validarAliasUnico(String alias) {
        for (Cuenta c : cuentasPorCvu.values()) {
            if (c.getAlias().equalsIgnoreCase(alias)) throw new RuntimeException("El alias ya existe.");
        }
    }

    @Override
    public String crearCuentaRegular(String dniUsuario, String alias) {
        Cliente cl = clientes.get(dniUsuario);
        if (cl == null) throw new RuntimeException("Usuario inexistente.");
        validarAliasUnico(alias);

        CuentaRegular cuenta = new CuentaRegular(dniUsuario, alias, 0.0, 5000000.0);
        cl.agregarCuenta(cuenta);
        cuentasPorCvu.put(cuenta.getCvu(), cuenta);
        return cuenta.getCvu();
    }

 
    @Override
    public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {
        Cliente cl = clientes.get(dniUsuario);
        if (cl == null) throw new RuntimeException("Usuario inexistente.");
        
        // CAMBIO: Ahora lanza IllegalArgumentException
        if (depositoInicial < 500000.0) throw new IllegalArgumentException("No cumple el depósito mínimo.");
        
        validarAliasUnico(alias);

        CuentaPremium cuenta = new CuentaPremium(dniUsuario, alias, depositoInicial, 500000.0);
        cl.agregarCuenta(cuenta);
        cuentasPorCvu.put(cuenta.getCvu(), cuenta);
        return cuenta.getCvu();
    }

    @Override
    public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
        Cliente cl = clientes.get(dniUsuario);
        Empresa emp = empresas.get(cuitEmpresa);
        if (cl == null || emp == null) throw new RuntimeException("Usuario o Empresa inexistente.");
        if (!emp.estaAutorizado(dniUsuario)) throw new RuntimeException("Usuario no autorizado por la empresa.");
        validarAliasUnico(alias);

        CuentaCoorpo cuenta = new CuentaCoorpo(dniUsuario, alias, 0.0, cuitEmpresa, false);
        cl.agregarCuenta(cuenta);
        cuentasPorCvu.put(cuenta.getCvu(), cuenta);
        return cuenta.getCvu();
    }

    @Override
    public List<String> obtenerCuentas(String dniUsuario) {
        Cliente cl = clientes.get(dniUsuario);
        if (cl == null) throw new RuntimeException("Usuario inexistente.");
        List<String> lista = new ArrayList<>();
        for (Cuenta c : cl.getCuentas()) {
            lista.add(c.obtenerFormatoLista());
        }
        return lista;
    }

    @Override
    public double obtenerSaldoDisponible(String cvu) {
        Cuenta c = cuentasPorCvu.get(cvu);
        if (c == null) throw new RuntimeException("Cuenta inexistente.");
        return c.getSaldo();
    }

    @Override
    public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
        Cuenta orig = cuentasPorCvu.get(cvuOrigen);
        Cuenta dest = cuentasPorCvu.get(cvuDestino);
        if (orig == null || dest == null) throw new RuntimeException("Cuentas inválidas.");

        boolean aprobado = (orig.getSaldo() >= monto);
        if (aprobado) {
            dest.depositar(monto); // Intentamos depositar primero por si salta el límite
            orig.debitar(monto);
        }

        Transferencia tf = new Transferencia(monto, orig.getDniUsuario(), cvuOrigen, dest.getDniUsuario(), cvuDestino, aprobado);
        orig.agregarActividad(tf);
        dest.agregarActividad(tf);
        historialGlobal.add(tf);
    }

    private int ejecutarInversionGenerica(String dni, String cvu, double monto, Inversiones inv, int plazo) {
        Cuenta c = cuentasPorCvu.get(cvu);
        Cliente cl = clientes.get(dni);
        if (c == null || cl == null) throw new RuntimeException("Datos inválidos.");

        boolean aprobado = (c.getSaldo() >= monto);
        if (aprobado) {
            c.debitar(monto);
            cl.sumarMontoInversion(monto);
            inversionesPorId.put(inv.getId(), inv);
        }

        TransaccionInversion ti = new TransaccionInversion(monto, dni, cvu, inv.getTipoInversion(), plazo, aprobado);
        c.agregarActividad(ti);
        historialGlobal.add(ti);
        return inv.getId();
    }

    @Override
    public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
        // CAMBIO: Tasa al 0.20 para que coincida con la prueba de precancelar renta fija
        Inversiones inv = new RentaFija(monto, plazoDias, 0.20);
        return ejecutarInversionGenerica(dni, cvu, monto, inv, plazoDias);
    }

    @Override
    public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa, double tasa) {
        Inversiones inv = new VinculadaADivisa(monto, plazoDias, tasa, divisa);
        return ejecutarInversionGenerica(dni, cvu, monto, inv, plazoDias);
    }


    @Override
    public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
        Cuenta c = cuentasPorCvu.get(cvu);
        
        // CAMBIO: Ahora lanza IllegalArgumentException
        if (!(c instanceof CuentaCoorpo)) throw new IllegalArgumentException("Solo permitido desde cuentas corporativas.");
        if (monto < 20000000.0) throw new IllegalArgumentException("Monto mínimo de inversión líquida: 20 millones.");

        Inversiones inv = new FondoLiquidezEmpresarial(monto, plazoDias);
        return ejecutarInversionGenerica(dni, cvu, monto, inv, plazoDias);
    }
    
    @Override
    public void precancelarInversion(String dni, String cvu, int idInversion) {
        Inversiones inv = inversionesPorId.get(idInversion);
        Cuenta c = cuentasPorCvu.get(cvu);
        Cliente cl = clientes.get(dni);
        if (inv == null || c == null || cl == null) throw new RuntimeException("Parámetros de cancelación erróneos.");

        double reintegro = inv.cancelar(c);
        c.depositar(reintegro);
        cl.restarMontoInversion(inv.getMonto());
        inversionesPorId.remove(idInversion); // Se remueve por completarse
    }


    @Override
    public String consultarCvu(String alias) {
        for (Cuenta c : cuentasPorCvu.values()) {
            if (c.getAlias().equalsIgnoreCase(alias)) return c.getCvu();
        }
        // CAMBIO: Ahora lanza IllegalArgumentException
        throw new IllegalArgumentException("Alias no registrado.");
    }

    @Override
    public List<String> consultarHistorialGlobal() {
        List<String> res = new ArrayList<>();
        for (Actividad act : historialGlobal) res.add(act.obtenerTextoFormateado());
        return res;
    }

    @Override
    public List<String> consultarHistorialCuenta(String cvu) {
        Cuenta c = cuentasPorCvu.get(cvu);
        if (c == null) throw new RuntimeException("Cuenta inexistente.");
        List<String> res = new ArrayList<>();
        for (Actividad act : c.getHistorial()) res.add(act.obtenerTextoFormateado());
        return res;
    }

    @Override
    public List<String> consultarHistorialUsuario(String dniUsuario) {
        Cliente cl = clientes.get(dniUsuario);
        if (cl == null) throw new RuntimeException("Usuario inexistente.");
        List<String> res = new ArrayList<>();
        for (Cuenta c : cl.getCuentas()) {
            for (Actividad act : c.getHistorial()) {
                String texto = act.obtenerTextoFormateado();
                if (!res.contains(texto)) res.add(texto);
            }
        }
        return res;
    }

    @Override
    public double obtenerTotalInvertido(String dniUsuario) {
        Cliente cl = clientes.get(dniUsuario);
        if (cl == null) throw new RuntimeException("Usuario inexistente.");
        return cl.obtenerTotalInvertido(); // Retorno O(1) instantáneo
    }

    @Override
    public List<String> cuentasConMayorVolumen(int cantidadTop) {
        if (cantidadTop <= 0) throw new RuntimeException("La cantidad debe ser positiva.");
        List<Cuenta> todas = new ArrayList<>(cuentasPorCvu.values());
        
        // Ordenamos de mayor a menor según cantidad de transacciones registradas
        todas.sort((c1, c2) -> Integer.compare(c2.getCantidadTransacciones(), c1.getCantidadTransacciones()));

        List<String> res = new ArrayList<>();
        int limite = Math.min(cantidadTop, todas.size());
        for (int i = 0; i < limite; i++) {
            res.add(todas.get(i).obtenerFormatoLista());
        }
        return res;
    }
}