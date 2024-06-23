package org.ekal.ivd.dto;

import lombok.Data;
import org.ekal.ivd.entity.Donor;

import java.util.Optional;

@Data
public class DonorDTO {

    private Integer id;
    private String name;
    private String trust;
    private String phoneNumber;
    private String emailId;
    private String zipCode;
    private String country;

    public DonorDTO(Donor donor) {

        Optional.ofNullable(donor).ifPresent(u -> {
            this.id = u.getId();
            this.name = u.getName();
            this.trust = u.getTrust();
            this.phoneNumber = u.getPhoneNumber();
            this.zipCode = u.getZipCode();
            this.country = u.getCountry();
            this.emailId = u.getEmailId();
        });
    }
}
