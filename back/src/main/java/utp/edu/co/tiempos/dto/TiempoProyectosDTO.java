/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.dto;

import java.util.List;

/**
 *
 * @author C-Lug
 */
public class TiempoProyectosDTO {
    private String name;
    private Long jobTimeUser;

    public TiempoProyectosDTO() {
    }

    public TiempoProyectosDTO(String name, Long jobTimeUser) {
        this.name = name;
        this.jobTimeUser = jobTimeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getJobTimeUser() {
        return jobTimeUser;
    }

    public void setJobTimeUser(Long jobTimeUser) {
        this.jobTimeUser = jobTimeUser;
    }
    
    

}
