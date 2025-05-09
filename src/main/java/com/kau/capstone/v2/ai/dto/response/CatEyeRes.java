package com.kau.capstone.v2.ai.dto.response;

import java.util.Map;

public record CatEyeRes(
    int size,
    float cornealUlcer,
    float cornealDystcatrophy,
    float conjunctivitis,
    float blepharitis,
    float nonUlcerativeKeratitis) {

    private static final String[] catDiseases = {"corneal_ulcer", "corneal_dystrophy",
        "conjunctivitis", "blepharitis", "non_ulcerative_keratitis"};

    public static CatEyeRes of(Map<String, Object> res) {
        int idx = 0;
        return new CatEyeRes(
            5,
            (float) res.get(catDiseases[idx++]),
            (float) res.get(catDiseases[idx++]),
            (float) res.get(catDiseases[idx++]),
            (float) res.get(catDiseases[idx++]),
            (float) res.get(catDiseases[idx]));
    }

}
