package com.kau.capstone.v2.ai.dto.response;

import java.util.Map;

public record DogEyeRes(
    int size,
    float ulcerativeKeratitis,
    float cataract,
    float pigmentaryKeratitis,
    float entropion,
    float eyelidTumor,
    float incontinence,
    float nuclearSclerosis,
    float conjunctivitis,
    float blepharitis,
    float nonUlcerativeKeratitis
) {

    private static final String[] dogDiseases = {"ulcerative_keratitis", "cataract",
        "pigmentary_keratitis", "entropion", "eyelid_tumor", "incontinence",
        "nuclear_sclerosis", "conjunctivitis", "blepharitis", "non_ulcerative_keratitis"};

    public static DogEyeRes of(Map<String, Object> res) {
        int idx = 0;
        return new DogEyeRes(
            10,
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx++]),
            (float) res.get(dogDiseases[idx])
        );
    }

}
