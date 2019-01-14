/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Repository.DescripcionRepository;
import utp.edu.co.Tiempos.Repository.ProyectoRepository;
import utp.edu.co.Tiempos.Repository.TareaRepository;
import utp.edu.co.Tiempos.Repository.UsuarioRepository;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
import utp.edu.co.Tiempos.Service.TimeService;

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

    public DefaultServiceTime(ConfiguracionService configuracionService, UsuarioRepository usuarioRepository, TareaRepository tareaRepository, ProyectoRepository proyectoRepository, DescripcionRepository descripcionRepository) {
        this.configuracionService = configuracionService;
        this.usuarioRepository = usuarioRepository;
        this.tareaRepository = tareaRepository;
        this.proyectoRepository = proyectoRepository;
        this.descripcionRepository = descripcionRepository;
    }
        
    @Override
    public Usuario iniciarSuspension(String id, Suspension suspension){
        Usuario usuarioToSus = configuracionService.consultarUsuariobyCC(id);
        if(usuarioToSus != null){
            List<Suspension> respuesta = new ArrayList<>();
            respuesta = usuarioToSus.getSuspensions();
            Date fechaInicio = new Date();
            suspension.setFechaInicio(fechaInicio);
            respuesta.add(suspension);
            usuarioToSus.setSuspensions(respuesta);
            usuarioToSus.setStatus("Suspension");
            usuarioRepository.save(usuarioToSus);
            return usuarioToSus;
        }
        return null;
    }
    
    @Override
    public Usuario finalizarSuspension(String id){
        Usuario usuarioFinSus = configuracionService.consultarUsuariobyCC(id);
        List<Suspension> respuesta = new ArrayList<>();
        respuesta = usuarioFinSus.getSuspensions();
        int aux = respuesta.size()-1;
        //guarda la fecha fin de la suspension
        Date fechaFin = new Date();
        respuesta.get(aux).setFechaFin(fechaFin);
        usuarioFinSus.setSuspensions(respuesta);
        long contador = respuesta.get(aux).getFechaFin().getTime()-respuesta.get(aux).getFechaInicio().getTime();
        contador = contador/1000;
        contador = contador/60;
        //guarda el tiempo que se tardo en la suspension
        respuesta.get(aux).setTiempoSuspension(contador);
        usuarioFinSus.setSuspensions(respuesta);
        long sumatoriaTiemposMeeting=0;
        long sumatoriaTiemposWC = 0;
        long sumatoriaTiemposSnack=0; 
        if(!(usuarioFinSus.getTiempoMeeting() == null))
            sumatoriaTiemposMeeting = usuarioFinSus.getTiempoMeeting();
        if(!(usuarioFinSus.getTiempoWC() == null))
            sumatoriaTiemposWC = usuarioFinSus.getTiempoWC();
        if(!(usuarioFinSus.getTiempoSnacks() == null))
            sumatoriaTiemposSnack = usuarioFinSus.getTiempoSnacks();
        if(respuesta.get(aux).getWcs() == 1)
            usuarioFinSus.setTiempoWC(sumatoriaTiemposWC + contador);
        if(respuesta.get(aux).getMeetings()== 1)
            usuarioFinSus.setTiempoMeeting(sumatoriaTiemposMeeting + contador);
        if(respuesta.get(aux).getSnacks() == 1)
            usuarioFinSus.setTiempoSnacks(sumatoriaTiemposSnack + contador);
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
        return contador;
    }

}
