/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Repository.UsuarioRepository;
import utp.edu.co.Tiempos.Service.ConfiguracionService;

/**
 *
 * @author C-Lug
 */
@Service
public class DefaultServiceConfiguracion implements ConfiguracionService{

    private UsuarioRepository usuarioRepository;

    public DefaultServiceConfiguracion(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    //Lista los usuarios guardados
    @Override
    public List<Usuario> listaUsuarios() {
        List<Usuario> respuesta = new ArrayList<>();
        respuesta = usuarioRepository.findAll();
        if(!respuesta.isEmpty()){
            return respuesta;
        }
        return null;      
    }

    //consulta un usuario por su ID
    @Override
    public Usuario consultarUsuario(String id) {
        Optional<Usuario> usuariOptional = usuarioRepository.findById(id);
        if(usuariOptional.isPresent()){
            return usuariOptional.get();
        }
        return null;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        Usuario representativo = usuarioRepository.insert(usuario);
        return representativo;
    }

    @Override
    public Usuario eliminarUsuario(String id) {
        Usuario usuarioToDel = consultarUsuario(id);
        if(usuarioToDel != null){
            usuarioRepository.deleteById(id);
            return usuarioToDel;
        }
        return null;
    }
    
    
    @Override
    public Usuario guardarSuspension(String id, Suspension suspension){
        Usuario usuarioToSus = consultarUsuario(id);
        if(usuarioToSus != null){
            List<Suspension> respuesta = new ArrayList<>();
            respuesta = usuarioToSus.getSuspensions();
            respuesta.add(suspension);
            usuarioToSus.setSuspensions(respuesta);
            return usuarioToSus;
        }
        return null;
    }
}
