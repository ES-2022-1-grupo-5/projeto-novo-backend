package chegamais.com.chagamais.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chegamais.com.chagamais.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);
    
}
