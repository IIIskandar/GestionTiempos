/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Controller;

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
import org.springframework.web.bind.annotation.RestController;
import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
import utp.edu.co.Tiempos.Service.TimeService;
import utp.edu.co.Tiempos.dto.TareaCategoriaDTO;
import utp.edu.co.Tiempos.dto.TiempoTareaUsuarioDTO;
import utp.edu.co.Tiempos.dto.TiempoUsuarioDTO;

/**
 *
 * @author C-Lug
 */
@CrossOrigin
@RestController
@RequestMapping("tiempos/v1/tareas")
public class TareaController {
    
    private ConfiguracionService configuracionService;
    private TimeService timeService;

    public TareaController(ConfiguracionService configuracionService, TimeService timeService) {
        this.configuracionService = configuracionService;
        this.timeService = timeService;
    }
    
    //trae una tarea
    @GetMapping("/{id}")
    public ResponseEntity<?> getTarea(@PathVariable("id") String id) {
        Tarea tarea = configuracionService.consultarTarea(id);

        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarea);
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
    
    //inicia un registro en una tarea
    @PostMapping("{id}/registro/inicio/{status}")
    public ResponseEntity<?> iniciarRegistro(@PathVariable("id") String id, @PathVariable("status") String status, @RequestBody Descripcion descripcion){
        descripcion = timeService.iniciarRegistro(id, status,descripcion);
         if(descripcion == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(descripcion);
    }
    //finalizar el registro de una tarea
    @PostMapping("{id}/registro/fin/{status}")
    public ResponseEntity<?> finalizarRegistro(@PathVariable("id") String id ,@PathVariable("status") String status ,@RequestBody Descripcion descripcion){
        Descripcion descripcionHelper = timeService.finalizarRegistro(id,descripcion,status);
        return ResponseEntity.ok(descripcionHelper);
    }
    
    //trae una lista con las categorias de las tareas y el tiempo que se ha trabajado en cada categoria
    @GetMapping("/tiempoCategorias")
    public ResponseEntity<?> tiempoPorCategoria(){
        List<TareaCategoriaDTO> tiempoCategoria = timeService.tiempoPorCategoria();
        return ResponseEntity.ok(tiempoCategoria);
    }
    
    
    //trae el tiempo que ha trabajado un usuario en una tarea espefica
    @GetMapping("/tiempoUsuario/{id}/{cc}")
    public ResponseEntity<?> tiempoTareaUsuario(@PathVariable("id") String id ,@PathVariable("cc") String cc){
        TiempoTareaUsuarioDTO tiempo = timeService.tiempoUsuarioPorTarea(id, cc);
        if(tiempo.getDescripcionesId().isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(tiempo);
    }
}
