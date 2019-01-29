/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.repository1;

import java.util.List;
import utp.edu.co.tiempos1.documents1.Descripcion;
import utp.edu.co.tiempos1.dto.TiempoUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public interface DescripcionRepositoryCustom {
    List<TiempoUsuarioDTO> consultarTiempoUsuario(String cc);
    List<TiempoUsuarioDTO> consultasTiemposAllUsers();
}
