
package reservaPadle;


abstract public class Persona {
    
    private String nombre;
    private String apellido;
    private String telefono;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String telefono) {
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }
    
}
