package de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Registration;

public interface RegistrationMysqlRepository extends JpaRepository<Registration, Integer> {

	List<Registration> findByUserId(int id);
}
