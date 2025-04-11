package com.kau.capstone.entity.AI;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v2.ai.dto.response.DogEyeRes;
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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DogEyes {

    @Id
    @Comment("안구질환 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("궤양성 각막질환")
    private float ulcerativeKeratitis;

    @Comment("백내장")
    private float cataract;

    @Comment("색소침착성각막염")
    private float pigmentaryKeratitis;

    @Comment("안검내반증")
    private float entropion;

    @Comment("안검종양")
    private float eyelidTumor;

    @Comment("유루증")
    private float incontinence;

    @Comment("핵경화")
    private float nuclearSclerosis;

    @Comment("결막염")
    private float conjunctivitis;

    @Comment("안검염")
    private float blepharitis;

    @Comment("비궤양성 각막질환")
    private float nonUlcerativeKeratitis;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private DogEyes(float ulcerativeKeratitis, float cataract, float pigmentaryKeratitis,
        float entropion, float eyelidTumor, float incontinence, float nuclearSclerosis,
        float conjunctivitis, float blepharitis, float nonUlcerativeKeratitis, Pet pet) {
        this.ulcerativeKeratitis = ulcerativeKeratitis;
        this.cataract = cataract;
        this.pigmentaryKeratitis = pigmentaryKeratitis;
        this.entropion = entropion;
        this.eyelidTumor = eyelidTumor;
        this.incontinence = incontinence;
        this.nuclearSclerosis = nuclearSclerosis;
        this.conjunctivitis = conjunctivitis;
        this.blepharitis = blepharitis;
        this.nonUlcerativeKeratitis = nonUlcerativeKeratitis;
        this.pet = pet;
    }

    public static DogEyes of(DogEyeRes eyeRes, Pet pet) {
        return new DogEyes(eyeRes.ulcerativeKeratitis(), eyeRes.cataract(),
            eyeRes.pigmentaryKeratitis(),
            eyeRes.entropion(), eyeRes.eyelidTumor(), eyeRes.incontinence(),
            eyeRes.nuclearSclerosis(),
            eyeRes.conjunctivitis(), eyeRes.blepharitis(), eyeRes.nonUlcerativeKeratitis(), pet);
    }

}
