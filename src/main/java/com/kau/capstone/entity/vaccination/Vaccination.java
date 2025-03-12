package com.kau.capstone.entity.vaccination;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v1.vaccination.dto.CreateVaccinationRequest;
import com.kau.capstone.v2.vaccination.dto.CreateVaccinationReqV2;
import com.kau.capstone.v1.vaccination.dto.PutVaccinationRequest;
import com.kau.capstone.v2.vaccination.dto.PutVaccinationReqV2;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vaccination {

    @Id
    @Comment("알람 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Comment("반려동물 연결")
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Comment("예방접종 이름")
    private String name;

    @Comment("접종시기")
    private LocalDate vaccinatedAt;

    private Vaccination(Pet pet, String name, LocalDate vaccinatedAt) {
        this.pet = pet;
        this.name = name;
        this.vaccinatedAt = vaccinatedAt;
    }

    public static Vaccination of(Pet pet, CreateVaccinationRequest request) {
        return new Vaccination(
                pet,
                request.name(),
                LocalDate.of(request.year(), request.month(), request.day())
        );
    }

    public static Vaccination of(Pet pet, CreateVaccinationReqV2 request) {
        return new Vaccination(
            pet,
            request.name(),
            request.vaccinatedAt()
        );
    }

    public void modify(PutVaccinationRequest request) {
        this.name = request.name();
        this.vaccinatedAt = LocalDate.of(request.year(), request.month(), request.day());
    }

    public void modify(PutVaccinationReqV2 request) {
        this.name = request.name();
        this.vaccinatedAt = request.vaccinatedAt();
    }
}
