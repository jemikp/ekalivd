package org.ekal.ivd.repository;

import org.ekal.ivd.entity.ProgramMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramMasterRepository extends JpaRepository<ProgramMaster, Integer> {
	Optional<ProgramMaster> findByName(String name);
}
