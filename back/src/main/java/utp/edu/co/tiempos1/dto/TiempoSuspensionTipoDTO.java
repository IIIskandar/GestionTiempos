/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.dto;

/**
 *
 * @author C-Lug
 */
public class TiempoSuspensionTipoDTO {
    private String tipo;
    private Long jobTimeSuspension;

    public TiempoSuspensionTipoDTO() {
    }

    public TiempoSuspensionTipoDTO(String tipo, Long jobTimeSuspension) {
        this.tipo = tipo;
        this.jobTimeSuspension = jobTimeSuspension;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getJobTimeSuspension() {
        return jobTimeSuspension;
    }

    public void setJobTimeSuspension(Long jobTimeSuspension) {
        this.jobTimeSuspension = jobTimeSuspension;
    }
    
    
}
