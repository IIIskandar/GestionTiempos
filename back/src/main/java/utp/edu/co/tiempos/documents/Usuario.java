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
@Document(collection = "Usuarios")
public class Usuario {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    @Indexed(unique=true)
    private String cc;
    @NotNull
    private String password;
    private List<Suspension> suspensions;
    private List<TiempoSuspensiones> tiempoSuspensiones;
    private String rol;
    private String status;
    
    protected Usuario(){   
        this.suspensions = new ArrayList<>();
        this.tiempoSuspensiones = new ArrayList<>();
    }

    public Usuario(String name, String cc, String password, List<Suspension> suspensions, List<TiempoSuspensiones> tiempoSuspensiones, String rol, String status) {
        this.name = name;
        this.cc = cc;
        this.password = password;
        this.suspensions = suspensions;
        this.tiempoSuspensiones = tiempoSuspensiones;
        this.rol = rol;
        this.status = status;
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


    public List<Suspension> getSuspensions() {
        return suspensions;
    }

    public List<TiempoSuspensiones> getTiempoSuspensiones() {
        return tiempoSuspensiones;
    }

    public void setTiempoSuspensiones(List<TiempoSuspensiones> tiempoSuspensiones) {
        this.tiempoSuspensiones = tiempoSuspensiones;
    }
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
