/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service;

import java.util.List;
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Documents.Suspension;

/**
 *
 * @author C-Lug
 */
public interface ConfiguracionService {
    List<Usuario> listaUsuarios();
    
    Usuario consultarUsuario(String id);
    
    Usuario guardarUsuario(Usuario usuario);
    
    Usuario actualizarUsuario(Usuario usuario);
    
    Usuario eliminarUsuario(String id);
<<<<<<< HEAD:src/main/java/utp/edu/co/Tiempos/Service/ConfiguracionService.java
    
    List<Proyecto> listaProyectos();
    
    Proyecto consultarProyecto(String id);
    
    Proyecto guardarProyecto(Proyecto proyecto);
    
    Proyecto eliminarProyecto(String id);
||||||| merged common ancestors
=======
    
    Usuario guardarSuspension(String id, Suspension suspension);
>>>>>>> f3a2861718249a171c0372d89fe026d30dc263b4:back/src/main/java/utp/edu/co/Tiempos/Service/ConfiguracionService.java
}
