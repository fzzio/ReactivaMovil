package reactiva.reactivamovil.classes;

/**
 * Created by Nancy on 2017-08-21.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Therapy {

    @SerializedName("id_therapy")
    @Expose
    private String idTherapy;
    @SerializedName("id_patient")
    @Expose
    private String idPatient;
    @SerializedName("id_consulta")
    @Expose
    private String idConsulta;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("status")
    @Expose
    private String status;

    public String getIdTherapy() {
        return idTherapy;
    }

    public void setIdTherapy(String idTherapy) {
        this.idTherapy = idTherapy;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
