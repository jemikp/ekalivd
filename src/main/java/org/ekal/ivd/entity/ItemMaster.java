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
@Table(name = "itemmaster")
public class ItemMaster extends BaseEntity {

    @Column(name = "item_name", columnDefinition = "VARCHAR(255)")
    String itemName;

    @Column(name = "item_type")
    Integer itemType;

    @Column(name = "delflag", columnDefinition = "INT DEFAULT 0")
    Integer delflag;
    
    @Column(name = "created_by", columnDefinition = "INT DEFAULT 0")
    Integer createdBy;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    User createdByUser;
}
