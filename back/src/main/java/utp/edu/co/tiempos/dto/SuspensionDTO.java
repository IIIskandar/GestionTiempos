/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.dto;



/**
 *
 * @author C-Lug
 */
public class SuspensionDTO {
    private String tipoSuspension;
    private Long tiempoSuspension;
    private String detalleSuspension;

    public SuspensionDTO() {
    }

    public SuspensionDTO(String tipoSuspension, Long tiempoSuspension, String detalleSuspension) {
        this.tipoSuspension = tipoSuspension;
        this.tiempoSuspension = tiempoSuspension;
        this.detalleSuspension = detalleSuspension;
    }

    public String getTipoSuspension() {
        return tipoSuspension;
    }

    public void setTipoSuspension(String tipoSuspension) {
        this.tipoSuspension = tipoSuspension;
    }

    public Long getTiempoSuspension() {
        return tiempoSuspension;
    }

    public void setTiempoSuspension(Long tiempoSuspension) {
        this.tiempoSuspension = tiempoSuspension;
    }

    public String getDetalleSuspension() {
        return detalleSuspension;
    }

    public void setDetalleSuspension(String detalleSuspension) {
        this.detalleSuspension = detalleSuspension;
    }
    
    
}
