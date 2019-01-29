/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.documents1;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */
@Document(collection = "Registros")
public class Descripcion {
    @Id
    private String id;
    private String jobDetails;
    private Date fechaInicio;
    private Date fechaFin;
    private Long jobTime;
    //se guarda la cedula o el id del usuario quien realizo esta parte de la tarea
    private String madeBy;

    public Descripcion() {
    }

    public Descripcion(String jobDetails, Date fechaInicio, Date fechaFin, Long jobTime, String madeBy) {
        this.jobDetails = jobDetails;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jobTime = jobTime;
        this.madeBy = madeBy;
    }

    
    public String getId() {
        return id;
    }

    public String getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getJobTime() {
        return jobTime;
    }

    public void setJobTime(Long jobTime) {
        this.jobTime = jobTime;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

}