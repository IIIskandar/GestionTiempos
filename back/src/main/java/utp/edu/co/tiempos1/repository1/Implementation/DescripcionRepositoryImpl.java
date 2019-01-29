/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos1.repository1.Implementation;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import utp.edu.co.tiempos1.documents1.Descripcion;
import utp.edu.co.tiempos1.repository1.DescripcionRepositoryCustom;
import utp.edu.co.tiempos1.dto.TiempoUsuarioDTO;

/**
 *
 * @author C-Lug
 */
public class DescripcionRepositoryImpl implements DescripcionRepositoryCustom{

    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public DescripcionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    private MatchOperation matchOperationCc(String cc){
        Criteria ccCriteria = where("madeBy").is(cc);
        return match(ccCriteria);
    }
    
    private GroupOperation groupOperationCc(){
        return group("madeBy")
                .last("madeBy").as("madeBy")
                .addToSet("id").as("descripcionId")
                .sum("jobTime").as("jobTimeUser");
    }
    
    private ProjectionOperation projectOperationTimeUsers(){
        return project("descripcionId", "jobTimeUser")
                .and("madeBy").previousOperation();
    }
    
    //filtra un usuario y saca su tiempo trabajado y los id de los registros en los que trabajó
    @Override
    public List<TiempoUsuarioDTO> consultarTiempoUsuario(String cc) {
        MatchOperation matchOperation = matchOperationCc(cc);
        GroupOperation groupOperation = groupOperationCc();
        ProjectionOperation projectionOperation = projectOperationTimeUsers();
        
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation
                ), Descripcion.class, TiempoUsuarioDTO.class ).getMappedResults();
    }

    //consulta y agrupa los tiempos de los usuarios por cedula
    @Override
    public List<TiempoUsuarioDTO> consultasTiemposAllUsers() {
        GroupOperation groupOperation = groupOperationCc();
        ProjectionOperation projectionOperation = projectOperationTimeUsers();
        
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                groupOperation,
                projectionOperation
                ), Descripcion.class, TiempoUsuarioDTO.class ).getMappedResults();
    }
    
}
