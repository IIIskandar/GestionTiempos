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
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author C-Lug
 */
@Document(collection = "Usuarios")
public class Usuario {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String cc;
    @NotNull
    private String password;
    
    @DBRef(lazy = true)
    private List<String> projectsId;
    @DBRef(lazy = true)
    private List<Suspension> suspensions;
    
    protected Usuario(){   
        this.projectsId = new ArrayList<>();
        this.suspensions = new ArrayList<>();
    }

    public Usuario(String name, String cc, String password, List<String> projectsId, List<Suspension> suspensions) {
        this.name = name;
        this.cc = cc;
        this.password = password;
        this.projectsId = projectsId;
        this.suspensions = suspensions;
    }

    public String getId() {
        return id;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProjectsId(List<String> projectsId) {
        this.projectsId = projectsId;
    }

    public void setSuspensions(List<Suspension> suspensions) {
        this.suspensions = suspensions;
    }
    

    public String getName() {
        return name;
    }

    public String getCc() {
        return cc;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getProjectsId() {
        return projectsId;
    }

    public List<Suspension> getSuspensions() {
        return suspensions;
    }
    
    
    
}
