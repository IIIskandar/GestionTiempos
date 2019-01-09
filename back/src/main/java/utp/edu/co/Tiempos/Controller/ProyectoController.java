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
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Proyecto> listaUsuarios = configuracionService.listaProyectos();
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUsuarios);
    }
    
    @PutMapping
    public ResponseEntity<?> insert(@RequestBody Proyecto proyecto){
        
        proyecto = configuracionService.guardarProyecto(proyecto);
        
        if(proyecto == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(proyecto);
    }
    
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
    public ResponseEntity<?> asignarUsuario(@PathVariable("id") String id){
        Proyecto proyectoHelper = configuracionService.consultarProyecto(id);
        Usuario usuarioHelper = configuracionService.consultarUsuariobyCC(cc);
        proyectoHelper = configuracionService.asignarUsuarioaProyecto(id, usuarioHelper.getId());
        if(proyectoHelper.getUsersId().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(proyectoHelper.getUsersId());
    }
}



