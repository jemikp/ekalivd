package org.ekal.ivd.dao;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ErrorResponseDTO;
import org.ekal.ivd.dto.ItemMasterDTO;
import org.ekal.ivd.entity.ItemMaster;
import org.ekal.ivd.exception.DynamicException;
import org.ekal.ivd.repository.ItemMasterRepository;
import org.ekal.ivd.util.ErrorResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemMasterDao {
    private final static Logger logger = LoggerFactory.getLogger(ItemMasterDao.class);

    @Autowired
    ItemMasterRepository itemMasterRepository;

    public void createItemMaster(@Valid ItemMasterDTO itemMasterDTO) {
        List<ItemMaster> optionalItemMaster = itemMasterRepository.findByItemName(itemMasterDTO.getItemName());
        if(optionalItemMaster.size() > 0){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_EXIST), HttpStatus.BAD_REQUEST, itemMasterDTO.getItemName());
        }
        ItemMaster master = new ItemMaster(itemMasterDTO);
        itemMasterRepository.save(master);
        itemMasterDTO.setId(master.getId());
    }

    public List<ItemMasterDTO> getItem(){
        List<ItemMaster> itemMasters = itemMasterRepository.findByDelflag(0,Sort.by(Sort.Direction.DESC, "id"));
        return itemMasters.stream().map(i -> new ItemMasterDTO(i)).collect(Collectors.toList());
    }
    
    public ItemMasterDTO getItemById(int itemId){
        Optional<ItemMaster> itemMaster = itemMasterRepository.findById(itemId);
        if(itemMaster.isEmpty()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_NOT_EXIST), HttpStatus.BAD_REQUEST, itemId+"");
        }
        return new ItemMasterDTO(itemMaster.get());
    }
    
    public void updateItemById(@Valid ItemMasterDTO itemMasterDTO,int itemId){
        Optional<ItemMaster> itemMasterOpt = itemMasterRepository.findById(itemId);
        if(itemMasterOpt.isEmpty()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_NOT_EXIST), HttpStatus.BAD_REQUEST, itemId+"");
        }
        List<ItemMaster> itemMasterList = itemMasterRepository.findByItemName(itemMasterDTO.getItemName());
        if(itemMasterList.size() > 1 || !itemMasterList.get(0).getId().equals(itemId)){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_EXIST), HttpStatus.BAD_REQUEST, itemMasterDTO.getItemName());
        }
        ItemMaster itemMaster = itemMasterOpt.get();
        itemMaster.setItemName(itemMasterDTO.getItemName());
        itemMaster.setItemType(itemMasterDTO.getItemType());
        itemMasterRepository.save(itemMaster);
    }

    public ItemMasterDTO deleteById(int itemId){
        Optional<ItemMaster> itemMasterOpt = itemMasterRepository.findById(itemId);
        if(itemMasterOpt.isEmpty()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.ITEM_NOT_EXIST), HttpStatus.BAD_REQUEST, itemId+"");
        }
        ItemMaster itemMaster = itemMasterOpt.get();
        itemMaster.setDelflag(1);
        itemMasterRepository.save(itemMaster);
        return new ItemMasterDTO(itemMaster);
    }
}