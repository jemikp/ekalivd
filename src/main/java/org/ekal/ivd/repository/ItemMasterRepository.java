package org.ekal.ivd.repository;

import java.util.List;
import java.util.Optional;

import org.ekal.ivd.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, Integer> {
	List<ItemMaster> findByItemName(String itemName);
	
	Optional<ItemMaster> findById(int id);
}
