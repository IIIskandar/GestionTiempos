/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.documents;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */
@Document
public class Suspension {
    
    private String tipoSuspension;
    private Long tiempoSuspension;
    private Date fechaInicio;
    private Date fechaFin;
    private String detalleSuspension;
    
    
    
    protected Suspension(){
        
    }

    public Suspension(String tipoSuspension,Long tiempoSuspension, Date fechaInicio, Date fechaFin, String detalleSuspension) {
        this.tipoSuspension = tipoSuspension;
        this.tiempoSuspension = tiempoSuspension;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detalleSuspension = detalleSuspension;
    }

    public String getTipoSuspension() {
        return tipoSuspension;
    }

    public void setTipoSuspension(String tipoSuspension) {
        this.tipoSuspension = tipoSuspension;
    }
    
    public Long getTiempoSuspension() {
        return tiempoSuspension;
    }

    public void setTiempoSuspension(Long tiempoSuspension) {
        this.tiempoSuspension = tiempoSuspension;
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

    public String getDetalleSuspension() {
        return detalleSuspension;
    }

    public void setDetalleSuspension(String detalleSuspension) {
        this.detalleSuspension = detalleSuspension;
    }
  
}
