/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Service.ConfiguracionService;
/**
 *
 * @author Sebastian Velez
 */
@RestController
@RequestMapping("/tiempos/v1/suspension")
public class SuspensionController {
    private ConfiguracionService configuracionService;

    public SuspensionController(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }
    
    @PutMapping
    public ResponseEntity<?> insert(@RequestBody String id, Suspension suspension){
        return null;
    }
    
}
