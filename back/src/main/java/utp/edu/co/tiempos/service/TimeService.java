/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.service;

import java.util.List;
import java.util.Date;
import utp.edu.co.tiempos.documents.Descripcion;
import utp.edu.co.tiempos.documents.Proyecto;
import utp.edu.co.tiempos.documents.Suspension;
import utp.edu.co.tiempos.documents.Tarea;
import utp.edu.co.tiempos.documents.Usuario;
import utp.edu.co.tiempos.dto.ProyectoTareaUsuarioDTO;
import utp.edu.co.tiempos.dto.SuspensionDTO;
import utp.edu.co.tiempos.dto.TareaCategoriaDTO;
import utp.edu.co.tiempos.dto.TareasPorProyectoDTO;
import utp.edu.co.tiempos.dto.TiempoProyectosDTO;
import utp.edu.co.tiempos.dto.TiempoSuspensionTipoDTO;
import utp.edu.co.tiempos.dto.TiempoTareaUsuarioDTO;
import utp.edu.co.tiempos.dto.TiempoUsuarioDTO;
import utp.edu.co.tiempos.dto.UsuariosPorProyectoDTO;

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
    
    List<Proyecto> contabilizarProyectos();
    
    List<TiempoProyectosDTO> tiempoProyectosFecha(String fechaInicio, String fechaFin);
    
    TiempoUsuarioDTO tiempoUsuarios(String cc);
    
    List<TiempoUsuarioDTO> tiempoAllUsers();
    
    List<TareaCategoriaDTO> tiempoPorCategoria(String fechaInicio, String fechaFin);
    
    List<TiempoSuspensionTipoDTO> tiempoPorTipoSus(String fechaInicio, String fechaFin);
    
//    List<TiempoTareaUsuarioDTO> tiempoTareaUsuario(String idTarea, String cc);
    
    Long TiempoSuspensionUsuarioTotal(String cc);
    
    TiempoTareaUsuarioDTO tiempoUsuarioPorTarea(String idTarea, String cc, String fechaInicio, String fechaFin);
        
    List<ProyectoTareaUsuarioDTO> tareasRealizadosPorUsuarioFecha(String cc, String fechaInicio, String fechaFin);
    
    List<ProyectoTareaUsuarioDTO> tareasRealizadosPorUsuario(String cc);
    
    List<TiempoProyectosDTO> proyectosRealizadosPorUsuario(String cc, String fechaInicio, String fechaFin);
    
    List<UsuariosPorProyectoDTO> usuariosPorProyecto(String idProyecto, String fechaInicio, String fechaFin);
    
    List<TareasPorProyectoDTO> tareasPorProyecto(String idProyecto, String fechaInicio, String fechaFin);
}
