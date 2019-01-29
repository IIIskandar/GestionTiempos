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
public class UsuariosPorProyectoDTO {
    private String nameProyecto;
    private String cc;
    private Long jobTimeUser;

    public UsuariosPorProyectoDTO() {
    }

    public UsuariosPorProyectoDTO(String nameProyecto, String cc, Long jobTimeUser) {
        this.nameProyecto = nameProyecto;
        this.cc = cc;
        this.jobTimeUser = jobTimeUser;
    }

    public String getNameProyecto() {
        return nameProyecto;
    }

    public void setNameProyecto(String nameProyecto) {
        this.nameProyecto = nameProyecto;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Long getJobTimeUser() {
        return jobTimeUser;
    }

    public void setJobTimeUser(Long jobTimeUser) {
        this.jobTimeUser = jobTimeUser;
    }
    
}
