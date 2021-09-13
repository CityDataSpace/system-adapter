package de.fraunhofer.dataspaces.iese.systemadapter.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.dataspaces.iese.systemadapter.model.mysql.User;

public interface UserMysqlRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
