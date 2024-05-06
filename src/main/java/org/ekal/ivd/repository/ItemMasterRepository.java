package org.ekal.ivd.repository;

import org.ekal.ivd.entity.ItemMaster;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, Integer> {
	List<ItemMaster> findByItemName(String itemName);
	
	Optional<ItemMaster> findById(int id);

	List<ItemMaster> findByDelflag(int delflag, Sort sort);
}
