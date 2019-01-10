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
    private Long tiempoWC;
    private Long tiempoSnacks;
    private Long tiempoMeeting;
    private String rol;
    
    protected Usuario(){   
        this.suspensions = new ArrayList<>();
    }

    public Usuario(String name, String cc, String password, List<Suspension> suspensions, Long tiempoWC, Long tiempoSnacks, Long tiempoMeeting, String rol) {
        this.name = name;
        this.cc = cc;
        this.password = password;
        this.suspensions = suspensions;
        this.tiempoWC = tiempoWC;
        this.tiempoSnacks = tiempoSnacks;
        this.tiempoMeeting = tiempoMeeting;
        this.rol = rol;
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

    public Long getTiempoWC() {
        return tiempoWC;
    }

    public void setTiempoWC(Long tiempoWC) {
        this.tiempoWC = tiempoWC;
    }

    public Long getTiempoSnacks() {
        return tiempoSnacks;
    }

    public void setTiempoSnacks(Long tiempoSnacks) {
        this.tiempoSnacks = tiempoSnacks;
    }

    public Long getTiempoMeeting() {
        return tiempoMeeting;
    }

    public void setTiempoMeeting(Long tiempoMeeting) {
        this.tiempoMeeting = tiempoMeeting;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
    
}
