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
@Table(name = "program_item")
public class ProgramItemMapping extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", referencedColumnName = "id", insertable = false, updatable = false)
    ProgramMaster program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    ItemMaster item;

    @Column(name = "displaysequence", columnDefinition = "INT DEFAULT 0")
    Integer display_sequence;
    
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
