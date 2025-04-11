package com.kau.capstone.v2.ai.service;


import com.kau.capstone.entity.AI.CatEyes;
import com.kau.capstone.entity.AI.DogEyes;
import com.kau.capstone.entity.AI.Repository.CatEyesRepository;
import com.kau.capstone.entity.AI.Repository.DogEyesRepository;
import com.kau.capstone.entity.member.Member;
import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.pet.repository.PetRepository;
import com.kau.capstone.global.aiModel.AIModelClient;
import com.kau.capstone.global.common.s3.FileService;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.ai.dto.response.CatEyeRes;
import com.kau.capstone.v2.ai.dto.response.DogEyeRes;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIServiceV2 {


    private final CatEyesRepository catEyesRepository;
    private final DogEyesRepository dogEyesRepository;
    private final MemberRepository memberRepository;
    private final PetRepository petRepository;
    private final AIModelClient aiModelClient;
    private final FileService fileService;

    public Object analyzeEye(LoginInfo loginInfo, Long petId, MultipartFile imageFile) {
        Member member = memberRepository.getById(loginInfo.memberId());
        Pet pet = petRepository.getByIdAndDeletedAtIsNullAndMember(petId, member);

        String imageUrl = fileService.uploadImage(imageFile, "ai/eyes/");
        Map<String, Object> aiResponse = aiModelClient.analyzeImage(imageUrl,
            pet.getPetType().toString());
        fileService.deleteImage(imageUrl);

        if (aiResponse.size() == 5) {
            CatEyeRes catEyeRes = CatEyeRes.of(aiResponse);
            catEyesRepository.save(CatEyes.of(catEyeRes, pet));
            return catEyeRes;
        }

        DogEyeRes dogEyeRes = DogEyeRes.of(aiResponse);
        dogEyesRepository.save(DogEyes.of(dogEyeRes, pet));
        return dogEyeRes;
    }

}
