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

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "itemvalue")
public class ItemValue extends BaseEntity {

    @Column(name = "value", columnDefinition = "TEXT")
    String taskDescription;
    
    @Column(name = "project_id")
    Integer projectId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
    Project project;

    @Column(name = "task_id")
    Integer taskId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id", insertable = false, updatable = false)
    Tasks task;

    @Column(name = "item_id")
    Integer itemId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    ItemMaster item;

    @Column(name = "delflag", columnDefinition = "INT DEFAULT 0")
    Integer delflag;

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
}
