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
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Repository.UsuarioRepository;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
import utp.edu.co.Tiempos.Service.TimeService;

/**
 *
 * @author C-Lug
 */
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
    
    @PutMapping
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
    
    @PostMapping("/suspension/iniciar/{id}")
    public ResponseEntity<?> iniciarSuspension(@PathVariable String id,@RequestBody Suspension suspension){
        timeService.iniciarSuspension(id, suspension);
        return ResponseEntity.ok(suspension);
    }
    
    @PostMapping("/suspension/fin/{id}")
    public ResponseEntity<?> finalizarSuspension(@PathVariable String id){
        Usuario usuarioHelper = timeService.finalizarSuspension(id);
        return ResponseEntity.ok(usuarioHelper);
    }
}
