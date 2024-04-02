package org.ekal.ivd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "project")
public class Project extends BaseEntity {

    @Column(name = "project_name", columnDefinition = "varchar(255)")
    String projectName;

    @Column(name = "project_desc", columnDefinition = "text")
    String projectDescription;

    @Column(name = "status", columnDefinition = "INT DEFAULT 0")
    Integer projectStatus;

    @Column(name = "delflag", columnDefinition = "INT DEFAULT 0")
    Integer delflag;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;
    
    @Column(name = "ivd_id")
    Integer ivdId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ivd_id", referencedColumnName = "id", insertable = false, updatable = false)
    IVDMaster ivd;

    @Column(name = "coordinator")
    Integer coordinator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator", referencedColumnName = "id", insertable = false, updatable = false)
    User coordinatorUser;

    @Column(name = "sub_coordinator")
    Integer subCoordinator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_coordinator", referencedColumnName = "id", insertable = false, updatable = false)
    User subCoordinatorUser;

    @Column(name = "created_by")
    Integer createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    User createdByUser;

    @Column(name = "modified_by")
    Integer modifiedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by", referencedColumnName = "id", insertable = false, updatable = false)
    User modifiedByUser;

    public Project(ProjectDTO project) {
        Optional.ofNullable(project).ifPresent(p -> {
            this.id = p.getId();
            this.projectName = p.getProjectName();
            this.projectDescription = p.getProjectDescription();
            this.projectStatus = p.getProjectStatus();
            this.startDate = p.getStartDate();
            this.endDate = p.getEndDate();
            this.ivdId = p.getIvdId();
            this.coordinator = p.getCoordinatorUserId();
            this.subCoordinator = p.getSubCoordinatorUserId();
        });
    }
}
