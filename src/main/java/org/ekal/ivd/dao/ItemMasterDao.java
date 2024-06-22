package org.ekal.ivd.dao;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ErrorResponseDTO;
import org.ekal.ivd.dto.ItemMasterDTO;
import org.ekal.ivd.entity.ItemMaster;
import org.ekal.ivd.entity.ItemOption;
import org.ekal.ivd.exception.DynamicException;
import org.ekal.ivd.repository.ItemMasterRepository;
import org.ekal.ivd.repository.ItemOptionRepository;
import org.ekal.ivd.util.ErrorResponseCode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemMasterDao {
    private final static Logger logger = LoggerFactory.getLogger(ItemMasterDao.class);

    @Autowired
    ItemMasterRepository itemMasterRepository;

    @Autowired
    ItemOptionRepository itemOptionRepository;

    @Transactional
    public void createItemMaster(@Valid ItemMasterDTO itemMasterDTO) {
        List<ItemMaster> optionalItemMaster = itemMasterRepository.findByItemName(itemMasterDTO.getItemName());
        if (!optionalItemMaster.isEmpty()) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_EXIST), HttpStatus.BAD_REQUEST, itemMasterDTO.getItemName());
        }
        ItemMaster master = new ItemMaster(itemMasterDTO);
        ItemMaster savedItem = itemMasterRepository.save(master);

        List<ItemOption> itemOptions = itemMasterDTO.getItemOptions()
                .stream()
                .map(itemOptDto -> {
                    ItemOption itemOption = new ItemOption();
                    itemOption.setOptionName(itemOptDto.getOptionName());
                    itemOption.setItem(savedItem);
                    return itemOption;
                }).toList();
        itemOptionRepository.saveAll(itemOptions);

        itemMasterDTO.setId(master.getId());
    }

    public List<ItemMasterDTO> getItem() {
        List<ItemMaster> itemMasters = itemMasterRepository.findByDelflag(0, Sort.by(Sort.Direction.DESC, "id"));
        itemMasters.forEach(item -> {
            item.setItemOptions(itemOptionRepository.findByDelflagAndItem(0, item, Sort.by(Sort.Direction.DESC, "id")));
        });
        return itemMasters.stream().map(i -> new ItemMasterDTO(i)).collect(Collectors.toList());
    }

    public ItemMasterDTO getItemById(int itemId) {
        Optional<ItemMaster> itemMaster = itemMasterRepository.findById(itemId);
        if (itemMaster.isEmpty()) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_NOT_EXIST), HttpStatus.BAD_REQUEST, itemId + "");
        }
        itemMaster.ifPresent(item -> {
            item.setItemOptions(itemOptionRepository.findByDelflagAndItem(0, item, Sort.by(Sort.Direction.DESC, "id")));
        });
        return new ItemMasterDTO(itemMaster.get());
    }

    public void updateItemById(@Valid ItemMasterDTO itemMasterDTO, int itemId) {
        Optional<ItemMaster> itemMasterOpt = itemMasterRepository.findById(itemId);
        if (itemMasterOpt.isEmpty()) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_NOT_EXIST), HttpStatus.BAD_REQUEST, itemId + "");
        }
        List<ItemMaster> itemMasterList = itemMasterRepository.findByItemName(itemMasterDTO.getItemName());
        if (itemMasterList.size() > 1 && !itemMasterList.get(0).getId().equals(itemId)) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_EXIST), HttpStatus.BAD_REQUEST, itemMasterDTO.getItemName());
        }
        ItemMaster itemMaster = itemMasterOpt.get();
        itemMaster.setItemName(itemMasterDTO.getItemName());
        itemMaster.setItemType(itemMasterDTO.getItemType());
        ItemMaster savedItem = itemMasterRepository.save(itemMaster);

        // Delete item option which are removed
        List<ItemOption> savedItemOptions = itemOptionRepository.findByDelflagAndItem(0, savedItem, Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .filter(itemOption -> itemMasterDTO.getItemOptions().stream().noneMatch(itemOpt -> Objects.equals(itemOpt.getId(), itemOption.getId())))
                .peek(itemOption -> itemOption.setDelflag(1))
                .toList();
        itemOptionRepository.saveAll(savedItemOptions);

        List<ItemOption> itemOptions = itemMasterDTO.getItemOptions()
                .stream()
                .filter(itemOptionDTO -> itemOptionDTO.getId() == null || itemOptionDTO.getId() == 0)
                .map(itemOptDto -> {
                    ItemOption itemOption = new ItemOption();
                    itemOption.setOptionName(itemOptDto.getOptionName());
                    itemOption.setItem(savedItem);
                    return itemOption;
                }).toList();
        itemOptionRepository.saveAll(itemOptions);
    }

    public ItemMasterDTO deleteById(int itemId) {
        Optional<ItemMaster> itemMasterOpt = itemMasterRepository.findById(itemId);
        if (itemMasterOpt.isEmpty()) {
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_NOT_EXIST), HttpStatus.BAD_REQUEST, itemId + "");
        }
        ItemMaster itemMaster = itemMasterOpt.get();
        itemMaster.setDelflag(1);
        itemMasterRepository.save(itemMaster);
        return new ItemMasterDTO(itemMaster);
    }

    public List<JSONObject> findByItemName(String itemName) {
        logger.info("Querying for {}", itemName);
        List<ItemMaster> itemMasterList = itemMasterRepository.findByItemNameStartingWith(itemName);
        return itemMasterList.stream()
                .map(itemMaster -> {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", itemMaster.getId());
                    jsonObject.put("label", itemMaster.getItemName());
                    jsonObject.put("data", itemMaster.getItemType());
                    return jsonObject;
                })
                .toList();
    }
}