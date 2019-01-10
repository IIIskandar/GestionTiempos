/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Documents;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */
@Document(collection = "Descripciones")
public class Descripcion {
    @Id
    private String id;
    @NotNull
    private String detalleTrabajado;
    @CreatedDate
    private Date fechaInicio;
    private Date fechaFin;
    private Long tiempoTrabajado;
    //se guarda la cedula o el ida del usuario quien realizo este tramo de la tarea
    private String realizadoPor;

    public Descripcion(String detalleTrabajado, Date fechaInicio, Date fechaFin, Long tiempoTrabajado) {
        this.detalleTrabajado = detalleTrabajado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tiempoTrabajado = tiempoTrabajado;
    }

    public String getId() {
        return id;
    }

    public String getDetalleTrabajado() {
        return detalleTrabajado;
    }

    public void setDetalleTrabajado(String detalleTrabajado) {
        this.detalleTrabajado = detalleTrabajado;
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

    public Long getTiempoTrabajado() {
        return tiempoTrabajado;
    }

    public void setTiempoTrabajado(Long tiempoTrabajado) {
        this.tiempoTrabajado = tiempoTrabajado;
    }
    
    
}
