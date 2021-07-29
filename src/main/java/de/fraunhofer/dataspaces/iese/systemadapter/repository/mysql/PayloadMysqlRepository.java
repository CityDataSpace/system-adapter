package de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Payload;

public interface PayloadMysqlRepository extends JpaRepository<Payload, Integer> {

}

