/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.dto;

/**
 *
 * @author C-Lug
 */
public class TipoSuspensionesDTO {
    
    private String name;

    public TipoSuspensionesDTO(String name) {
        this.name = name;
    }

    public TipoSuspensionesDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
