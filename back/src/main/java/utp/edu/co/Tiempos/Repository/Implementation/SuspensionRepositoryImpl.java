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
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import utp.edu.co.Tiempos.Documents.Suspension;
import utp.edu.co.Tiempos.Repository.SuspensionRepositoryCustom;
import utp.edu.co.Tiempos.dto.TiempoSuspensionTipoDTO;

/**
 *
 * @author C-Lug
 */
public class SuspensionRepositoryImpl implements SuspensionRepositoryCustom{
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public SuspensionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    
    private GroupOperation groupOperationCc(){
        return group("tipoSuspension")
                .last("tipoSuspension").as("tipo")
                .sum("tiempoSuspension").as("jobTimeSuspension");
    }
    
    private ProjectionOperation projectOperationTimeUsers(){
        return project("jobTimeSuspension")
                .and("tipoSuspension").previousOperation();
    }

        
}
