
package reservaPadle;

public class Reserva {
    private int idReserva;
    private String fechaReserva;
    private String estado;

    private Cliente cliente;
    private Empleado empleado;
    private Cancha cancha;
    private Turno turno;

    public Reserva() {
    }

    public Reserva(int idReserva, String fechaReserva, String estado, Cliente cliente, Empleado empleado, Cancha cancha, Turno turno) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.cliente = cliente;
        this.empleado = empleado;
        this.cancha = cancha;
        this.turno = turno;
        this.turno.ocuparTurno(); 
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno nuevoTurno) {
    // 1. Antes de soltar el turno actual, lo liberamos para que otros puedan usarlo
    if (this.turno != null) {
        this.turno.liberarTurno();
    }
    
    // 2. Asignamos el nuevo turno a la reserva
    this.turno = nuevoTurno;
    
    // 3. Ocupamos el nuevo turno para que figure reservado en la grilla
    this.turno.ocuparTurno();
}

    public double calcularTotal() {
        return this.cancha.getPrecioHora() * this.turno.getDuracionHoras();
    }

   public void cancelarReserva() {
    this.estado = "Cancelada"; // Cambia el estado de la reserva
    if (this.turno != null) {
        this.turno.liberarTurno(); // ⚠️ RN06: Vuelve el turno a "Disponible" automáticamente
    }
}
    public void emitirComprobante() {
        System.out.println("Fecha: "+fechaReserva);
        double total = calcularTotal();

        System.out.println("==================================================");
        System.out.println("              RESERVA DE PÁDEL #" + this.idReserva);
        System.out.println("==================================================");
        System.out.println("Fecha de Emisión: " + this.fechaReserva);
        System.out.println("Atendido por: " + this.empleado.getNombre() );
        // ⚠️ AQUÍ SE AGREGA EL NOMBRE Y APELLID DEL CLIENTE QUE REALIZÓ LA RESERVA
        System.out.println("DATOS DEL CLIENTE:");
        System.out.println("  - Nombre:       " + this.cliente.getNombre() + " " + this.cliente.getApellido());
        System.out.println("  - Teléfono:     " + this.cliente.getTelefono()); // Ajusta al nombre exacto de tu getter de teléfono
        System.out.println("\nDETALLE DEL TURNO:");
        System.out.println("  - Cancha: " + this.cancha.getNumeroCancha() );
        System.out.println("  - Día y Hora: " + this.turno.getFecha() + " a las " + this.turno.getHora() + " hs");

        System.out.println("  - Duración: " + this.turno.getDuracionHoras() + " horas");
        System.out.println("--------------------------------------------------");
        System.out.printf("TOTAL A PAGAR: $%.2f\n", total);
        System.out.println("==================================================");
    }
}

