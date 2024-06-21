package org.ekal.ivd.controller;

import jakarta.validation.Valid;
import org.ekal.ivd.dao.DonorDao;
import org.ekal.ivd.dto.DonorDTO;
import org.ekal.ivd.dto.PaginationDTO;
import org.ekal.ivd.entity.Donor;
import org.ekal.ivd.util.CommonConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donor")
public class DonorController {
    private final DonorDao donorDao;

    public DonorController(DonorDao donorDao) {
        this.donorDao = donorDao;
    }

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Donor> save(@Valid @RequestBody DonorDTO donorDTO) {
        Donor donor = donorDao.saveDonor(donorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(donor);
    }

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationDTO<DonorDTO>> getAllDonors(@RequestParam(name = "page", defaultValue = CommonConstant.PAGE) int page,
                                                                @RequestParam(name = "size", defaultValue = CommonConstant.SIZE) int size) {
        PaginationDTO<DonorDTO> donorPageDto = donorDao.getAllDonors(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(donorPageDto);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<?> deleteDonor(@RequestParam(name = "donorId") Integer donorId) {
        donorDao.deleteDonor(donorId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Donor with Id: " + donorId);
    }
}
