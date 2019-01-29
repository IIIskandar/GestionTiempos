/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.repository1;

import java.util.Date;
import java.util.List;
import utp.edu.co.tiempos1.dto.TareaCategoriaDTO;
import utp.edu.co.tiempos1.dto.TiempoTareaUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public interface TareaRepositoryCustom {
    List<TareaCategoriaDTO> consultarTiempoCategoria();
//    List<TiempoTareaUsuarioDTO> consultarTiempoTareaUsuario(String idTarea, String cc);
}
