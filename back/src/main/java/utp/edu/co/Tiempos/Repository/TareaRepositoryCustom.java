/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Repository;

import java.util.List;
import utp.edu.co.Tiempos.dto.TareaCategoriaDTO;
import utp.edu.co.Tiempos.dto.TiempoTareaUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public interface TareaRepositoryCustom {
    List<TareaCategoriaDTO> consultarTiempoCategoria();
    List<TiempoTareaUsuarioDTO> consultarTiempoTareaUsuario(String idTarea, String cc);
}
