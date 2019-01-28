/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import utp.edu.co.Tiempos.Documents.Descripcion;

/**
 *
 * @author C-Lug
 */
@Repository
public interface DescripcionRepository extends MongoRepository<Descripcion, String>, DescripcionRepositoryCustom{
    List<Descripcion> findByMadeBy(String cc);
}
