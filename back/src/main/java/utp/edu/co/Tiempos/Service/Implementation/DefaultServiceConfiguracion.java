/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Repository.ProyectoRepository;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Repository.DescripcionRepository;
import utp.edu.co.Tiempos.Repository.TareaRepository;
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
    private TareaRepository tareaRepository;
    private DescripcionRepository descripcionRepository;

    public DefaultServiceConfiguracion(TareaRepository tareaRepository, UsuarioRepository usuarioRepository, ProyectoRepository proyectoRepository, DescripcionRepository descripcionRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
        this.proyectoRepository = proyectoRepository;
        this.descripcionRepository = descripcionRepository;
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

    //inserta un usuario
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        Usuario representativo = usuarioRepository.insert(usuario);
        return representativo;
    }
    
    //actualiza un usuario
    @Override
    public Usuario actualizarUsuario(Usuario usuario){
        Optional<Usuario> update = usuarioRepository.findById(usuario.getId());
        if(update.isPresent()){
            Usuario respuesta = usuarioRepository.save(update.get());
            return respuesta;
        }
        return null;
    }

    //elimina un usuario
    @Override
    public Usuario eliminarUsuario(String id) {
        Usuario usuarioToDel = consultarUsuario(id);
        if(usuarioToDel != null){
            usuarioRepository.deleteById(id);
            return usuarioToDel;
        }
        return null;
    }

    //lista los proyectos
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
        Optional<Proyecto> proyectOptional = proyectoRepository.findById(id);
        if(proyectOptional.isPresent()){
            return proyectOptional.get();
        }
        return null;
    }

    @Override
    public Proyecto guardarProyecto(Proyecto proyecto) {
       Proyecto representativo = proyectoRepository.insert(proyecto);
       return representativo;
    }

    @Override
    public Proyecto eliminarProyecto(String id) {
        Proyecto proyectoToDel = consultarProyecto(id);
        if(proyectoToDel != null){
            proyectoRepository.deleteById(id);
            return proyectoToDel;
        }
        return null;
    }
    

    //se le asigna un usuario al proyecto añadiendo el id del usuario en el array de usuariosID que tien el proyecto
    //del mismo modo se hace para el usuario añadiendo el id del proyecto a su array de proyectosID
    @Override
    public Proyecto asignarUsuarioaProyecto(String idProyecto, String idUsuario) {
        Proyecto proyectoHelper = consultarProyecto(idProyecto);
        Usuario usuarioHelper = consultarUsuario(idUsuario);
        List<Usuario> usuariosId = new ArrayList<>();
        usuariosId = proyectoHelper.getUsersId();
        usuariosId.add(usuarioHelper);
        proyectoHelper.setUsersId(usuariosId);
        //List<Proyecto> proyectosId = new ArrayList<>();
//        proyectosId = usuarioHelper.getProjectsId();
//        proyectosId.add(proyectoHelper);
//        usuarioHelper.setProjectsId(proyectosId);
//        usuarioRepository.save(usuarioHelper);
        proyectoRepository.save(proyectoHelper);
        
        return proyectoHelper;
    }

    @Override
    public Usuario consultarUsuariobyCC(String cc) {
        Optional<Usuario> usuariOptional = usuarioRepository.findByCc(cc);
        if(usuariOptional.isPresent()){
            return usuariOptional.get();
        }
        return null;
    }

    @Override
    public List<Tarea> listaTareas(String id) {
        List<Tarea> respuesta = new ArrayList<>();
        Proyecto proyectoHelper = consultarProyecto(id);
        respuesta = proyectoHelper.getTareas();
        if(!respuesta.isEmpty()){
            return respuesta;
        }
        return null; 
    }
    
    @Override
    public Tarea consultarTarea(String id) {   
    Optional<Tarea> tareaOptional = tareaRepository.findById(id);
        if(tareaOptional.isPresent()){
            return tareaOptional.get();
        }
        return null;
    }

    @Override
    public Tarea eliminarTarea(String id) {
       Tarea tareaToDel = consultarTarea(id);
        if(tareaToDel != null){
            tareaRepository.deleteById(id);
            return tareaToDel;
        }
        return null;
    }

    @Override
    public Tarea guardarTarea(String id, Tarea tarea) {
        Tarea representativo = tareaRepository.insert(tarea);
        Proyecto proyectoHelper = consultarProyecto(id);
        List<Tarea> tareas = new ArrayList<>();
        tareas = proyectoHelper.getTareas();
        tareas.add(tarea);
        proyectoHelper.setTareas(tareas);
        proyectoRepository.save(proyectoHelper);
        return representativo;
    }    

    @Override
    public List<Descripcion> listaRegistros(String id) {
       List<Descripcion> respuesta = new ArrayList<>();
        Tarea tareaHelper = consultarTarea(id);
        respuesta = tareaHelper.getDescripciones();
        if(!respuesta.isEmpty()){
            return respuesta;
        }
        return null; 
    }

    @Override
    public Descripcion consultarRegistro(String id) {
        Optional<Descripcion> descripcionOptional = descripcionRepository.findById(id);
        if(descripcionOptional.isPresent()){
            return descripcionOptional.get();
        }
        return null;
    }

    @Override
    public Descripcion eliminarRegistro(String id) {
        Descripcion descripcionToDel = consultarRegistro(id);
        if(descripcionToDel != null){
            tareaRepository.deleteById(id);
            return descripcionToDel;
        }
        return null;
    }
}
