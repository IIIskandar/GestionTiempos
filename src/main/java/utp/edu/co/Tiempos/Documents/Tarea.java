/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Documents;

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
    private String nombre;
    private String tipo;
    private String estado;
    private String tiempoTrabajo;
    @DBRef(lazy = true)
    private List<Descripcion> descripciones;
    
    protected Tarea()
    {this.descripciones = new ArrayList<>();}

    public Tarea(String nombre, String tipo, String estado, String tiempoTrabajo, List<Descripcion> descripciones) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.tiempoTrabajo = tiempoTrabajo;
        this.descripciones = descripciones;
    }

    public String getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    public void setTiempoTrabajo(String tiempoTrabajo) {
        this.tiempoTrabajo = tiempoTrabajo;
    }

    public List<Descripcion> getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(List<Descripcion> descripciones) {
        this.descripciones = descripciones;
    }
    
    
}
