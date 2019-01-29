/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.tiempos.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import utp.edu.co.tiempos.documents.Usuario;

/**
 *
 * @author C-Lug
 */
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>, UsuarioRepositoryCustom{
    Optional<Usuario> findByCc(String cc);
}
