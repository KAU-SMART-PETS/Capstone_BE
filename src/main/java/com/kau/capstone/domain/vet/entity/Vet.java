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

import java.util.Objects;

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

    /*
    동물병원과 사용자 사이의 거리 계산
    링크: https://www.geodatasource.com/developers/java
    1 Mile = 1609.344 m
    값이 너무 부정확하면 BigDecimal로 교체하기
     */
    public double calculateDistanceToMember(Double memberMapY, Double memberMapX) {
        if ((Objects.equals(mapX, memberMapX)) && (Objects.equals(mapY, memberMapY))) {
            return 0;
        }

        double theta = mapX - memberMapX;
        double dist = Math.sin(
                Math.toRadians(mapY))
                * Math.sin(Math.toRadians(memberMapY))
                +
                Math.cos(Math.toRadians(mapY))
                        * Math.cos(Math.toRadians(memberMapY))
                        * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515; // 여기까지가 마일(Mile) 단위 계산
        dist = dist * 1609.344; // 미터(M) 단위로 계산
        return dist;
    }
}
