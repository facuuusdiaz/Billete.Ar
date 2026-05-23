package billeteAR;



import java.util.HashSet;
import java.util.Set;

public class Empresa {
    private String cuit;
    private String nombreFantasia;
    private String telefono;
    private String email;
    private String nombreContacto;
    private Set<String> personasAutorizadas; // Guarda los DNI de los autorizados

    public Empresa(String cuit, String nombreFantasia, String telefono, String email, String nombreContacto) {
        this.cuit = cuit;
        this.nombreFantasia = nombreFantasia;
        this.telefono = telefono;
        this.email = email;
        this.nombreContacto = nombreContacto;
        this.personasAutorizadas = new HashSet<>();
    }

    public void autorizarPersona(String dni) {
        if (personasAutorizadas.contains(dni)) {
            throw new RuntimeException("La persona ya está autorizada.");
        }
        personasAutorizadas.add(dni);
    }

    public boolean estaAutorizado(String dni) {
        return personasAutorizadas.contains(dni);
    }

    public String getCuit() { return cuit; }
}