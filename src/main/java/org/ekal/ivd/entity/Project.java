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

import java.time.LocalDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ivd_id", referencedColumnName = "id", insertable = false, updatable = false)
    IVDMaster ivd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator", referencedColumnName = "id", insertable = false, updatable = false)
    User coordinator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_coordinator", referencedColumnName = "id", insertable = false, updatable = false)
    User subCoordinator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by", referencedColumnName = "id", insertable = false, updatable = false)
    User modifiedBy;
}
