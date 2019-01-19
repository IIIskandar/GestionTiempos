/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Repository;

import java.util.List;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.dto.TiempoUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public interface DescripcionRepositoryCustom {
    List<TiempoUsuarioDTO> consultarTiempoUsuario(String cc);
}
