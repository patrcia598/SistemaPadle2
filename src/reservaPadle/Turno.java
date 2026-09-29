
package reservaPadle;

public class Turno {
    private int idTurno;
    private int numeroTurno;
    
    private String fecha;
     private String hora;
    private String estado;
    Cancha cancha;
    private int duracionHoras ;

    public Turno() {
    }

    public Turno(int idTurno, int numeroTurno, String fecha, String hora, String estado, Cancha cancha, int duracionHoras) {
        this.idTurno = idTurno;
        this.numeroTurno = numeroTurno;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.cancha = cancha;
        this.duracionHoras = duracionHoras;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getNumeroTurno() {
        return numeroTurno;
    }

    public void setNumeroTurno(int numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public void ocuparTurno() {
        this.estado = "Ocupado";
    }

    public void liberarTurno() {
        this.estado = "Disponible";
    }
    

}