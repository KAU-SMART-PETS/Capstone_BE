package com.kau.capstone.v2.vaccination.service;

import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.pet.repository.PetRepository;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.pet.exception.PetNotFoundExceptionV2;
import com.kau.capstone.v2.vaccination.dto.CreateVaccinationReqV2;
import com.kau.capstone.v2.vaccination.dto.PutVaccinationReqV2;
import com.kau.capstone.v2.vaccination.dto.VaccinationsResV2;
import com.kau.capstone.entity.vaccination.Vaccination;
import com.kau.capstone.entity.vaccination.repository.VaccinationRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccinationServiceV2 {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;
    private final VaccinationRepository vaccinationRepository;

    @Transactional
    public void createVaccinationInfo(LoginInfo loginInfo, Long petId, CreateVaccinationReqV2 request) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);

        Vaccination vaccination = Vaccination.of(pet, request);
        vaccinationRepository.save(vaccination);
    }

    @Transactional(readOnly = true)
    public VaccinationsResV2 getVaccinationInfo(LoginInfo loginInfo, Long petId) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);

        List<Vaccination> vaccinations = vaccinationRepository.findAllByPetOrderByVaccinatedAtDesc(pet);
        return VaccinationsResV2.of(pet, vaccinations);
    }

    @Transactional
    public void putVaccinationInfo(LoginInfo loginInfo, Long vaccinationId, PutVaccinationReqV2 request) {
//        Member member = memberRepository.getById(loginInfo.memberId());
//        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);
        Vaccination vaccination = vaccinationRepository.getById(vaccinationId);
        if (!Objects.equals(vaccination.getPet().getMember().getId(), loginInfo.memberId()))
            throw new PetNotFoundExceptionV2();
        vaccination.modify(request);
    }

    @Transactional
    public void deleteVaccinationInfo(LoginInfo loginInfo, Long vaccinationId) {
//        Member member = memberRepository.getById(loginInfo.memberId());
//        Pet pet = petRepository.getByIdAndMemberAndDeletedAtIsNull(petId, member);
        Vaccination vaccination = vaccinationRepository.getById(vaccinationId);
        if (!Objects.equals(vaccination.getPet().getMember().getId(), loginInfo.memberId()))
            throw new PetNotFoundExceptionV2();
        vaccinationRepository.delete(vaccination);
    }

}
