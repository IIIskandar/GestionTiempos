/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.dto;

import java.util.List;

/**
 *
 * @author C-Lug
 */
public class TiempoTareaUsuarioDTO {
    private String name;
    private List<String> descripcionesId;
    private Long jobTimeUser;

    public TiempoTareaUsuarioDTO() {
    }

    public TiempoTareaUsuarioDTO(String name, List<String> descripcionesId, Long jobTimeUser) {
        this.name = name;
        this.descripcionesId = descripcionesId;
        this.jobTimeUser = jobTimeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescripcionesId() {
        return descripcionesId;
    }

    public void setDescripcionesId(List<String> descripcionesId) {
        this.descripcionesId = descripcionesId;
    }

    public Long getJobTimeUser() {
        return jobTimeUser;
    }

    public void setJobTimeUser(Long jobTimeUser) {
        this.jobTimeUser = jobTimeUser;
    }
    
    
}
