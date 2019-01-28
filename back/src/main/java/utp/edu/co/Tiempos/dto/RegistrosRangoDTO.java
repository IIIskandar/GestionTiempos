/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.dto;

import java.util.List;

/**
 *
 * @author C-Lug
 */
public class RegistrosRangoDTO {
    private String Tarea;
    private String category;
    private List<String> descripcionesId;
    private Long jobTime;

    public RegistrosRangoDTO() {
    }

    public RegistrosRangoDTO(String Tarea, List<String> descripcionesId, Long jobTime,String category) {
        this.Tarea = Tarea;
        this.descripcionesId = descripcionesId;
        this.jobTime = jobTime;
        this.category = category;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String Tarea) {
        this.Tarea = Tarea;
    }

    public List<String> getDescripcionesId() {
        return descripcionesId;
    }

    public void setDescripcionesId(List<String> descripcionesId) {
        this.descripcionesId = descripcionesId;
    }


    public Long getJobTime() {
        return jobTime;
    }

    public void setJobTime(Long jobTime) {
        this.jobTime = jobTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
