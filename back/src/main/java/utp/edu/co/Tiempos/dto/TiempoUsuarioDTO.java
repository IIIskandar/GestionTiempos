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
public class TiempoUsuarioDTO {
    
    private List<String> descripcionId;
    private Long jobTimeUser;

    public TiempoUsuarioDTO() {
    }

    public TiempoUsuarioDTO(List<String> descripcionId, Long jobTimeUser) {
        this.descripcionId = descripcionId;
        this.jobTimeUser = jobTimeUser;
    }

    public List<String> getDescripcionesId() {
        return descripcionId;
    }

    public void setDescripcionesId(List<String> descripcionesId) {
        this.descripcionId = descripcionesId;
    }

    public Long getJobTimeUser() {
        return jobTimeUser;
    }

    public void setJobTimeUser(Long jobTimeUser) {
        this.jobTimeUser = jobTimeUser;
    }
    
}
