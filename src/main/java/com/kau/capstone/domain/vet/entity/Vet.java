package com.kau.capstone.domain.vet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vet {

    @Id
    @Comment("동물병원 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("CSV 파일 식별자")
    private String csvId;

    /*
    주소 CSV 사이트: https://www.data.go.kr/data/15045050/fileData.do
     */
    @Comment("주소 (기본값: 도로명 전체 주소 기준, 없으면 소재지 전체 주소로 설정)")
    private String address;

    @Comment("이름")
    private String name;

    @Comment("주소의 x좌표 (경도, longitude)")
    private Double mapX;

    @Comment("주소의 y좌표 (위도, latitude)")
    private Double mapY;

    @Comment("전화번호")
    private String telephone;
}
