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
    
    private String madeBy;
    private List<String> descripcionId;
    private Long jobTimeUser;

    public TiempoUsuarioDTO() {
    }

    public TiempoUsuarioDTO(String madeBy, List<String> descripcionId, Long jobTimeUser) {
        this.madeBy = madeBy;
        this.descripcionId = descripcionId;
        this.jobTimeUser = jobTimeUser;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public List<String> getDescripcionId() {
        return descripcionId;
    }

    public void setDescripcionId(List<String> descripcionId) {
        this.descripcionId = descripcionId;
    }

    public Long getJobTimeUser() {
        return jobTimeUser;
    }

    public void setJobTimeUser(Long jobTimeUser) {
        this.jobTimeUser = jobTimeUser;
    }

  
    
}
