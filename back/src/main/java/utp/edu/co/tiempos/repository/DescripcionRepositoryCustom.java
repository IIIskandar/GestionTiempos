/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.repository;

import java.util.List;
import utp.edu.co.tiempos.documents.Descripcion;
import utp.edu.co.tiempos.dto.TiempoUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public interface DescripcionRepositoryCustom {
    List<TiempoUsuarioDTO> consultarTiempoUsuario(String cc);
    List<TiempoUsuarioDTO> consultasTiemposAllUsers();
}
