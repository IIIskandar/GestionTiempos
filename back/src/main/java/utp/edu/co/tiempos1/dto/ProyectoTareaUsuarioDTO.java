/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.dto;

/**
 *
 * @author C-Lug
 */
public class ProyectoTareaUsuarioDTO {
    private String nameProyecto;
    private String nameTarea;
    private String status;
    private Long totalRegistros;
    private Long jobTimeUser;

    public ProyectoTareaUsuarioDTO() {
    }

    public ProyectoTareaUsuarioDTO(String nameProyecto, String nameTarea, String status, Long totalRegistros, Long jobTimeUser) {
        this.nameProyecto = nameProyecto;
        this.nameTarea = nameTarea;
        this.status = status;
        this.totalRegistros = totalRegistros;
        this.jobTimeUser = jobTimeUser;
    }

    public String getNameProyecto() {
        return nameProyecto;
    }

    public void setNameProyecto(String nameProyecto) {
        this.nameProyecto = nameProyecto;
    }

    public String getNameTarea() {
        return nameTarea;
    }

    public void setNameTarea(String nameTarea) {
        this.nameTarea = nameTarea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Long totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public Long getJobTimeUser() {
        return jobTimeUser;
    }

    public void setJobTimeUser(Long jobTimeUser) {
        this.jobTimeUser = jobTimeUser;
    }
    
}
