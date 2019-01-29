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
public class TareaCategoriaDTO {
    
    private String category;
    private Long jobTimeCategory;

    public TareaCategoriaDTO() {
    }

    public TareaCategoriaDTO(String category, Long jobTimeCategory) {
        this.category = category;
        this.jobTimeCategory = jobTimeCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getJobTimeCategory() {
        return jobTimeCategory;
    }

    public void setJobTimeCategory(Long jobTimeCategory) {
        this.jobTimeCategory = jobTimeCategory;
    }
    
    
}
