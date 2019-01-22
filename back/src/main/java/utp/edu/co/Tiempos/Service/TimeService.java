/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service;

import java.util.List;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.dto.SuspensionDTO;
import utp.edu.co.Tiempos.dto.TareaCategoriaDTO;
import utp.edu.co.Tiempos.dto.TiempoSuspensionTipoDTO;
import utp.edu.co.Tiempos.dto.TiempoTareaUsuarioDTO;
import utp.edu.co.Tiempos.dto.TiempoUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public interface TimeService {
    
    Usuario iniciarSuspension(String id, SuspensionDTO suspensiondto);
    
    Usuario finalizarSuspension(String id);
    
    Descripcion iniciarRegistro(String id,String status, Descripcion descripcion);
    
    Descripcion finalizarRegistro(String id, Descripcion descripcion, String status);
    
    Long contabilizarProyecto(String id);
    
    TiempoUsuarioDTO tiempoUsuarios(String cc);
    
    List<TareaCategoriaDTO> tiempoPorCategoria();
    
    List<TiempoSuspensionTipoDTO> tiempoPorTipoSus();
    
    List<TiempoTareaUsuarioDTO> tiempoTareaUsuario(String idTarea, String cc);
    
    Long TiempoSuspensionUsuarioTotal(String cc);
}
