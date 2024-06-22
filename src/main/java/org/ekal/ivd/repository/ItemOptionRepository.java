package org.ekal.ivd.repository;

import org.ekal.ivd.entity.ItemMaster;
import org.ekal.ivd.entity.ItemOption;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemOptionRepository extends JpaRepository<ItemOption, Integer> {
    Set<ItemOption> findByDelflagAndItem(int delflag, ItemMaster item, Sort sort);
}
