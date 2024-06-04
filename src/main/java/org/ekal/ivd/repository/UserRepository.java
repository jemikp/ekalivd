package org.ekal.ivd.repository;

import org.ekal.ivd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByIdOrMobile(int id,String whatsApp);

	Optional<User> getByMobile(String whatsApp);

	Optional<User> getByEmailAndPassword(String email,String password);
}
