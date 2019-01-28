/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service;

import java.util.List;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Documents.TipoSuspensiones;
import utp.edu.co.Tiempos.dto.SuspensionDTO;
import utp.edu.co.Tiempos.dto.TipoSuspensionesDTO;

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
    
    TipoSuspensionesDTO eliminarTipoSuspension(String nombre);
}
