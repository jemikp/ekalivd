package org.ekal.ivd.dao;

import lombok.extern.slf4j.Slf4j;
import org.ekal.ivd.dto.DonorDTO;
import org.ekal.ivd.dto.PaginationDTO;
import org.ekal.ivd.dto.UserDTO;
import org.ekal.ivd.entity.Donor;
import org.ekal.ivd.entity.User;
import org.ekal.ivd.repository.DonorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DonorDao {
    private final DonorRepository donorRepository;

    public DonorDao(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor saveDonor(DonorDTO donorDTO) {
        Donor donor = new Donor(donorDTO);
        return donorRepository.save(donor);
    }

    public PaginationDTO<DonorDTO> getAllDonors(int page, int size) {

        PaginationDTO<DonorDTO> donorPage = null;
        Pageable paging = PageRequest.of(page, size);
        Page<Donor> allDonors = donorRepository.findAll(paging);

        if (allDonors.hasContent()) {

            Page<DonorDTO> donorDto = allDonors.map(DonorDTO::new);
            donorPage = new PaginationDTO<>(donorDto);
        }
        return donorPage;
    }

    public void deleteDonor(int donorId){
        donorRepository.deleteById(donorId);
    }
}
