package org.ekal.ivd.repository;

import org.ekal.ivd.entity.IVDMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVDMasterRepository extends JpaRepository<IVDMaster, Integer> {
	Optional<IVDMaster> findByName(String name);
}
