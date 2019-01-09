/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service;

import java.util.List;
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
    
    Usuario eliminarUsuario(String id);
    
    Usuario guardarSuspension(String id, Suspension suspension);
}
