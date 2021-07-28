package de.fraunhofer.dataspaces.iese.systemadapter.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.dataspaces.iese.systemadapter.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
