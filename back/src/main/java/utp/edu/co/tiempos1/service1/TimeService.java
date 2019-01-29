/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.service1;

import java.util.List;
import java.util.Date;
import utp.edu.co.tiempos1.documents1.Descripcion;
import utp.edu.co.tiempos1.documents1.Proyecto;
import utp.edu.co.tiempos1.documents1.Suspension;
import utp.edu.co.tiempos1.documents1.Tarea;
import utp.edu.co.tiempos1.documents1.Usuario;
import utp.edu.co.tiempos1.dto.ProyectoTareaUsuarioDTO;
import utp.edu.co.tiempos1.dto.SuspensionDTO;
import utp.edu.co.tiempos1.dto.TareaCategoriaDTO;
import utp.edu.co.tiempos1.dto.TareasPorProyectoDTO;
import utp.edu.co.tiempos1.dto.TiempoProyectosDTO;
import utp.edu.co.tiempos1.dto.TiempoSuspensionTipoDTO;
import utp.edu.co.tiempos1.dto.TiempoTareaUsuarioDTO;
import utp.edu.co.tiempos1.dto.TiempoUsuarioDTO;
import utp.edu.co.tiempos1.dto.UsuariosPorProyectoDTO;

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
