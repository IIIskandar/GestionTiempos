/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.documents;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */
@Document
public class TiempoSuspensiones {
    
    private String nombre;
    private Long acumulado;
    

    public TiempoSuspensiones() {
    }

    public TiempoSuspensiones(String nombre, Long acumulado) {
        this.nombre = nombre;
        this.acumulado = acumulado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(Long acumulado) {
        this.acumulado = acumulado;
    }
    
}
