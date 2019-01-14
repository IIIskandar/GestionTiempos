/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Documents;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */
@Document
public class Suspension {
    
    private Integer wcs;
    private Integer snacks;
    private Integer meetings;
    private Long tiempoSuspension;
    private Date fechaInicio;
    private Date fechaFin;
    private String detalleSuspension;
    
    
    
    protected Suspension(){
        
    }

    public Suspension(Integer wcs, Integer snacks, Integer meetings,Long tiempoSuspension, Date fechaInicio, Date fechaFin, String detalleSuspension) {
        this.wcs = wcs;
        this.snacks = snacks;
        this.meetings = meetings;
        this.tiempoSuspension = tiempoSuspension;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detalleSuspension = detalleSuspension;
    }

    public Integer getWcs() {
        return wcs;
    }

    public void setWcs(Integer wcs) {
        this.wcs = wcs;
    }

    public Integer getSnacks() {
        return snacks;
    }

    public void setSnacks(Integer snacks) {
        this.snacks = snacks;
    }

    public Integer getMeetings() {
        return meetings;
    }

    public void setMeetings(Integer meetings) {
        this.meetings = meetings;
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
