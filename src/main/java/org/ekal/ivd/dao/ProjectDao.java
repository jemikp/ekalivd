package org.ekal.ivd.dao;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ErrorResponseDTO;
import org.ekal.ivd.dto.PaginationDTO;
import org.ekal.ivd.dto.ProjectDTO;
import org.ekal.ivd.entity.Project;
import org.ekal.ivd.exception.DynamicException;
import org.ekal.ivd.repository.ProjectRepository;
import org.ekal.ivd.util.ErrorResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectDao {
    private final static Logger logger = LoggerFactory.getLogger(ProjectDao.class);

    @Autowired
    ProjectRepository projectRepository;

    public void createProject(@Valid ProjectDTO projectDTO) {
        Optional<Project> optionalProject = projectRepository.findByProjectNameAndIvdIdAndDelflag(projectDTO.getProjectName(),projectDTO.getIvdId(),0);
        if(optionalProject.isPresent()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.DUPLICATE_PROJECT), HttpStatus.BAD_REQUEST, projectDTO.getProjectName());
        }
        Project project = new Project(projectDTO);
        projectRepository.save(project);
        projectDTO.setId(project.getId());
    }

    public PaginationDTO<ProjectDTO> getAllProjects(int page, int size) {

        PaginationDTO<ProjectDTO> projectPage = null;
        Pageable paging = PageRequest.of(page, size);
        Page<Project> allProjects = projectRepository.findAll(paging);

        if (allProjects.hasContent()) {

            Page<ProjectDTO> projectDTOS = allProjects.map(p -> new ProjectDTO(p));

            projectPage = new PaginationDTO<ProjectDTO>(projectDTOS);
        }
        return projectPage;
    }
}