
package reservaPadle;

public class Cliente extends Persona {
    
    // ⚠️ CORREGIDO: Las variables de clase se declaran AQUÍ (afuera de los métodos)
    private static int contadorClientes = 0; 
    private int idCliente;

    // Constructor vacío (lo dejamos por si lo usas en otro lado)
    public Cliente() {
        super(); // Llama al constructor vacío de Persona si existe
    }

    // Constructor Principal Autoincrementable

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, String nombre, String apellido, String telefono) {
        super(nombre, apellido, telefono);
        this.idCliente = idCliente;
    }

    public static int getContadorClientes() {
        return contadorClientes;
    }

    public static void setContadorClientes(int contadorClientes) {
        Cliente.contadorClientes = contadorClientes;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
