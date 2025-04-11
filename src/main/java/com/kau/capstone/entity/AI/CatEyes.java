package com.kau.capstone.entity.AI;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.v2.ai.dto.response.CatEyeRes;
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
public class CatEyes {

    @Id
    @Comment("안구질환 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("각막궤양")
    private float cornealUlcer;

    @Comment("각막부골편")
    private float cornealDystcatrophy;

    @Comment("결막염")
    private float conjunctivitis;

    @Comment("안검염")
    private float blepharitis;

    @Comment("비궤양성 각막질환")
    private float nonUlcerativeKeratitis;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private CatEyes(float cornealUlcer, float cornealDystcatrophy, float conjunctivitis,
        float blepharitis, float nonUlcerativeKeratitis, Pet pet){
        this.cornealUlcer = cornealUlcer;
        this.cornealDystcatrophy = cornealDystcatrophy;
        this.conjunctivitis = conjunctivitis;
        this.blepharitis = blepharitis;
        this.nonUlcerativeKeratitis = nonUlcerativeKeratitis;
        this.pet = pet;
    }

    public static CatEyes of(CatEyeRes eyeRes, Pet pet){
        return new CatEyes(eyeRes.cornealUlcer(),
            eyeRes.cornealDystcatrophy(),
            eyeRes.conjunctivitis(),
            eyeRes.blepharitis(),
            eyeRes.nonUlcerativeKeratitis(),
            pet);
    }

}
