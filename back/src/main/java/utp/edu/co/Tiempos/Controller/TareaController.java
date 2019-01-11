/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
import utp.edu.co.Tiempos.Service.TimeService;

/**
 *
 * @author C-Lug
 */
@RestController
@RequestMapping("tiempos/v1/tareas")
public class TareaController {
    
    private ConfiguracionService configuracionService;
    private TimeService timeService;

    public TareaController(ConfiguracionService configuracionService, TimeService timeService) {
        this.configuracionService = configuracionService;
        this.timeService = timeService;
    }
    
    
    
    //borra una tarea, con su id
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable("id") String id){
        Tarea tarea = configuracionService.eliminarTarea(id);
        if(tarea == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(tarea);
    }
    
    //iniciar un registro
    @PutMapping("{id}/registro/inicio")
    public ResponseEntity<?> iniciarRegistro(@PathVariable("id") String id, @RequestBody Descripcion descripcion){
        descripcion = timeService.iniciarRegistro(id, descripcion);
         if(descripcion == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(descripcion);
    }
    //finalizar el registro de una tarea
    @PostMapping("{id}/registro/fin")
    public ResponseEntity<?> finalizarRegistro(@PathVariable("id") String id, @RequestBody Descripcion descripcion){
        Descripcion descripcionHelper = timeService.finalizarRegistro(id,descripcion);
        return ResponseEntity.ok(descripcionHelper);
    }
}
