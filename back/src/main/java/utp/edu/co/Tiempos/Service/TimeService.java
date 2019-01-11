/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Service;

import utp.edu.co.Tiempos.Documents.Descripcion;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Documents.Usuario;

/**
 *
 * @author C-Lug
 */
public interface TimeService {
    
    Usuario iniciarSuspension(String id, Suspension suspension);
    
    Usuario finalizarSuspension(String id);
    
    Descripcion iniciarRegistro(String id, Descripcion descripcion);
    
    Descripcion finalizarRegistro(String id, Descripcion descripcion);
}
