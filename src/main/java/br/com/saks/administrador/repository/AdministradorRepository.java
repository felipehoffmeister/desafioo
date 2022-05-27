
package br.com.saks.administrador.repository;

import br.com.saks.administrador.model.Administrador;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

   
    
}
