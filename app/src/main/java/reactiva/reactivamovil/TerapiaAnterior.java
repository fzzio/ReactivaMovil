package reactiva.reactivamovil;

/**
 * Created by Nancy on 2017-07-16.
 */

public class TerapiaAnterior {

    String fechaTerapiaAnterior;
    String terapistaTerapiaAnterior;
    String comentTerapiaAnterior;
    String galleryFotos;

    public TerapiaAnterior(String fechaTerapiaAnterior, String terapistaTerapiaAnterior, String comentTerapiaAnterior, String galleryFotos) {
        this.fechaTerapiaAnterior = fechaTerapiaAnterior;
        this.terapistaTerapiaAnterior = terapistaTerapiaAnterior;
        this.comentTerapiaAnterior = comentTerapiaAnterior;
        this.galleryFotos = galleryFotos;
    }

    public String getFechaTerapiaAnterior() {
        return fechaTerapiaAnterior;
    }

    public String getTerapistaTerapiaAnterior() {
        return terapistaTerapiaAnterior;
    }

    public String getComentTerapiaAnterior() {
        return comentTerapiaAnterior;
    }

    public String getGalleryFotos() {
        return galleryFotos;
    }

    public void setFechaTerapiaAnterior(String fechaTerapiaAnterior) {
        this.fechaTerapiaAnterior = fechaTerapiaAnterior;
    }

    public void setTerapistaTerapiaAnterior(String terapistaTerapiaAnterior) {
        this.terapistaTerapiaAnterior = terapistaTerapiaAnterior;
    }

    public void setComentTerapiaAnterior(String comentTerapiaAnterior) {
        this.comentTerapiaAnterior = comentTerapiaAnterior;
    }

    public void setGalleryFotos(String galleryFotos) {
        this.galleryFotos = galleryFotos;
    }
}
