package com.kau.capstone.domain.member.entity;

import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import com.kau.capstone.domain.point.entity.Point;
import com.kau.capstone.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @Comment("회원 포인트")
    @JoinColumn(name = "point_id")
    private Point point;

    @Comment("회원이 등록한 반려동물 목록")
    @OneToMany(mappedBy = "member")
    private List<OwnedPet> ownedPets;

    @Comment("사용자 휴대폰 번호")
    private String phoneNumber;

    @Comment("SMS 수신 여부")
    @Builder.Default
    private Boolean smsOptIn = false;

    @Comment("이메일 수신 여부")
    @Builder.Default
    private Boolean emailOptIn = false;

    public void updateInfo(String email, String phoneNumber, Boolean smsOptIn, Boolean emailOptIn) {
        updateEmail(email);
        updatePhoneNumber(phoneNumber);
        updateSmsOptIn(smsOptIn);
        updateEmailOptIn(emailOptIn);
    }

    private void updateEmail(String email) {
        if (!Objects.isNull(email)) {
            this.email = email;
        }
    }

    private void updatePhoneNumber(String phoneNumber) {
        if (!Objects.isNull(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
    }

    private void updateSmsOptIn(Boolean smsOptIn) {
        if (!Objects.isNull(smsOptIn)) {
            this.smsOptIn = smsOptIn;
        }
    }

    private void updateEmailOptIn(Boolean emailOptIn) {
        if (!Objects.isNull(emailOptIn)) {
            this.emailOptIn = emailOptIn;
        }
    }

    public void connectPoint(Point point) {
        this.point = point;
    }

    public void updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
