package org.ekal.ivd.entity;

import java.util.Optional;

import org.ekal.ivd.dto.ItemMasterDTO;

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

    @Column(name = "item_type", columnDefinition = "VARCHAR(50)")
    String itemType;

	public ItemMaster(ItemMasterDTO itemMaster) {

		Optional.ofNullable(itemMaster).ifPresent(i -> {
			this.itemName = i.getItemName();
			this.itemType = i.getItemType();
			
		});
	}
}
