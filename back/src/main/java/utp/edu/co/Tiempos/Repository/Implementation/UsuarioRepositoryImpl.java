/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Repository.Implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Documents.Usuario;
import utp.edu.co.Tiempos.Repository.UsuarioRepositoryCustom;
import utp.edu.co.Tiempos.dto.TiempoSuspensionTipoDTO;

/**
 *
 * @author C-Lug
 */
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom{
      private final MongoTemplate mongoTemplate;
    
    @Autowired
    public UsuarioRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    private UnwindOperation unwindOperationSus(){
        return unwind("suspensions");
    }
    
    private UnwindOperation unwindOperationTiempoUsuarioSus(){
        return unwind("tiempoSuspensiones");
    }
        
    private GroupOperation groupOperationTiempoUsuarioSus(){
        return group("cc")
                .last("cc").as("cedula")
                .sum("tiempoSuspensiones.acumulado").as("jobTimeUserSuspension");
    }
    
    
    private GroupOperation groupOperationCc(){
        return group("suspensions.tipoSuspension")
                .last("suspensions.tipoSuspension").as("tipo")
                .sum("suspensions.tiempoSuspension").as("jobTimeSuspension");
    }
    
    private ProjectionOperation projectOperationTimeUsersSuspension(){
        return project("jobTimeUserSuspension")
                .and("cedula").previousOperation();
    }
    
    private ProjectionOperation projectOperationTypeSuspensions(){
        return project("jobTimeSuspension")
                .and("tipo").previousOperation();
    }

    @Override
    public List<TiempoSuspensionTipoDTO> tiempoPorTipoSuspension() {
        GroupOperation groupOperation = groupOperationCc();
        ProjectionOperation projectionOperation = projectOperationTypeSuspensions();
        UnwindOperation unwindOperation = unwindOperationSus();
        
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                unwindOperation,
                groupOperation,
                projectionOperation
                ), Usuario.class, TiempoSuspensionTipoDTO.class ).getMappedResults();
    }
        
}
