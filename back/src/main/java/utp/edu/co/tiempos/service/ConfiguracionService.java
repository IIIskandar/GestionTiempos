/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.service;

import java.util.List;
import utp.edu.co.tiempos.documents.Descripcion;
import utp.edu.co.tiempos.documents.Proyecto;
import utp.edu.co.tiempos.documents.Usuario;
import utp.edu.co.tiempos.documents.Suspension;
import utp.edu.co.tiempos.documents.Tarea;
import utp.edu.co.tiempos.documents.TipoSuspensiones;
import utp.edu.co.tiempos.dto.SuspensionDTO;
import utp.edu.co.tiempos.dto.TipoSuspensionesDTO;

/**
 *
 * @author C-Lug
 */
public interface ConfiguracionService {
    List<Usuario> listaUsuarios();
    
    Usuario consultarUsuario(String id);
    
    Usuario consultarUsuariobyCC(String cc);
    
    Usuario guardarUsuario(Usuario usuario);
    
    Usuario actualizarUsuario(Usuario usuario);
    
    String eliminarUsuario(String id);
    
    List<Proyecto> listaProyectos();
    
    Proyecto consultarProyecto(String id);
    
    Proyecto guardarProyecto(Proyecto proyecto);
    
    Proyecto eliminarProyecto(String id);
    
    Proyecto asignarUsuarioaProyecto(String idProyecto, String idUsuario);
    
    List<Tarea> listaTareas(String id);
    
    Tarea consultarTarea(String id);
    
    Tarea guardarTarea(String id, Tarea tarea);
    
    Tarea eliminarTarea(String id);
    
    List<Descripcion> listaRegistros(String id);
    
    Descripcion consultarRegistro(String id);
    
    Descripcion eliminarRegistro(String id);
    
    List<Proyecto> consultarProyectosUsuario (String ccUsuario);
    
    TipoSuspensionesDTO crearTipoSuspension(TipoSuspensionesDTO tiposuspensiondto);
    
    List<TipoSuspensiones> listaTipoSuspensiones();
    
    TipoSuspensionesDTO consultarSuspension(String nombre);
    
    String eliminarTipoSuspension(String nombre);
}
