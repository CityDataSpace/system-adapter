package de.fraunhofer.dataspaces.iese.systemadapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.fraunhofer.dataspaces.iese.systemadapter.entity.Json;

public interface JsonRepository extends JpaRepository<Json, Integer> {

}
