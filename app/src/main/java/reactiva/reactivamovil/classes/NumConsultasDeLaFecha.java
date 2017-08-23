package reactiva.reactivamovil.classes;

/**
 * Created by Nancy on 2017-08-21.
 */

public class NumConsultasDeLaFecha {

    private String dia;
    private String numDeCitas;

    public NumConsultasDeLaFecha(String dia, String numDeCitas) {
        this.dia = dia;
        this.numDeCitas = numDeCitas;
    }

    public NumConsultasDeLaFecha() {
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getNumDeCitas() {
        return numDeCitas;
    }

    public void setNumDeCitas(String numDeCitas) {
        this.numDeCitas = numDeCitas;
    }
}
