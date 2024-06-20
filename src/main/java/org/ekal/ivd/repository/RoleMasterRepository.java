package org.ekal.ivd.repository;

import org.ekal.ivd.entity.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer> {
	Optional<RoleMaster> getByRoleName(String roleName);
}
