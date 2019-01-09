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
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Usuario;
<<<<<<< HEAD:src/main/java/utp/edu/co/Tiempos/Service/Implementation/DefaultServiceConfiguracion.java
import utp.edu.co.Tiempos.Repository.ProyectoRepository;
||||||| merged common ancestors
=======
import utp.edu.co.Tiempos.Documents.Suspension;
>>>>>>> f3a2861718249a171c0372d89fe026d30dc263b4:back/src/main/java/utp/edu/co/Tiempos/Service/Implementation/DefaultServiceConfiguracion.java
import utp.edu.co.Tiempos.Repository.UsuarioRepository;
import utp.edu.co.Tiempos.Service.ConfiguracionService;

/**
 *
 * @author C-Lug
 */
@Service
public class DefaultServiceConfiguracion implements ConfiguracionService{

    private UsuarioRepository usuarioRepository;
    private ProyectoRepository proyectoRepository;

    public DefaultServiceConfiguracion(UsuarioRepository usuarioRepository, ProyectoRepository proyectoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.proyectoRepository = proyectoRepository;
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
    public Usuario actualizarUsuario(Usuario usuario){
        Optional<Usuario> update = usuarioRepository.findById(usuario.getId());
        if(update.isPresent()){
            Usuario respuesta = usuarioRepository.save(update.get());
            return respuesta;
        }
        return null;
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
    public List<Proyecto> listaProyectos() {
        List<Proyecto> respuesta = new ArrayList<>();
        respuesta = proyectoRepository.findAll();
        if(!respuesta.isEmpty()){
            return respuesta;
        }
        return null;      
    }

    @Override
    public Proyecto consultarProyecto(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto guardarProyecto(Proyecto proyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto eliminarProyecto(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
