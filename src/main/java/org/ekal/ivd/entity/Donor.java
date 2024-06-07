package org.ekal.ivd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ekal.ivd.dto.DonorDTO;

import java.util.Optional;

@Setter
@Getter
@ToString
@Entity
@Table(name = "doner")
@RequiredArgsConstructor
public class Donor extends BaseEntity {
    private String name;
    private String trust;
    private String phoneNumber;
    private String emailId;
    private String zipCode;
    private String country;

    public Donor(DonorDTO donorDTO) {
        Optional.ofNullable(donorDTO).ifPresent(dto -> {
            this.setName(dto.getName());
            this.setTrust(dto.getTrust());
            this.setPhoneNumber(dto.getPhoneNumber());
            this.setEmailId(dto.getEmailId());
            this.setZipCode(dto.getZipCode());
            this.setCountry(dto.getCountry());
        });
    }
}
