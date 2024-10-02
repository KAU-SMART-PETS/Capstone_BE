package com.kau.capstone.domain.member.entity;

import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Comment("회원 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("소셜 로그인 ID")
    private String platformId;

    @Comment("소셜 로그인 사이트")
    private String socialSite;

    @Comment("소셜 사이트 리프레시 토큰값")
    private String refreshToken;

    @Comment("회원 이름")
    private String name;

    @Comment("회원 이메일")
    private String email;

    @Comment("회원 포인트")
    private Long point;

    @Comment("회원이 등록한 반려동물 목록")
    @OneToMany(mappedBy = "member")
    private List<OwnedPet> ownedPets;

    public void updateInfo(String name, String email) {
        updateName(name);
        updateEmail(email);
    }

    private void updateName(String name) {
        if (!Objects.isNull(name)) {
            this.name = name;
        }
    }

    private void updateEmail(String email) {
        if (!Objects.isNull(email)) {
            this.email = email;
        }
    }
}
