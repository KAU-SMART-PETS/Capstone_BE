package com.kau.capstone.v2.ai.service;


import com.kau.capstone.entity.member.repository.MemberRepository;
import com.kau.capstone.global.aiModel.AIModelClient;
import com.kau.capstone.global.common.s3.FileService;
import com.kau.capstone.v1.auth.dto.LoginInfo;
import com.kau.capstone.v2.ai.dto.request.EyeReqV2;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIServiceV2 {

    private static final String[] diseases = {"conjunctivitis", "ulcerative_keratitis", "cataract",
        "non_ulcerative_keratitis", "pigmentary_keratitis", "entropion", "blepharitis",
        "eyelid_tumor", "incontinence", "nuclear_sclerosis", "corneal_dystrophy", "corneal_ulcer"};

    private final MemberRepository memberRepository;
    private final AIModelClient aiModelClient;
    private final FileService fileService;

    public EyeReqV2 analyzeEye(LoginInfo loginInfo, MultipartFile imageFile, String petType) {
        memberRepository.getById(loginInfo.memberId());

        String imageUrl = fileService.uploadImage(imageFile, "ai/eyes/");

        Map<String, Object> aiResponse = aiModelClient.analyzeImage(imageUrl, petType);

        Float[] prob = new Float[diseases.length];
        for (int i = 0; i < diseases.length; ++i) {
            if (aiResponse.containsKey(diseases[i])) {
                prob[i] = (Float) aiResponse.get(diseases[i]);
            } else {
                prob[i] = null;
            }
        }

        int idx = 0;
        EyeReqV2 eyeRes = new EyeReqV2(
            prob[idx++], prob[idx++], prob[idx++], prob[idx++], prob[idx++], prob[idx++],
            prob[idx++], prob[idx++], prob[idx++], prob[idx++], prob[idx++], prob[idx]
        );

        fileService.deleteImage(imageUrl);

        return eyeRes;
    }

}
