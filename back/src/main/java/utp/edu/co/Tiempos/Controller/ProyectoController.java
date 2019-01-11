/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Service.ConfiguracionService;

/**
 *
 * @author C-Lug
 */
@RestController
@RequestMapping("/tiempos/v1/proyectos")
public class ProyectoController {
    
    private ConfiguracionService configuracionService;

    public ProyectoController(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }
    
    //lista los proyectos
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Proyecto> listaUsuarios = configuracionService.listaProyectos();
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUsuarios);
    }
    
    //crea un proyecto
    @PutMapping
    public ResponseEntity<?> insert(@RequestBody Proyecto proyecto){
        
        proyecto = configuracionService.guardarProyecto(proyecto);
        
        if(proyecto == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(proyecto);
    }
    
    //borra un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        Proyecto proyecto = configuracionService.eliminarProyecto(id);
        if(proyecto == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(proyecto);
    }
    
    //asigna un usuario al proyecto en el q se encuentra, para asignar el usuario deberia intrudcir la cc del usuario que quiere agregar
    //en un formulario y de ese formulario tenemos que sacar esa cedula para buscar al usuario por su cc
    @PostMapping("/asignarUsuario/{id}")
    public ResponseEntity<?> asignarUsuario(@PathVariable("id") String id,@RequestBody Usuario usuario){
        Proyecto proyectoHelper = configuracionService.consultarProyecto(id);
        Usuario usuarioHelper = configuracionService.consultarUsuariobyCC(usuario.getCc());
        proyectoHelper = configuracionService.asignarUsuarioaProyecto(id, usuarioHelper.getId());
        if(proyectoHelper.getUsersId().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(proyectoHelper.getUsersId());
    }
    
    //consulta las tareas que tiene un proyecto
    @GetMapping("/{id}/tareas")
    public ResponseEntity<?> getAllTareas(@PathVariable("id") String id){
        List<Tarea> listaTareas = configuracionService.listaTareas(id);
        if (listaTareas == null || listaTareas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaTareas);
    }
    
    //crea una tarea dentro de un proyecto, el id es del proyecto en el que se va a crear la tarea
    @PutMapping("/{id}/tarea")
    public ResponseEntity<?> createTarea(@PathVariable("id") String id,@RequestBody Tarea tarea){
        
        tarea = configuracionService.guardarTarea(id,tarea);
        
        if(tarea == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(tarea);
    }
    
    
}



