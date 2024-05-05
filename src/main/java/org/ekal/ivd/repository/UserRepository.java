package org.ekal.ivd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.ekal.ivd.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByIdOrMobile(int id,String whatsApp);

	Optional<User> getByMobile(String whatsApp);
}
