/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Controller;

import java.util.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.co.Tiempos.Documents.TipoSuspensiones;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
import utp.edu.co.Tiempos.Service.TimeService;
import utp.edu.co.Tiempos.dto.TiempoSuspensionTipoDTO;
import utp.edu.co.Tiempos.dto.TipoSuspensionesDTO;

/**
 *
 * @author C-Lug
 */
@CrossOrigin
@RestController
@RequestMapping("tiempos/v1/suspension")
public class SuspensionController {
    private ConfiguracionService configuracionService;
    private TimeService timeService;

    public SuspensionController(ConfiguracionService configuracionService, TimeService timeService) {
        this.configuracionService = configuracionService;
        this.timeService = timeService;
    }
    
    
    //crea una suspension
    @PostMapping("/crear")
    public ResponseEntity<?> crearSuspension(@RequestBody TipoSuspensionesDTO tipo){
        configuracionService.crearTipoSuspension(tipo);
        return ResponseEntity.ok(tipo);
    }
    
    //lista las suspensiones que creo el admin
    @GetMapping("/listar")
    public ResponseEntity<?> listarSuspeniones(){
        List<TipoSuspensiones> lista = configuracionService.listaTipoSuspensiones();
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }
    
    
    //borra una suspension
    @DeleteMapping("/eliminar/{nombre}")
    public ResponseEntity<?> delete(@PathVariable("nombre") String nombre){
        TipoSuspensionesDTO auxiliar = configuracionService.eliminarTipoSuspension(nombre);
        if(auxiliar == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(auxiliar);
    }
    
    //carga una lista con el tiempo de las suspensiones usadas
    @GetMapping("/tiempo")
    public ResponseEntity<?> tiempoPorSuspension(String fechaInicio, String fechaFin){
        List<TiempoSuspensionTipoDTO> tiempoSuspension = timeService.tiempoPorTipoSus(fechaInicio, fechaFin);
        if(tiempoSuspension == null)
            return ResponseEntity.badRequest().build();
        if(tiempoSuspension.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tiempoSuspension);
    }
}
