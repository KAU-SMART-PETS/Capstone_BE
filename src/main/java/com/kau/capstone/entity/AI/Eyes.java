package com.kau.capstone.entity.AI;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v2.ai.dto.request.EyeReqV2;
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
public class Eyes {

    @Id
    @Comment("안구질환 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("결막염 - cat, dog")
    private Float conjunctivitis;

    @Comment("궤양성 각막질환")
    private Float ulcerativeKeratitis;

    @Comment("백내장")
    private Float cataract;

    @Comment("비궤양성 각막질환 - cat, dog")
    private Float nonUlcerativeKeratitis;

    @Comment("색소침착성각막염")
    private Float pigmentaryKeratitis;

    @Comment("안검내반증")
    private Float entropion;

    @Comment("안검염 - cat, dog")
    private Float blepharitis;

    @Comment("안검종양")
    private Float eyelidTumor;

    @Comment("유루증")
    private Float incontinence;

    @Comment("핵경화")
    private Float nuclearSclerosis;

    @Comment("각막궤양 - only cat")
    private Float cornealUlcer;

    @Comment("각막부골편 - only cat")
    private Float cornealDystrophy;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private Eyes(Float conjunctivitis, Float ulcerativeKeratitis, Float cataract,
        Float nonUlcerativeKeratitis, Float pigmentaryKeratitis, Float entropion,
        Float blepharitis, Float eyelidTumor, Float incontinence,
        Float nuclearSclerosis, Float cornealDystrophy, Float cornealUlcer, Pet pet) {
        this.conjunctivitis = conjunctivitis;
        this.ulcerativeKeratitis = ulcerativeKeratitis;
        this.cataract = cataract;
        this.nonUlcerativeKeratitis = nonUlcerativeKeratitis;
        this.pigmentaryKeratitis = pigmentaryKeratitis;
        this.entropion = entropion;
        this.blepharitis = blepharitis;
        this.eyelidTumor = eyelidTumor;
        this.incontinence = incontinence;
        this.nuclearSclerosis = nuclearSclerosis;
        this.cornealDystrophy = cornealDystrophy;
        this.cornealUlcer = cornealUlcer;
        this.pet = pet;
    }

    public static Eyes of(EyeReqV2 eyeReq, Pet pet){
        return new Eyes(eyeReq.conjunctivitis(), eyeReq.ulcerativeKeratitis(),  eyeReq.cataract(),
            eyeReq.nonUlcerativeKeratitis(), eyeReq.pigmentaryKeratitis(), eyeReq.entropion(),
            eyeReq.blepharitis(), eyeReq.eyelidTumor(), eyeReq.incontinence(),
            eyeReq.nuclearSclerosis(), eyeReq.cornealDystrophy(), eyeReq.cornealUlcer(), pet);
    }

}
