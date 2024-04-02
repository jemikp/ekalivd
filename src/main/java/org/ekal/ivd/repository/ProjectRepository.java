package org.ekal.ivd.repository;

import org.ekal.ivd.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Optional<Project> findByProjectNameAndIvdIdAndDelflag(String projectName,int ivdId,int delflag);
}
