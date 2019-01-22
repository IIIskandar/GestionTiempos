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
import utp.edu.co.Tiempos.Documents.Proyecto;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Documents.TipoSuspensiones;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Repository.UsuarioRepository;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
import utp.edu.co.Tiempos.Service.TimeService;
import utp.edu.co.Tiempos.dto.SuspensionDTO;
import utp.edu.co.Tiempos.dto.TiempoUsuarioDTO;
import utp.edu.co.Tiempos.dto.TipoSuspensionesDTO;

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
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Usuario> listaUsuarios = configuracionService.listaUsuarios();
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUsuarios);
    }
    
    @GetMapping("/{cc}")
    public ResponseEntity<?> getUsuario(@PathVariable("cc") String cc) {
        Usuario usuario = configuracionService.consultarUsuariobyCC(cc);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Usuario usuario){
        
        usuario = configuracionService.guardarUsuario(usuario);
        
        if(usuario == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        Usuario usuario = configuracionService.eliminarUsuario(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(usuario);
    }
    
    
    @PostMapping("/suspension/iniciar/{cc}")
    public ResponseEntity<?> iniciarSuspension(@PathVariable("cc") String cc,@RequestBody SuspensionDTO suspension){
        timeService.iniciarSuspension(cc, suspension);
        return ResponseEntity.ok(suspension);
        
    }
    
    @PostMapping("/suspension/fin/{cc}")
    public ResponseEntity<?> finalizarSuspension(@PathVariable("cc") String cc){
        Usuario usuarioHelper = timeService.finalizarSuspension(cc);
        return ResponseEntity.ok(usuarioHelper);
    }
    
    @GetMapping("/{cc}/proyectos")
    public ResponseEntity<?> listarProyectosUsuario(@PathVariable("cc") String cc){
        List<Proyecto> proyectosUser = configuracionService.consultarProyectosUsuario(cc);
        return ResponseEntity.ok(proyectosUser);
    }
    
    @GetMapping("/tiempo/{cc}")
    public ResponseEntity<?> tiempoUsuario(@PathVariable("cc") String cc){
        TiempoUsuarioDTO tiempoUsuario = timeService.tiempoUsuarios(cc);
        return ResponseEntity.ok(tiempoUsuario);
    }
    
    @GetMapping("/tiempoSuspensiones/{cc}")
    public ResponseEntity<?> tiempoSuspensionUsuario(@PathVariable("cc") String cc){
        Long tiempoSuspensionUsuario = timeService.TiempoSuspensionUsuarioTotal(cc);
        return ResponseEntity.ok(tiempoSuspensionUsuario);
    }
    
}
