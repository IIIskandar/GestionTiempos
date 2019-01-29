/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.repository1.Implementation;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import utp.edu.co.tiempos1.documents1.Suspension;
import utp.edu.co.tiempos1.documents1.Usuario;
import utp.edu.co.tiempos1.repository1.UsuarioRepositoryCustom;
import utp.edu.co.tiempos1.dto.TiempoSuspensionTipoDTO;

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
    
    private MatchOperation matchOperationFechas(Date fechaInicio, Date fechaFin){
        Criteria ccCriteria = where("suspensions.fechaInicio").gte(fechaInicio).andOperator(where("suspensions.fechaFin").lte(fechaFin));
        return match(ccCriteria);   
        
    }   
    
    private GroupOperation groupOperationCc(){
        return group("suspensions.tipoSuspension")
                .last("suspensions.tipoSuspension").as("tipo")
                .sum("suspensions.tiempoSuspension").as("jobTimeSuspension");
    }
 
    private ProjectionOperation projectOperationTypeSuspensions(){
        return project("jobTimeSuspension")
                .and("tipo").previousOperation();
    }

    @Override
    public List<TiempoSuspensionTipoDTO> tiempoPorTipoSuspension(Date fechaInicio,Date fechaFin) {
        MatchOperation matchOperation = matchOperationFechas(fechaInicio, fechaFin);
        GroupOperation groupOperation = groupOperationCc();
        ProjectionOperation projectionOperation = projectOperationTypeSuspensions();
        UnwindOperation unwindOperation = unwindOperationSus();
        
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                unwindOperation,
                matchOperation,
                groupOperation,
                projectionOperation
                ), Usuario.class, TiempoSuspensionTipoDTO.class ).getMappedResults();
    }
        
}
