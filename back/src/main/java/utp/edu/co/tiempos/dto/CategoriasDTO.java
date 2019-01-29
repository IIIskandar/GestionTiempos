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
public class CategoriasDTO {
    private String name;

    public CategoriasDTO(String name) {
        this.name = name;
    }

    public CategoriasDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
