/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.co.tiempos.documents.Proyecto;
import utp.edu.co.tiempos.documents.Suspension;
import utp.edu.co.tiempos.documents.Tarea;
import utp.edu.co.tiempos.documents.TipoSuspensiones;
import utp.edu.co.tiempos.documents.Usuario;
import utp.edu.co.tiempos.repository.UsuarioRepository;
import utp.edu.co.tiempos.service.ConfiguracionService;
import utp.edu.co.tiempos.service.TimeService;
import utp.edu.co.tiempos.dto.ProyectoTareaUsuarioDTO;
import utp.edu.co.tiempos.dto.SuspensionDTO;
import utp.edu.co.tiempos.dto.TiempoProyectosDTO;
import utp.edu.co.tiempos.dto.TiempoUsuarioDTO;
import utp.edu.co.tiempos.dto.TipoSuspensionesDTO;

/**
 *
 * @author C-Lug
 */
@CrossOrigin
@RestController
@RequestMapping("/tiempos/v1/usuarios")
public class UsuarioController {
    private ConfiguracionService configuracionService;
    private TimeService timeService;

    public UsuarioController(ConfiguracionService configuracionService, TimeService timeService) {
        this.configuracionService = configuracionService;
        this.timeService = timeService;
    }
    
    //trae todos los usuarios
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Usuario> listaUsuarios = configuracionService.listaUsuarios();
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUsuarios);
    }
    
    //trae un usuario por su cedula
    @GetMapping("/{cc}")
    public ResponseEntity<?> getUsuario(@PathVariable("cc") String cc) {
        Usuario usuario = configuracionService.consultarUsuariobyCC(cc);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }
    
    //inserta un usuario
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Usuario usuario){
        
        usuario = configuracionService.guardarUsuario(usuario);
        
        if(usuario == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(usuario);
    }
    
    //borra un usuario por su cedula
    @DeleteMapping("/{cc}")
    public ResponseEntity<?> delete(@PathVariable("cc") String cc){
        String usuario = configuracionService.eliminarUsuario(cc);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        if(usuario.contains("borrar"))
            return ResponseEntity.ok(0);
        return ResponseEntity.ok(1);
    }
    
    //inicia una suspension 
    @PostMapping("/suspension/iniciar/{cc}")
    public ResponseEntity<?> iniciarSuspension(@PathVariable("cc") String cc,@RequestBody SuspensionDTO suspension){
        timeService.iniciarSuspension(cc, suspension);
        return ResponseEntity.ok(suspension);
        
    }
    
    //finaliza la suspension
    @PostMapping("/suspension/fin/{cc}")
    public ResponseEntity<?> finalizarSuspension(@PathVariable("cc") String cc){
        Usuario usuarioHelper = timeService.finalizarSuspension(cc);
        return ResponseEntity.ok(usuarioHelper);
    }
    
    //trae todos los proyectos en los que ha trabajado el usuario
    @GetMapping("/{cc}/proyectos")
    public ResponseEntity<?> listarProyectosUsuario(@PathVariable("cc") String cc){
        List<Proyecto> proyectosUser = configuracionService.consultarProyectosUsuario(cc);
        return ResponseEntity.ok(proyectosUser);
    }
    
    //me trae todo el tiempo trabajado de un usuario
    @GetMapping("/tiempo/{cc}")
    public ResponseEntity<?> tiempoUsuario(@PathVariable("cc") String cc){
        TiempoUsuarioDTO tiempoUsuario = timeService.tiempoUsuarios(cc);
        return ResponseEntity.ok(tiempoUsuario);
    }
    
    //me trae todo el tiempo trabajado de todos los usuarios
    @GetMapping("/tiempoAll")
    public ResponseEntity<?> tiempoAllUsers(){
        List<TiempoUsuarioDTO> tiempoUsuario = timeService.tiempoAllUsers();
        return ResponseEntity.ok(tiempoUsuario);
    }
    
    //me trae el tiempo de las suspensiones de un usuario
    @GetMapping("/tiempoSuspensiones/{cc}")
    public ResponseEntity<?> tiempoSuspensionUsuario(@PathVariable("cc") String cc){
        Long tiempoSuspensionUsuario = timeService.TiempoSuspensionUsuarioTotal(cc);
        return ResponseEntity.ok(tiempoSuspensionUsuario);
    }
    
    //me trae las tareas realizadas por un usuario totales
    @GetMapping("tiempoTarea/{cc}")
    public ResponseEntity<?> tareasPorUsuario(@PathVariable("cc") String cc){
        List<ProyectoTareaUsuarioDTO> tareasUsuario= timeService.tareasRealizadosPorUsuario(cc);
        if(tareasUsuario.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tareasUsuario);
    }
    
    //me trae las tareas realizadas por un usuario y su tiempo trabajado
    @GetMapping("tiempoTareaFecha/{cc}")
    public ResponseEntity<?> tareasPorUsuarioFecha(@PathVariable("cc") String cc,@RequestParam("fechaInicio") String fechaInicio,@RequestParam("fechaFin") String fechaFin){
        List<ProyectoTareaUsuarioDTO> tareasUsuario= timeService.tareasRealizadosPorUsuarioFecha(cc,fechaInicio,fechaFin);
        if(tareasUsuario.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tareasUsuario);
    }
    
    //trae los proyectos realizados por un usuario y su tiempo trabajado
    @GetMapping("tiempoProyecto/{cc}")
    public ResponseEntity<?> proyectosPorUsuario(@PathVariable("cc") String cc, @RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin){
        List<TiempoProyectosDTO> tareasUsuario= timeService.proyectosRealizadosPorUsuario(cc,fechaInicio,fechaFin);
        if(tareasUsuario.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tareasUsuario);
    }
    
    @GetMapping("suspensionesDetalles/{cc}")
    public ResponseEntity<?> suspensionesPorUsuario(@PathVariable("cc") String cc, @RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin){
        List<Suspension> suspensionesUsuario = timeService.suspensionesUsuario(cc, fechaInicio, fechaFin);
        if(suspensionesUsuario.isEmpty())
            return ResponseEntity.noContent().build();
        if(suspensionesUsuario == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(suspensionesUsuario);
    }
    
}
