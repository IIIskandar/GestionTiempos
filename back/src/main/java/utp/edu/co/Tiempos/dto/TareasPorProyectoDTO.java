/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.dto;

/**
 *
 * @author C-Lug
 */
public class TareasPorProyectoDTO {
    public String name;
    public String category;
    public String status;
    public Long jobTime;
    public Long expectedTime;

    public TareasPorProyectoDTO() {
    }

    public TareasPorProyectoDTO(String name, String category, String status, Long jobTime, Long expectedTime) {
        this.name = name;
        this.category = category;
        this.status = status;
        this.jobTime = jobTime;
        this.expectedTime = expectedTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getJobTime() {
        return jobTime;
    }

    public void setJobTime(Long jobTime) {
        this.jobTime = jobTime;
    }

    public Long getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Long expectedTime) {
        this.expectedTime = expectedTime;
    }
    
}
