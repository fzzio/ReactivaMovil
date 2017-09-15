package reactiva.reactivamovil.classes;

/**
 * Created by Usuario on 5/9/2017.
 */

public class ObservacionTerapia {

    int idObservacionTerapia;
    int idTerapia;
    String obsComentario;
    String obsHoraComentario;
    String obsEstado;
   // int obsFoto;


    public ObservacionTerapia() {
    }

    public ObservacionTerapia(int idObservacionTerapia, int idTerapia, String obsComentario, String obsHoraComentario, String obsEstado) {
        this.idObservacionTerapia = idObservacionTerapia;
        this.idTerapia = idTerapia;
        this.obsComentario = obsComentario;
        this.obsHoraComentario = obsHoraComentario;
        this.obsEstado = obsEstado;
    }

    public int getIdObservacionTerapia() {
        return idObservacionTerapia;
    }

    public void setIdObservacionTerapia(int idObservacionTerapia) {
        this.idObservacionTerapia = idObservacionTerapia;
    }

    public int getIdTerapia() {
        return idTerapia;
    }

    public void setIdTerapia(int idTerapia) {
        this.idTerapia = idTerapia;
    }

    public String getObsComentario() {
        return obsComentario;
    }

    public void setObsComentario(String obsComentario) {
        this.obsComentario = obsComentario;
    }

    public String getObsHoraComentario() {
        return obsHoraComentario;
    }

    public void setObsHoraComentario(String obsHoraComentario) {
        this.obsHoraComentario = obsHoraComentario;
    }

    public String getObsEstado() {
        return obsEstado;
    }

    public void setObsEstado(String obsEstado) {
        this.obsEstado = obsEstado;
    }
}
