/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Repository.Implementation;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import utp.edu.co.Tiempos.Documents.Tarea;
import utp.edu.co.Tiempos.Repository.TareaRepositoryCustom;
import utp.edu.co.Tiempos.dto.TareaCategoriaDTO;
import utp.edu.co.Tiempos.dto.TiempoTareaUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public class TareaRepositoryImpl implements TareaRepositoryCustom {
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public TareaRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
//     private UnwindOperation unwindOperationTiempoUsuarioSus(){
//        return unwind("$descripciones");
//    }
//    
//    private MatchOperation matchOperationTarea(String idTarea, String cc){
//        Criteria ccCriteria = where("id").is(idTarea).andOperator(where("descripciones.madeBy").is(cc));
//        return match(ccCriteria);
//    }
//      
//    private GroupOperation groupOperationUserTarea(){
//        return group("name")
//                .last("name").as("name")
//                .addToSet("descripciones.id").as("descripcionesId")
//                .sum("descripciones.jobTime").as("jobTimeUser");
//    }
//    
//    private ProjectionOperation projectOperationUserTarea(){
//        return project("descripcionesId", "jobTimeUser")
//                .and("name").previousOperation();
//    }
    
//  consulta para sacar el tiempo de una fecha a     
//    private UnwindOperation unwindOperationTiempoUsuarioSus(){
//        return unwind("$descripciones");
//    }
//    
//    private MatchOperation matchOperationTarea(Date fechaInicio, Date fechaFin){
//        Criteria ccCriteria = where("descripciones.fechaInicio").gte(fechaInicio).andOperator(where("descripciones.fechaFin").lte(fechaFin));
//        return match(ccCriteria);
//    }
//    
    private GroupOperation groupOperationCc(){
        return group("category")
                .last("category").as("category")
                .addToSet("id").as("tareasId")
                .sum("jobTime").as("jobTimeCategory");
    }
    
    private ProjectionOperation projectOperationTimeCategory(){
        return project("tareasId", "jobTimeCategory")
                .and("category").previousOperation();
    }

    @Override
    public List<TareaCategoriaDTO> consultarTiempoCategoria() {
        
        GroupOperation groupOperation = groupOperationCc();
        ProjectionOperation projectionOperation = projectOperationTimeCategory();
        
        return mongoTemplate.aggregate(Aggregation.newAggregation(
            groupOperation,
            projectionOperation
            ), Tarea.class, TareaCategoriaDTO.class ).getMappedResults();
    }

//    @Override
//    public List<TiempoTareaUsuarioDTO> consultarTiempoTareaUsuario(String idTarea, String cc) {
//        MatchOperation matchOperationT = matchOperationTarea(idTarea, cc);
//        UnwindOperation unwindoperationDes = unwindOperationTiempoUsuarioSus();
//        GroupOperation groupOperation = groupOperationUserTarea();
//        ProjectionOperation projectOperation = projectOperationUserTarea();
//        
//        return mongoTemplate.aggregate(Aggregation.newAggregation(
//            unwindoperationDes,
//            matchOperationT,
//            groupOperation,
//            projectOperation
//            ), Tarea.class, TiempoTareaUsuarioDTO.class ).getMappedResults();
//    }

}
    
    

