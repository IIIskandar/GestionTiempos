/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import utp.edu.co.tiempos.documents.Descripcion;
import utp.edu.co.tiempos.documents.Proyecto;
import utp.edu.co.tiempos.documents.Usuario;
import utp.edu.co.tiempos.repository.ProyectoRepository;
import utp.edu.co.tiempos.documents.Tarea;
import utp.edu.co.tiempos.documents.TiempoSuspensiones;
import utp.edu.co.tiempos.documents.TipoSuspensiones;
import utp.edu.co.tiempos.dto.CategoriasDTO;
import utp.edu.co.tiempos.repository.DescripcionRepository;
import utp.edu.co.tiempos.repository.TareaRepository;
import utp.edu.co.tiempos.repository.TipoSuspensionesRepository;
import utp.edu.co.tiempos.repository.UsuarioRepository;
import utp.edu.co.tiempos.service.ConfiguracionService;
import utp.edu.co.tiempos.dto.TipoSuspensionesDTO;

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
    private TipoSuspensionesRepository tipoSuspensionesRepository;
    private ModelMapper modMapper;

    public DefaultServiceConfiguracion(TareaRepository tareaRepository, UsuarioRepository usuarioRepository, ProyectoRepository proyectoRepository, DescripcionRepository descripcionRepository, TipoSuspensionesRepository tipoSuspensionesRepository, ModelMapper modMapper) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
        this.proyectoRepository = proyectoRepository;
        this.descripcionRepository = descripcionRepository;
        this.tipoSuspensionesRepository = tipoSuspensionesRepository;
        this.modMapper = modMapper;
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
    public String eliminarUsuario(String cc) {
        Usuario usuarioToDel = consultarUsuariobyCC(cc);
        List<Descripcion> descripciones = descripcionRepository.findByMadeBy(cc);
        if(descripciones.isEmpty()&&usuarioToDel.getSuspensions().isEmpty()){
            if(usuarioToDel != null){
                usuarioRepository.deleteById(usuarioToDel.getId());
                return "Usuario Borrado Correctamente";
            }
        }else{
            return "No se pudo borrar el usuario, porque tiene suspensiones o registros realizados";
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

    //consulta un proyecto por su ID
    @Override
    public Proyecto consultarProyecto(String id) {
        Optional<Proyecto> proyectOptional = proyectoRepository.findById(id);
        if(proyectOptional.isPresent()){
            return proyectOptional.get();
        }
        return null;
    }

    //guarda un proyecto, con sus tareas, y asigna los usuarios
    @Override
    public Proyecto guardarProyecto(Proyecto proyecto) {
        long conT=0;
        long conU=0;
        Usuario usuarioHelper;
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        if(!proyecto.getTareas().isEmpty()){
            conT = proyecto.getTareas().size();
            for (int i = 0; i < conT; i++) {
                tareaRepository.insert(proyecto.getTareas().get(i));
            }
        }
        if(!proyecto.getUsersId().isEmpty()){
            conU = proyecto.getUsersId().size();
            for (int i = 0; i < conU; i++) {
                usuarioHelper = consultarUsuariobyCC(proyecto.getUsersId().get(i).getCc());
                listaUsuarios.add(usuarioHelper);
            }
        }
        proyecto.setUsersId(listaUsuarios);
        Proyecto representativo = proyectoRepository.insert(proyecto);
        return representativo;
    }
    
    @Override
    public Proyecto editarProyectoTareas(String id, List<Tarea> tareas){
        Proyecto proyectoAux = consultarProyecto(id);
        List<Tarea> tareasProyecto = proyectoAux.getTareas();
       
        for (Tarea tareaAux : tareas) {
            tareaRepository.insert(tareaAux);
            tareasProyecto.add(tareaAux);
        }
        
        proyectoAux.setTareas(tareasProyecto);
        proyectoRepository.save(proyectoAux);

        return proyectoAux;
    }
    
    @Override
    public Proyecto editarProyectoUsuarios(String id, List<String> UsuariosCc){
        Proyecto proyectoAux = consultarProyecto(id); 
        List<Usuario> usuariosProyecto = proyectoAux.getUsersId();
         for (String ccs : UsuariosCc) {
            Usuario usuarioAux = consultarUsuariobyCC(ccs);
            usuariosProyecto.add(usuarioAux);
        }
        proyectoAux.setUsersId(usuariosProyecto);
        proyectoRepository.save(proyectoAux);
        return proyectoAux;
    }

    //elimina un proyecto por ID
    @Override
    public Proyecto eliminarProyecto(String id) {
        Proyecto proyectoToDel = consultarProyecto(id);
        if(proyectoToDel != null){
            proyectoRepository.deleteById(id);
            return proyectoToDel;
        }
        return null;
    }
    
    @Override
    public Usuario eliminarUsuarioProyecto(String id, String cc){
        Proyecto proyectoAux = consultarProyecto(id);
        Usuario usuarioAux = consultarUsuariobyCC(cc);
        List<Usuario> usuariosProyecto = proyectoAux.getUsersId();
        List<Tarea> tareasProyecto = proyectoAux.getTareas();
        for (Tarea tarea : tareasProyecto) {
            List<Descripcion> descripciones = tarea.getDescripciones();
            for (Descripcion descripcion : descripciones) {
                if(cc.equals(descripcion.getMadeBy()))
                    return null;
            }
        }
        for (Usuario usuario : usuariosProyecto) {
            if(usuario.getCc().equals(cc)){
                usuariosProyecto.remove(usuario);
                break;
            }
        }
        proyectoAux.setUsersId(usuariosProyecto);
        proyectoRepository.save(proyectoAux);
        return usuarioAux;
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
        proyectoRepository.save(proyectoHelper);
        
        return proyectoHelper;
    }

    //consulta un usuario por cedula
    @Override
    public Usuario consultarUsuariobyCC(String cc) {
        Optional<Usuario> usuariOptional = usuarioRepository.findByCc(cc);
        if(usuariOptional.isPresent()){
            return usuariOptional.get();
        }
        return null;
    }

    //lista las tareas de un proyecto
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
    
    //consulta una tarea por id
    @Override
    public Tarea consultarTarea(String id) {   
    Optional<Tarea> tareaOptional = tareaRepository.findById(id);
        if(tareaOptional.isPresent()){
            return tareaOptional.get();
        }
        return null;
    }

    //elimina una tarea por id
    @Override
    public Tarea eliminarTarea(String idProyecto, String idTarea) {
        Proyecto proyectoAux = consultarProyecto(idProyecto);
        Tarea tareaToDel = consultarTarea(idTarea);
        List<Tarea> tareasAux = proyectoAux.getTareas();
        for (Tarea tarea : tareasAux) {
            if(tarea.getId().equals(idTarea)&&tarea.getDescripciones().isEmpty()){
                tareasAux.remove(tarea);
                break;
            }
        }
        proyectoAux.setTareas(tareasAux);
        proyectoRepository.save(proyectoAux);
        if(tareaToDel.getDescripciones().isEmpty()){
            if(tareaToDel != null){
                tareaRepository.deleteById(idTarea);
                return tareaToDel;
            }
        }
        return null;
    }

    //guarda una tarea, se requiere el id del proyecto 
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

    //lista los registros de una tarea
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

    //consulta registros de una tarea
    @Override
    public Descripcion consultarRegistro(String id) {
        Optional<Descripcion> descripcionOptional = descripcionRepository.findById(id);
        if(descripcionOptional.isPresent()){
            return descripcionOptional.get();
        }
        return null;
    }

    //elimina un registro
    @Override
    public Descripcion eliminarRegistro(String id) {
        Descripcion descripcionToDel = consultarRegistro(id);
        if(descripcionToDel != null){
            tareaRepository.deleteById(id);
            return descripcionToDel;
        }
        return null;
    }
    
    //consulta los proyectos a los que pertenece un usuario
    @Override
    public List<Proyecto> consultarProyectosUsuario(String cc){
        List<Proyecto> proyectos = listaProyectos();
        List<Proyecto> proyectosUsuario = new ArrayList<>();
        for (Proyecto proyectoAux : proyectos) {
            for (Usuario usuario : proyectoAux.getUsersId()) {
                if(cc.equals(usuario.getCc()))
                  proyectosUsuario.add(proyectoAux);
            }
        }
        return proyectosUsuario;
    }
    
    //crea un tipo de suspension
    @Override
    public TipoSuspensionesDTO crearTipoSuspension(TipoSuspensionesDTO tiposuspensiondto){
        TipoSuspensiones aux = modMapper.map(tiposuspensiondto,TipoSuspensiones.class);
        TipoSuspensiones representativo = tipoSuspensionesRepository.insert(aux);
        return modMapper.map(representativo, TipoSuspensionesDTO.class);
    }
    
    //lista los tipos de suspension creados por el usuario
    @Override
    public List<TipoSuspensiones> listaTipoSuspensiones() {
        List<TipoSuspensiones> respuesta = new ArrayList<>();
        respuesta = tipoSuspensionesRepository.findAll();
        if(!respuesta.isEmpty()){
            return respuesta;
        }
        return null;  
    }
    
    //consulta una suspension por nombre
    @Override
    public TipoSuspensionesDTO consultarSuspension(String nombre) {   
    Optional<TipoSuspensiones> tipOptional = tipoSuspensionesRepository.findByName(nombre);
        if(tipOptional.isPresent()){
            return modMapper.map(tipOptional.get(), TipoSuspensionesDTO.class);
        }
        return null;
    }
    
    //elimina un tipo de suspension por el nombre FALTA COSNSULTAR QUE NO HAYA SIDO UTILIZADA
    @Override
    public String eliminarTipoSuspension(String nombre) {
         
        Optional<TipoSuspensiones> tipOptional = tipoSuspensionesRepository.findByName(nombre);
        List<Usuario> usuarios = listaUsuarios();
        for (Usuario usuario : usuarios) {
            List<TiempoSuspensiones> suspensiones = usuario.getTiempoSuspensiones();
            for (int i = 0; i < suspensiones.size(); i++) {
                if(nombre.equals(suspensiones.get(i).getNombre()))
                    return "Tipo suspension usada";
            }
        }
        String id = tipOptional.get().getId();
        if(tipOptional.isPresent()){
            tipoSuspensionesRepository.deleteById(id);
            return "Eliminada correctamente";
        }
        return null;
    }
    
    @Override
    public List<CategoriasDTO> listaCategorias(){
        List<Tarea> tareasAux = tareaRepository.findAll();
        List<CategoriasDTO> categorias = new ArrayList<>();
        List<String> categoriasAux = new ArrayList<>();
        for (Tarea tarea : tareasAux) {
            CategoriasDTO categoria = new CategoriasDTO();
            if(!categoriasAux.contains(tarea.getCategory())){
                categoriasAux.add(tarea.getCategory());
                categoria.setName(tarea.getCategory());  
                categorias.add(categoria);
            }
        }
        return categorias;
    }
}
