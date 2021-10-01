package de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.Role;

public interface RoleMysqlRepository extends JpaRepository<Role, Integer> {
	
	List<Role> findByUserId(int id);
}
