package org.ekal.ivd.dao;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dto.ErrorResponseDTO;
import org.ekal.ivd.dto.ProgramMasterDTO;
import org.ekal.ivd.entity.ProgramMaster;
import org.ekal.ivd.exception.DynamicException;
import org.ekal.ivd.repository.ProgramMasterRepository;
import org.ekal.ivd.util.ErrorResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgramMasterDao {
    private final static Logger logger = LoggerFactory.getLogger(ProgramMasterDao.class);

    @Autowired
    ProgramMasterRepository programMasterRepository;

    public void createProgram(@Valid ProgramMasterDTO programMasterDTO) {
        Optional<ProgramMaster> optionalProgramMaster = programMasterRepository.findByName(programMasterDTO.getName());
        if(optionalProgramMaster.isPresent()){
            throw new DynamicException(new ErrorResponseDTO(ErrorResponseCode.PROGRAM_ALREADY_EXIST), HttpStatus.BAD_REQUEST, programMasterDTO.getName());
        }
        ProgramMaster master = new ProgramMaster(programMasterDTO);
        programMasterRepository.save(master);
        programMasterDTO.setId(master.getId());
    }

    public List<ProgramMasterDTO> getProgram(){
        List<ProgramMaster> programMasters = programMasterRepository.findAll();
        return programMasters.stream().map(p -> new ProgramMasterDTO(p)).collect(Collectors.toList());
    }
}