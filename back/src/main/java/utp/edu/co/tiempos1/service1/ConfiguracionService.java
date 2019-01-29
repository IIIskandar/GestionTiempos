/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.service1;

import java.util.List;
import utp.edu.co.tiempos1.documents1.Descripcion;
import utp.edu.co.tiempos1.documents1.Proyecto;
import utp.edu.co.tiempos1.documents1.Usuario;
import utp.edu.co.tiempos1.documents1.Suspension;
import utp.edu.co.tiempos1.documents1.Tarea;
import utp.edu.co.tiempos1.documents1.TipoSuspensiones;
import utp.edu.co.tiempos1.dto.SuspensionDTO;
import utp.edu.co.tiempos1.dto.TipoSuspensionesDTO;

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
