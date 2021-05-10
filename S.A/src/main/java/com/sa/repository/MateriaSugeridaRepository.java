package com.sa.repository;

import com.sa.model.MateriaSugerida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaSugeridaRepository extends JpaRepository<MateriaSugerida,Long> {

}
