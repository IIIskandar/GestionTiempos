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
    private Date fechaInicio;
    private Date fechaFin;
    private String detalleSuspension;
    
    
    
    protected Suspension(){
        
    }

    public Suspension(Integer wcs, Integer snacks, Integer meetings, Date fechaInicio, Date fechaFin, String detalleSuspension) {
        this.wcs = wcs;
        this.snacks = snacks;
        this.meetings = meetings;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detalleSuspension = detalleSuspension;
    }


    public Integer getWCs() {
        return wcs;
    }

    public void setWCs(Integer wcs) {
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
