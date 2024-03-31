package org.ekal.ivd.dao;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ErrorResponseDTO;
import org.ekal.ivd.dto.IVDMasterDTO;
import org.ekal.ivd.entity.IVDMaster;
import org.ekal.ivd.exception.AppException;
import org.ekal.ivd.repository.IVDMasterRepository;
import org.ekal.ivd.util.ErrorResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IVDMasterDao {
    private final static Logger logger = LoggerFactory.getLogger(IVDMasterDao.class);

    @Autowired
    IVDMasterRepository ivdMasterRepository;

    public void createIVD(@Valid IVDMasterDTO ivdMasterDTO) {
        Optional<IVDMaster> optionalIVDMaster = ivdMasterRepository.findByName(ivdMasterDTO.getName());
        if(optionalIVDMaster.isPresent()){
            throw new AppException(new ErrorResponseDTO(ErrorResponseCode.IVD_ALREADY_EXIST), HttpStatus.BAD_REQUEST, ivdMasterDTO.getName());
        }
        IVDMaster master = new IVDMaster(ivdMasterDTO);
        ivdMasterRepository.save(master);
        ivdMasterDTO.setId(master.getId());
    }
}