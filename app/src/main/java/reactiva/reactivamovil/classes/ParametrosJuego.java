package reactiva.reactivamovil.classes;

/**
 * Created by Erasmo Zurita on 2017-08-02.
 */

public class ParametrosJuego {

    String detalleParametro;
    String parametroX;
    String parametroY;
    String parametroZ;


    public ParametrosJuego(String detalleParametro, String parametroX, String parametroY, String parametroZ) {
        this.detalleParametro = detalleParametro;
        this.parametroX = parametroX;
        this.parametroY = parametroY;
        this.parametroZ = parametroZ;
    }

    public String getDetalleParametro() {
        return detalleParametro;
    }

    public void setDetalleParametro(String detalleParametro) {
        this.detalleParametro = detalleParametro;
    }

    public String getParametroX() {
        return parametroX;
    }

    public void setParametroX(String parametroX) {
        this.parametroX = parametroX;
    }

    public String getParametroY() {
        return parametroY;
    }

    public void setParametroY(String parametroY) {
        this.parametroY = parametroY;
    }

    public String getParametroZ() {
        return parametroZ;
    }

    public void setParametroZ(String parametroZ) {
        this.parametroZ = parametroZ;
    }
}
