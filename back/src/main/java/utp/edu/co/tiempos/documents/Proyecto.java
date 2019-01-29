/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.documents;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */
@Document(collection = "Proyectos")
public class Proyecto {
    @Id
    private String id;
    @NotNull
    @Indexed(unique=true)
    private String name;
    private String creator;
    private Long jobTime;
    @DBRef(lazy = true)
    private List<Tarea> tareas;
    @DBRef
    private List<Usuario> UsersId;
    
    protected Proyecto(){
        this.tareas = new ArrayList<>();
        this.UsersId = new ArrayList<>();}

    public Proyecto(String name, String creator,Long jobTime, List<Tarea> tareas, List<Usuario> UsersId) {
        this.name = name;
        this.creator = creator;
        this.jobTime = jobTime;
        this.tareas = tareas;
        this.UsersId = UsersId;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getJobTime() {
        return jobTime;
    }

    public void setJobTime(Long jobTime) {
        this.jobTime = jobTime;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<Usuario> getUsersId() {
        return UsersId;
    }

    public void setUsersId(List<Usuario> UsersId) {
        this.UsersId = UsersId;
    }
    
}
