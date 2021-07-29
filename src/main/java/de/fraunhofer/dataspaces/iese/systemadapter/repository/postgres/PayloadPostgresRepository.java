package de.fraunhofer.dataspaces.iese.systemadapter.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.dataspaces.iese.systemadapter.model.postgres.Payload;

public interface PayloadPostgresRepository extends JpaRepository<Payload, Integer> {

}
