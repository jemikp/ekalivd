package org.ekal.ivd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "itemoption")
public class ItemOption extends BaseEntity {

    @Column(name = "option_name", columnDefinition = "VARCHAR(50)")
    String optionName;

    @ManyToOne
    @JoinColumn(name = "item_id")
    ItemMaster item;
}
