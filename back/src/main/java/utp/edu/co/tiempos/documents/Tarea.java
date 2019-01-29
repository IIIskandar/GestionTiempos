/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.documents;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */

@Document(collection = "Tareas")
public class Tarea {
    @Id
    private String id;
    private String name;
    private String category;
    private String status;
    private Long jobTime;
    private Long expectedTime;
    
    @DBRef(lazy = true)
    private List<Descripcion> descripciones;
    
    protected Tarea()
    {this.descripciones = new ArrayList<>();}

    public Tarea(String name, String category, String status, Long jobTime, Long expectedTime, List<Descripcion> descripciones) {
        this.name = name;
        this.category = category;
        this.status = status;
        this.jobTime = jobTime;
        this.expectedTime = expectedTime;
        this.descripciones = descripciones;
    }

    
    public String getId() {
        return id;
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

    public List<Descripcion> getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(List<Descripcion> descripciones) {
        this.descripciones = descripciones;
    }
    
    
}
