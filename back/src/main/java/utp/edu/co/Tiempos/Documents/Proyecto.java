/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Documents;

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
    @DBRef(lazy = true)
    private List<Tarea> tareas;
    @DBRef(lazy = true)
    private List<String> UsersId;
    
    protected Proyecto(){
        this.tareas = new ArrayList<>();
        this.UsersId = new ArrayList<>();}

    public Proyecto(String name, String creator, List<Tarea> tareas, List<String> UsersId) {
        this.name = name;
        this.creator = creator;
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

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<String> getUsersId() {
        return UsersId;
    }

    public void setUsersId(List<String> UsersId) {
        this.UsersId = UsersId;
    }
    
}
