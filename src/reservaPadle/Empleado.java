
package reservaPadle;

public class Empleado extends Persona{
    private int legajo;
    private String direccion;
    private String mail;
    private String usuario;
    private String contrasenia;


    public Empleado() {
    }

   

    public Empleado(int legajo, String direccion, String mail,String usuario,String contrasenia, String nombre, String apellido, String telefono) {
        super(nombre, apellido, telefono);
        this.legajo = legajo;
        this.direccion = direccion;
        this.mail = mail;
        this.usuario=usuario;
        this.contrasenia=contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    

    

    public int getLegajo() {
        return legajo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMail() {
        return mail;
    }
    
    
    
}
