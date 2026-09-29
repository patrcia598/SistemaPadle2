
package reservaPadle;


public class Cancha {
    private int idCancha;
    private int numeroCancha;
    private boolean enMantenimiento;
   private double precioHora;

    public Cancha() {
    }

    public Cancha(int idCancha, int numeroCancha, boolean enMantenimiento, double precioHora) {
        this.idCancha = idCancha;
        this.numeroCancha = numeroCancha;
        this.enMantenimiento = enMantenimiento;
        this.precioHora = precioHora;
    }

    public double getPrecioHora() {
        return precioHora;
    }

   

    

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public int getNumeroCancha() {
        return numeroCancha;
    }

    public void setNumeroCancha(int numeroCancha) {
        this.numeroCancha = numeroCancha;
    }

    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }

}