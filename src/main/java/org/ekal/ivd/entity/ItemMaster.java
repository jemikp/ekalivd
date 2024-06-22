package org.ekal.ivd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ItemMasterDTO;

import java.util.Optional;
import java.util.Set;

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

	@Column(name = "item_desc", columnDefinition = "VARCHAR(100)")
    String itemDesc;

	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
	Set<ItemOption> itemOptions;

	public ItemMaster(ItemMasterDTO itemMaster) {

		Optional.ofNullable(itemMaster).ifPresent(i -> {
			this.itemName = i.getItemName();
			this.itemType = i.getItemType();
			this.itemDesc = i.getItemDesc();
		});
	}
}
