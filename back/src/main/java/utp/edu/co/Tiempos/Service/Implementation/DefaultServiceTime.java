/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Documents.TiempoSuspensiones;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Repository.DescripcionRepository;
import utp.edu.co.Tiempos.Repository.ProyectoRepository;
import utp.edu.co.Tiempos.Repository.TareaRepository;
import utp.edu.co.Tiempos.Repository.UsuarioRepository;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
import utp.edu.co.Tiempos.Service.TimeService;
import utp.edu.co.Tiempos.dto.SuspensionDTO;
import utp.edu.co.Tiempos.dto.TiempoUsuarioDTO;

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
                usuarioToSus.setStatus("Suspension");
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

    @Override
    public TiempoUsuarioDTO tiempoUsuarios(String cc) {
        List<TiempoUsuarioDTO> tiempos = descripcionRepository.consultarTiempoUsuario(cc);
        return tiempos.get(0);
    }

}
