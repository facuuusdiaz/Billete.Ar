package billeteAR;


import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected String dniUsuario; // Asociamos la cuenta al DNI del usuario dueño
    protected String cvu;
    protected String alias;
    protected double saldo;
    protected List<Actividad> historial; 
    protected int cantidadTransacciones;

    public Cuenta(String dniUsuario, String cvu, String alias, double saldo) {
        this.dniUsuario = dniUsuario;
        this.cvu = cvu;
        this.alias = alias;
        this.saldo = saldo;
        this.historial = new ArrayList<>();
        this.cantidadTransacciones = 0;
    }

    // PUNTOS 3 y 10: Retorna el String exacto solicitado para las listas
    public String obtenerFormatoLista() {
        return this.getTipo() + ": " + this.alias + " (" + this.cvu + ")";
    }

    // Método abstracto para obtener el nombre del tipo de cuenta
    public abstract String getTipo();

    // Actualizado: Ahora pide el DNI destino para armar el objeto Transferencia completo
    public void transferirDinero(String dniDestino, String cvuDestino, double monto) {
        boolean aprobado = (this.saldo >= monto);
        
        if (aprobado) {
            this.saldo -= monto;
        }
        
        this.cantidadTransacciones++;
        
        // Se guarda SIEMPRE en el historial, especificando si fue aprobado o rechazado
        Transferencia nuevaTransferencia = new Transferencia(monto, this.dniUsuario, this.cvu, dniDestino, cvuDestino, aprobado);
        this.historial.add(nuevaTransferencia);
    }

    // Actualizado: Ahora recibe el plazo de la inversión
    public void invertir(Inversiones inversion, int plazo, double monto) {
        boolean aprobado = (this.saldo >= monto);
        
        if (aprobado) {
            this.saldo -= monto;
        }
        
        this.cantidadTransacciones++;
        
        // Se guarda en el historial con su respectivo estado
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