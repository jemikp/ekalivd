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
@Table(name = "tasks")
public class Tasks extends BaseEntity {

    @Column(name = "task_name", columnDefinition = "VARCHAR(255)")
    String taskName;

    @Column(name = "task_description", columnDefinition = "TEXT")
    String taskDescription;

    @Column(name = "start_date")
    LocalDate startDate;
    
    @Column(name = "status", columnDefinition = "INT DEFAULT 0")
    Integer taskStatus;

    @Column(name = "delflag", columnDefinition = "INT DEFAULT 0")
    Integer delflag;

    @Column(name = "end_date")
    LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
    Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", referencedColumnName = "id", insertable = false, updatable = false)
    ProgramMaster program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_to", referencedColumnName = "id", insertable = false, updatable = false)
    User assignedto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by", referencedColumnName = "id", insertable = false, updatable = false)
    User modifiedBy;
}
    

