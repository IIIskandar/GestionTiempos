/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Repository;

import java.util.Date;
import java.util.List;
import utp.edu.co.Tiempos.dto.TiempoSuspensionTipoDTO;

/**
 *
 * @author C-Lug
 */
public interface UsuarioRepositoryCustom {
        List<TiempoSuspensionTipoDTO> tiempoPorTipoSuspension(Date fechaInicio, Date fechaFin);

}
