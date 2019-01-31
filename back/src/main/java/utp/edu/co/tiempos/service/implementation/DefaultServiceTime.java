/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import utp.edu.co.tiempos.documents.Descripcion;
import utp.edu.co.tiempos.documents.Proyecto;
import utp.edu.co.tiempos.documents.Suspension;
import utp.edu.co.tiempos.documents.Tarea;
import utp.edu.co.tiempos.documents.TiempoSuspensiones;
import utp.edu.co.tiempos.documents.Usuario;
import utp.edu.co.tiempos.repository.DescripcionRepository;
import utp.edu.co.tiempos.repository.ProyectoRepository;
import utp.edu.co.tiempos.repository.TareaRepository;
import utp.edu.co.tiempos.repository.UsuarioRepository;
import utp.edu.co.tiempos.service.ConfiguracionService;
import utp.edu.co.tiempos.service.TimeService;
import utp.edu.co.tiempos.dto.ProyectoTareaUsuarioDTO;
import utp.edu.co.tiempos.dto.RegistrosRangoDTO;
import utp.edu.co.tiempos.dto.SuspensionDTO;
import utp.edu.co.tiempos.dto.TareaCategoriaDTO;
import utp.edu.co.tiempos.dto.TareasPorProyectoDTO;
import utp.edu.co.tiempos.dto.TiempoProyectosDTO;
import utp.edu.co.tiempos.dto.TiempoSuspensionTipoDTO;
import utp.edu.co.tiempos.dto.TiempoTareaUsuarioDTO;
import utp.edu.co.tiempos.dto.TiempoUsuarioDTO;
import utp.edu.co.tiempos.dto.UsuariosPorProyectoDTO;

/**
 *
 * @author C-Lug
 */
@Service
public class DefaultServiceTime implements TimeService{
        private ConfiguracionService configuracionService;
        private UsuarioRepository usuarioRepository;
        private TareaRepository tareaRepository;
        private ProyectoRepository proyectoRepository;
        private DescripcionRepository descripcionRepository;
        private ModelMapper modMapper;


    public DefaultServiceTime(ConfiguracionService configuracionService, UsuarioRepository usuarioRepository, TareaRepository tareaRepository, ProyectoRepository proyectoRepository, DescripcionRepository descripcionRepository, ModelMapper modMapper) {
        this.configuracionService = configuracionService;
        this.usuarioRepository = usuarioRepository;
        this.tareaRepository = tareaRepository;
        this.proyectoRepository = proyectoRepository;
        this.descripcionRepository = descripcionRepository;
        this.modMapper = modMapper;
    }
        
    
    //inicia una suspension por usuario
    @Override
    public Usuario iniciarSuspension(String id, SuspensionDTO suspensiondto){
        boolean nombreB=false;
        Usuario usuarioToSus = configuracionService.consultarUsuariobyCC(id);
        if(usuarioToSus != null){
            List<Suspension> respuesta = new ArrayList<>();
            //obtengo las suspeniones del usuario
            respuesta = usuarioToSus.getSuspensions();
            //mapeo la suspensionDTO q me llego
            Suspension aux=modMapper.map(suspensiondto,Suspension.class);
            //tomo la fecha de inicio y la guardo en la suspension
            Date fechaInicio = new Date();
            aux.setFechaInicio(fechaInicio);
            //agrego la suspension a la lista de suspensiones
            respuesta.add(aux);
            //guardo la lista de suspensiones en el usuario
            usuarioToSus.setSuspensions(respuesta);
            //compruebo si el nombre de la suspension ya esta en la lista de tiempos
            List<TiempoSuspensiones> listaTiempos = usuarioToSus.getTiempoSuspensiones();
            for(int i=0; i<listaTiempos.size(); i++){
                if(listaTiempos.get(i).getNombre().equals(suspensiondto.getTipoSuspension()))
                    nombreB=true;
            }
            //si el nombre ya esta guardo el usuario sin hacer ningun cambio en la lista de tiempo
            if (nombreB){
                usuarioToSus.setStatus("suspension");
                usuarioRepository.save(usuarioToSus);}
            //sino esta, tomo el nombre y lo agrego a la lista de tiempos y luego lo guardo en el
            else{
                TiempoSuspensiones auxTiempo= new TiempoSuspensiones();
                auxTiempo.setNombre(aux.getTipoSuspension());
                auxTiempo.setAcumulado(0L);
                listaTiempos.add(auxTiempo);
                usuarioToSus.setTiempoSuspensiones(listaTiempos);
                usuarioToSus.setStatus("suspension");
                usuarioRepository.save(usuarioToSus);
            }
            return usuarioToSus;
        }
        return null;
    }
    
    
    //finaliza una suspension por usuario
    @Override
    public Usuario finalizarSuspension(String id){
        Usuario usuarioFinSus = configuracionService.consultarUsuariobyCC(id);
        List<Suspension> respuesta = new ArrayList<>();
        //obtengo los tiempos de las suspensiones
        List<TiempoSuspensiones> tiempos = usuarioFinSus.getTiempoSuspensiones();
        //obtengo las suspensiones
        respuesta = usuarioFinSus.getSuspensions();
        int aux = respuesta.size()-1;
        //guarda la fecha fin de la suspension
        Date fechaFin = new Date();
        //le asigno la fecha cierre a la ultima suspension
        respuesta.get(aux).setFechaFin(fechaFin);
        //calculo el tiempo que se demoro la suspension en horas
        long contador = respuesta.get(aux).getFechaFin().getTime()-respuesta.get(aux).getFechaInicio().getTime();
        //convierto a segundos
        contador = contador/1000;
        //convierto a minutos
        contador = contador/60;
        //convierto a horas
        //contador = contador/60;
        //guarda el tiempo que se tardo en la suspension
        respuesta.get(aux).setTiempoSuspension(contador);
        //guardo esa suspension en la lista del usuario
        usuarioFinSus.setSuspensions(respuesta);
        //miro si hay tiempo acumulado en las suspensiones y lo guardo en sumatorias
        List<Long> sumatorias = new ArrayList<>();
        for (int i = 0; i < tiempos.size(); i++) {
            if(!(tiempos.get(i) == null))
                sumatorias.add(tiempos.get(i).getAcumulado());
        }
        //
        for (int i = 0; i< tiempos.size(); i++){
            if(respuesta.get(aux).getTipoSuspension().equals(tiempos.get(i).getNombre()))
                usuarioFinSus.getTiempoSuspensiones().get(i).setAcumulado(contador+sumatorias.get(i));}
        usuarioFinSus.setStatus("disponible");
        usuarioRepository.save(usuarioFinSus);
        return usuarioFinSus;
    }

    //inicia un registro con el id de la tarea
    @Override
    public Descripcion iniciarRegistro(String id, String status, Descripcion descripcion) {
        Tarea tareaHelper = configuracionService.consultarTarea(id);
        Usuario usuarioHelper = configuracionService.consultarUsuariobyCC(descripcion.getMadeBy());
        List<Descripcion> registros = new ArrayList<>();
        registros = tareaHelper.getDescripciones();
        Date fechaInicio = new Date();
        descripcion.setFechaInicio(fechaInicio);
        Descripcion representativo = descripcionRepository.insert(descripcion);
        registros.add(descripcion);
        tareaHelper.setDescripciones(registros);
        tareaHelper.setStatus(status);
        usuarioHelper.setStatus(status);
        usuarioRepository.save(usuarioHelper);
        tareaRepository.save(tareaHelper);
        return representativo;
    }

    //finaliza un resgistro con el id de la tarea
    @Override
    public Descripcion finalizarRegistro(String id, Descripcion descripcion, String status) {
        long auxCont=0;
        Tarea tareaHelper = configuracionService.consultarTarea(id);
        tareaHelper.setStatus(status);
        List<Descripcion> respuesta = new ArrayList<>();
        respuesta = tareaHelper.getDescripciones();
        int aux = respuesta.size()-1;
        Date fechaFin = new Date();
        Descripcion descripcionHelper = configuracionService.consultarRegistro(respuesta.get(aux).getId());
        Usuario usuarioHelper = configuracionService.consultarUsuariobyCC(descripcionHelper.getMadeBy());
        descripcionHelper.setFechaFin(fechaFin);
        descripcionHelper.setJobDetails(descripcion.getJobDetails());
        long contador = descripcionHelper.getFechaFin().getTime()-descripcionHelper.getFechaInicio().getTime();
        contador = contador/1000;
        contador = contador/60;
        if(!(tareaHelper.getJobTime()==null))
            auxCont = tareaHelper.getJobTime();
        tareaHelper.setJobTime(auxCont + contador);
        descripcionHelper.setJobTime(contador);
        descripcionRepository.save(descripcionHelper);
        respuesta.set(aux, descripcionHelper);
        tareaHelper.setDescripciones(respuesta);
        tareaRepository.save(tareaHelper);
        usuarioHelper.setStatus("disponible");
        usuarioRepository.save(usuarioHelper);
        return descripcionHelper;
    }
    
    //contabiliza el tiempo trabajado de un proyecto 
    @Override
    public Long contabilizarProyecto(String id) {
        long contador=0;
        Proyecto proyectoAux = configuracionService.consultarProyecto(id);
        List<Tarea> tareasAux = proyectoAux.getTareas();
        for (Tarea tarea : tareasAux) {
            contador = tarea.getJobTime() + contador;
        }
        proyectoAux.setJobTime(contador);
        proyectoRepository.save(proyectoAux);
        return contador;
    }
    
    //contabiliza el tiempo trabajado de todos los proyectos y devuelve una lista de Proyecto
    @Override
    public List<Proyecto> contabilizarProyectos() {
        long contador=0;
        List<Proyecto> proyectosAux = configuracionService.listaProyectos();
        for (Proyecto proyectoAux : proyectosAux) {
            List<Tarea> tareasAux = proyectoAux.getTareas();
            for (Tarea tarea : tareasAux) {
                if(!(tarea.getJobTime()== null))
                contador = tarea.getJobTime() + contador;
            }
        proyectoAux.setJobTime(contador);
        proyectoRepository.save(proyectoAux);
        contador = 0;
        }
        return proyectosAux;
    }
    
    @Override
    public List<TiempoProyectosDTO> tiempoProyectosFecha(String fechaInicio, String fechaFin){ 
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        List<TiempoProyectosDTO> proyectosPorUsuario = new ArrayList<>();
        List<Proyecto> proyectos = proyectoRepository.findAll();
        long contador = 0;
        List<Descripcion> registrosTareas = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            TiempoProyectosDTO proyectoUsuario = new TiempoProyectosDTO();
            List<Tarea> tareas = proyecto.getTareas();
            for (Tarea tarea : tareas) {
                if(!(tarea==null)){
                    registrosTareas = tarea.getDescripciones();
                    for (Descripcion registroTarea : registrosTareas) {
                        if(!(registroTarea.getFechaFin() == null))
                            if((registroTarea.getFechaInicio().after(dateObj1)||registroTarea.getFechaInicio().equals(dateObj1))&&(registroTarea.getFechaFin().before(dateObj2)||registroTarea.getFechaInicio().equals(dateObj2))){
                                if(!(registroTarea.getJobTime() == null))
                                    contador = contador + registroTarea.getJobTime();
                        }
                    }  
                }
            }   
            proyectoUsuario.setName(proyecto.getName());
            proyectoUsuario.setJobTimeUser(contador);
            proyectosPorUsuario.add(proyectoUsuario);
            contador = 0;
        }
        
        return proyectosPorUsuario;
    }
    //consulta el tiempo de un usuario con el id de los registros trabajados
    @Override
    public TiempoUsuarioDTO tiempoUsuarios(String cc) {
        List<TiempoUsuarioDTO> tiempos = descripcionRepository.consultarTiempoUsuario(cc);
        return tiempos.get(0);
    }
    
    //consulta el tiempo de todos los usuarios
    @Override
    public List<TiempoUsuarioDTO> tiempoAllUsers() {
        List<TiempoUsuarioDTO> tiempos = descripcionRepository.consultasTiemposAllUsers();
        return tiempos;
    }

    //consulta el tiempo de las tareas por categoria
    @Override
    public List<TareaCategoriaDTO> tiempoPorCategoria(String fechaInicio, String fechaFin) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        List<RegistrosRangoDTO> registrosEnRango = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        List<TareaCategoriaDTO> tiemposCategoria = new ArrayList<>();
        long contador = 0;
        List<Tarea> tareasAux = tareaRepository.findAll();
        for (Tarea tareaAux : tareasAux) {
            if(!(tareaAux==null)){
                RegistrosRangoDTO registroEnRango = new RegistrosRangoDTO();
                List<Descripcion> registrosTareas = tareaAux.getDescripciones();
                for (Descripcion registroTarea : registrosTareas) {
                    if((registroTarea.getFechaInicio() != null)&&(registroTarea.getFechaFin() != null)) 
                        if((registroTarea.getFechaInicio().after(dateObj1)||registroTarea.getFechaInicio().equals(dateObj1))&&(registroTarea.getFechaFin().before(dateObj2)||registroTarea.getFechaInicio().equals(dateObj2))){
                            if(!(registroTarea.getJobTime()==null))
                                contador = registroTarea.getJobTime() + contador;
                    }
                }
                registroEnRango.setTarea(tareaAux.getName());
                registroEnRango.setCategory(tareaAux.getCategory());
                registroEnRango.setJobTime(contador);
                registrosEnRango.add(registroEnRango);
                contador = 0;
            }
        }
        
        for (int i = 0; i < registrosEnRango.size(); i++) {
            if(!categories.contains(registrosEnRango.get(i).getCategory()))
                categories.add(registrosEnRango.get(i).getCategory());
        }
        
        for (int i = 0; i < categories.size(); i++) {
            TareaCategoriaDTO tiempoCategoria = new TareaCategoriaDTO();
            for (int j = 0; j < registrosEnRango.size(); j++) {
                if(categories.get(i).equals(registrosEnRango.get(j).getCategory()))
                    if(!(registrosEnRango.get(i).getJobTime()==null))
                        contador = registrosEnRango.get(j).getJobTime() + contador;
            }
            if(contador!=0){
                tiempoCategoria.setCategory(categories.get(i));
                tiempoCategoria.setJobTimeCategory(contador);
                tiemposCategoria.add(tiempoCategoria);}
            contador = 0;
        }
        
        return tiemposCategoria;
    }
     

    //consulta el tiempo por tipo de suspension
    @Override
    public List<TiempoSuspensionTipoDTO> tiempoPorTipoSus(String fechaInicio, String fechaFin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        List<TiempoSuspensionTipoDTO> tiemposSuspension = usuarioRepository.tiempoPorTipoSuspension(dateObj1, dateObj2);
        return tiemposSuspension;
    }

    //consulta el tiempo total de suspension de un usuario
    @Override
    public Long TiempoSuspensionUsuarioTotal(String cc) {
        long contador = 0;
        Usuario usuarioAux = configuracionService.consultarUsuariobyCC(cc);
        List<TiempoSuspensiones> tiempos = usuarioAux.getTiempoSuspensiones();
        for (TiempoSuspensiones tiempo : tiempos) {
            contador = tiempo.getAcumulado() + contador;
        }
        return contador;
    }

    //consulta el tiempo que ha trabajado un usuario por tarea especifica
    @Override
    public TiempoTareaUsuarioDTO tiempoUsuarioPorTarea(String idTarea, String cc, String fechaInicio, String fechaFin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        TiempoTareaUsuarioDTO tiempoUsuario = new TiempoTareaUsuarioDTO();
        long contador = 0;
        List<String> descripcionesId= new ArrayList<>();
        Tarea tareaAux = configuracionService.consultarTarea(idTarea);
        List<Descripcion> registrosTareas = tareaAux.getDescripciones();
        for (Descripcion registroTarea : registrosTareas) {
            if((registroTarea.getFechaInicio() != null)&&(registroTarea.getFechaFin() != null))
                if((registroTarea.getMadeBy().equals(cc))&&(registroTarea.getFechaInicio().after(dateObj1)||registroTarea.getFechaInicio().equals(dateObj1))&&(registroTarea.getFechaFin().before(dateObj2)||registroTarea.getFechaInicio().equals(dateObj2))){
                    if(!(registroTarea.getJobTime()==null)){
                        contador = registroTarea.getJobTime() + contador;
                        descripcionesId.add(registroTarea.getId());}
                }
        }
        tiempoUsuario.setName(tareaAux.getName());
        tiempoUsuario.setJobTimeUser(contador);
        tiempoUsuario.setDescripcionesId(descripcionesId);
        return tiempoUsuario;
    }
    
    
    //consulta las tareas realizadas por un usuario
    @Override
    public List<ProyectoTareaUsuarioDTO> tareasRealizadosPorUsuarioFecha(String cc, String fechaInicio, String fechaFin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        List<ProyectoTareaUsuarioDTO> proyectosPorUsuario = new ArrayList<>();
        List<Proyecto> proyectos = proyectoRepository.findAll();
        long contador = 0;
        long contadorRegistros=0;
        int i =0;
        List<Descripcion> registrosTareas = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            List<Tarea> tareas = proyecto.getTareas();
            for (Tarea tarea : tareas) {
                if(!(tarea==null)){
                ProyectoTareaUsuarioDTO tareaUsuario = new ProyectoTareaUsuarioDTO();
                registrosTareas = tarea.getDescripciones();
                for (Descripcion registroTarea : registrosTareas) {
                    if((registroTarea.getFechaInicio() != null)&&(registroTarea.getFechaFin() != null)){
                        if(registroTarea.getMadeBy().equals(cc)&&(registroTarea.getFechaInicio().after(dateObj1)||registroTarea.getFechaInicio().equals(dateObj1))&&(registroTarea.getFechaFin().before(dateObj2)||registroTarea.getFechaInicio().equals(dateObj2))){
                            if(!(registroTarea.getJobTime()== null)){
                                contador = contador + registroTarea.getJobTime();
                                contadorRegistros = contadorRegistros + 1;}
                        }
                    }
                }
                tareaUsuario.setNameProyecto(proyecto.getName());
                tareaUsuario.setNameTarea(tarea.getName());
                tareaUsuario.setStatus(tarea.getStatus());
                tareaUsuario.setTotalRegistros(contadorRegistros);
                tareaUsuario.setJobTimeUser(contador);
                if(contadorRegistros>0)
                    proyectosPorUsuario.add(tareaUsuario);
                contador = 0;
                contadorRegistros=0;
                }
            }
        }
        
        return proyectosPorUsuario;
    }
    
    //consulta las tareas realizadas por un usuario
    @Override
    public List<ProyectoTareaUsuarioDTO> tareasRealizadosPorUsuario(String cc){
        
        List<ProyectoTareaUsuarioDTO> proyectosPorUsuario = new ArrayList<>();
        List<Proyecto> proyectos = proyectoRepository.findAll();
        long contador = 0;
        long contadorRegistros=0;
        int i =0;
        List<Descripcion> registrosTareas = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            List<Tarea> tareas = proyecto.getTareas();
            for (Tarea tarea : tareas) {
                if(!(tarea==null)){
                ProyectoTareaUsuarioDTO tareaUsuario = new ProyectoTareaUsuarioDTO();
                registrosTareas = tarea.getDescripciones();
                for (Descripcion registroTarea : registrosTareas) {
                    if(registroTarea.getMadeBy().equals(cc)){
                        if(!(registroTarea.getJobTime()==null)){
                            contador = contador + registroTarea.getJobTime();
                            contadorRegistros = contadorRegistros + 1;}
                    }
                }
                tareaUsuario.setNameProyecto(proyecto.getName());
                tareaUsuario.setNameTarea(tarea.getName());
                tareaUsuario.setStatus(tarea.getStatus());
                tareaUsuario.setTotalRegistros(contadorRegistros);
                tareaUsuario.setJobTimeUser(contador);
                if(contadorRegistros>0)
                    proyectosPorUsuario.add(tareaUsuario);
                contador = 0;
                contadorRegistros=0;
                }
            }
        }
        
        return proyectosPorUsuario;
    }
    
    //consulta los proyectos realizados por un usuario
    @Override
    public List<TiempoProyectosDTO> proyectosRealizadosPorUsuario(String cc,String fechaInicio, String fechaFin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        List<TiempoProyectosDTO> proyectosPorUsuario = new ArrayList<>();
        List<Proyecto> proyectos = proyectoRepository.findAll();
        long contador = 0;
        List<Descripcion> registrosTareas = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            TiempoProyectosDTO proyectoUsuario = new TiempoProyectosDTO();
            List<Tarea> tareas = proyecto.getTareas();
            for (Tarea tarea : tareas) {
                if(!(tarea==null)){
                    registrosTareas = tarea.getDescripciones();
                    for (Descripcion registroTarea : registrosTareas) {
                        if((registroTarea.getFechaInicio() != null)&&(registroTarea.getFechaFin() != null)){
                            if(registroTarea.getMadeBy().equals(cc)&&(registroTarea.getFechaInicio().after(dateObj1)||registroTarea.getFechaInicio().equals(dateObj1))&&(registroTarea.getFechaFin().before(dateObj2)||registroTarea.getFechaInicio().equals(dateObj2)))
                                if(!(registroTarea.getJobTime()==null))
                                    contador = contador + registroTarea.getJobTime();
                        }
                    }
                }
            }   
            proyectoUsuario.setName(proyecto.getName());
            proyectoUsuario.setJobTimeUser(contador);
            if(contador>0)
                proyectosPorUsuario.add(proyectoUsuario);
            contador = 0;
        }
        
        return proyectosPorUsuario;
    }
    
    //consulta los usuarios que han trabajado en un proyecto en especifico, en un rango de fecha
    @Override
    public List<UsuariosPorProyectoDTO> usuariosPorProyecto(String idProyecto, String fechaInicio, String fechaFin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        List<UsuariosPorProyectoDTO> usuariosProyecto= new ArrayList<>();
        Proyecto proyectoAux = configuracionService.consultarProyecto(idProyecto);
        List<Tarea> tareas = proyectoAux.getTareas();
        List<Descripcion> registrosTareas = new ArrayList<>();
        List<String> ccs = new ArrayList<>();
        
        long contador = 0;
        for (Tarea tarea : tareas) {
            if(!(tarea==null)){
                registrosTareas = tarea.getDescripciones();
                for (Descripcion registrosTarea : registrosTareas) {
                    if(!ccs.contains(registrosTarea.getMadeBy()))
                        ccs.add(registrosTarea.getMadeBy());
                }
            }
        }
        for (int i = 0; i < ccs.size(); i++) {
            UsuariosPorProyectoDTO usuarioPP = new UsuariosPorProyectoDTO();
            for (Tarea tarea : tareas) {
                if(!(tarea==null)){
                    registrosTareas = tarea.getDescripciones();
                    for (Descripcion registroTarea : registrosTareas) {
                        if((registroTarea.getFechaInicio() != null)&&(registroTarea.getFechaFin() != null)){
                            if((registroTarea.getMadeBy().equals(ccs.get(i)))&&(registroTarea.getFechaInicio().after(dateObj1)||registroTarea.getFechaInicio().equals(dateObj1))&&(registroTarea.getFechaFin().before(dateObj2)||registroTarea.getFechaInicio().equals(dateObj2)))
                                if(registroTarea.getJobTime() != null)
                                    contador = contador + registroTarea.getJobTime();
                        }
                    }
                }
            }
            usuarioPP.setCc(ccs.get(i));
            usuarioPP.setJobTimeUser(contador);
            usuarioPP.setNameProyecto(proyectoAux.getName());
            usuariosProyecto.add(usuarioPP);
            contador=0;
        }
        return usuariosProyecto;
    }
    
    @Override
    public List<TareasPorProyectoDTO> tareasPorProyecto(String idProyecto, String fechaInicio, String fechaFin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        List<TareasPorProyectoDTO> tareasProyecto= new ArrayList<>();
        Proyecto proyectoAux = configuracionService.consultarProyecto(idProyecto);
        List<Tarea> tareas = proyectoAux.getTareas();
        List<Descripcion> registrosTareas = new ArrayList<>();

        long contador = 0;
            
        for (Tarea tarea : tareas) {
            if(!(tarea==null)){
                TareasPorProyectoDTO tareaPP = new TareasPorProyectoDTO();
                registrosTareas = tarea.getDescripciones();
                for (Descripcion registroTarea : registrosTareas) {
                    if((registroTarea.getFechaInicio() != null)&&(registroTarea.getFechaFin() != null)){
                        if((registroTarea.getFechaInicio().after(dateObj1)||registroTarea.getFechaInicio().equals(dateObj1))&&(registroTarea.getFechaFin().before(dateObj2)||registroTarea.getFechaInicio().equals(dateObj2)))
                            if(registroTarea.getJobTime() != null)
                                contador = contador + registroTarea.getJobTime();
                    }
                }
                tareaPP.setName(tarea.getName());
                tareaPP.setJobTime(contador);
                tareaPP.setCategory(tarea.getCategory());
                tareaPP.setStatus(tarea.getStatus());
                tareaPP.setExpectedTime(tarea.getExpectedTime());
                if(contador > 0)
                    tareasProyecto.add(tareaPP);
                contador=0;
            }
        }
            
        
        return tareasProyecto;
    }
    
    @Override
    public List<Suspension> suspensionesUsuario(String cc, String fechaInicio, String fechaFin){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if((fechaInicio == null) || (fechaFin == null))
            return null;
        Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Date dateObj2 = null;
            try {
                dateObj2 = sdf.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(DefaultServiceTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        Usuario usuarioAux = configuracionService.consultarUsuariobyCC(cc);
        List<Suspension> suspensionesUsuario = usuarioAux.getSuspensions();
        List<Suspension> suspensionesAux = new ArrayList<>();
        for (Suspension suspension : suspensionesUsuario) {
            if(!(suspension.getFechaFin()==null))
                if((suspension.getFechaInicio().after(dateObj1)||suspension.getFechaInicio().equals(dateObj1))&&(suspension.getFechaFin().before(dateObj2)||suspension.getFechaInicio().equals(dateObj2)))
                    suspensionesAux.add(suspension);
        }
        
        return suspensionesAux;
    }

}
