// ReciboRepository.java
package com.ecopredict.repository;

import com.ecopredict.model.ReciboCFE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReciboRepository extends JpaRepository<ReciboCFE, Long> {
    List<ReciboCFE> findByIdUsuarioOrderByFechaRegistroAsc(Long idUsuario);
}