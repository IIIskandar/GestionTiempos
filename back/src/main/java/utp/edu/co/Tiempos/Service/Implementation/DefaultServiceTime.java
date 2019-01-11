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
        Usuario usuarioToSus = configuracionService.consultarUsuario(id);
        if(usuarioToSus != null){
            List<Suspension> respuesta = new ArrayList<>();
            respuesta = usuarioToSus.getSuspensions();
            Date fechaInicio = new Date();
            suspension.setFechaInicio(fechaInicio);
            respuesta.add(suspension);
            usuarioToSus.setSuspensions(respuesta);
            usuarioRepository.save(usuarioToSus);
            return usuarioToSus;
        }
        return null;
    }
    
    @Override
    public Usuario finalizarSuspension(String id){
        Usuario usuarioFinSus = configuracionService.consultarUsuario(id);
        List<Suspension> respuesta = new ArrayList<>();
        respuesta = usuarioFinSus.getSuspensions();
        int aux = respuesta.size()-1;
        Date fechaFin = new Date();
        respuesta.get(aux).setFechaFin(fechaFin);
        usuarioFinSus.setSuspensions(respuesta);
        long contador = respuesta.get(aux).getFechaFin().getTime()-respuesta.get(aux).getFechaInicio().getTime();
        contador = contador/1000;
        contador = contador/60;
        long sumatoriaTiemposMeeting=0;
        long sumatoriaTiemposWC = 0;
        long sumatoriaTiemposSnack=0; 
        if(!(usuarioFinSus.getTiempoMeeting() == null))
            sumatoriaTiemposMeeting = usuarioFinSus.getTiempoMeeting();
        if(!(usuarioFinSus.getTiempoWC() == null))
            sumatoriaTiemposWC = usuarioFinSus.getTiempoWC();
        if(!(usuarioFinSus.getTiempoSnacks() == null))
            sumatoriaTiemposSnack = usuarioFinSus.getTiempoSnacks();
        if(respuesta.get(aux).getWCs() == 1)
            usuarioFinSus.setTiempoWC(sumatoriaTiemposWC + contador);
        if(respuesta.get(aux).getMeetings()== 1)
            usuarioFinSus.setTiempoMeeting(sumatoriaTiemposMeeting + contador);
        if(respuesta.get(aux).getSnacks() == 1)
            usuarioFinSus.setTiempoSnacks(sumatoriaTiemposSnack + contador);
        usuarioRepository.save(usuarioFinSus);
        return usuarioFinSus;
    }

    @Override
    public Descripcion iniciarRegistro(String id, Descripcion descripcion) {
        Tarea tareaHelper = configuracionService.consultarTarea(id);
        List<Descripcion> registros = new ArrayList<>();
        registros = tareaHelper.getDescripciones();
        Date fechaInicio = new Date();
        descripcion.setFechaInicio(fechaInicio);
        Descripcion representativo = descripcionRepository.insert(descripcion);
        registros.add(descripcion);
        tareaHelper.setDescripciones(registros);
        tareaRepository.save(tareaHelper);
        return representativo;
    }

    @Override
    public Descripcion finalizarRegistro(String id, Descripcion descripcion) {
        long auxCont=0;
        Tarea tareaHelper = configuracionService.consultarTarea(id);
        List<Descripcion> respuesta = new ArrayList<>();
        respuesta = tareaHelper.getDescripciones();
        int aux = respuesta.size()-1;
        Date fechaFin = new Date();
        Descripcion descripcionHelper = configuracionService.consultarRegistro(respuesta.get(aux).getId());
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
        return descripcionHelper;
    }

}
