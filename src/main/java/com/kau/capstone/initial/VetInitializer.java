package com.kau.capstone.initial;

import com.kau.capstone.domain.vet.entity.Vet;
import com.kau.capstone.domain.vet.repository.VetRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class VetInitializer {

    private final VetRepository vetRepository;

    @PostConstruct
    public void init() {
        // 동물병원 더미데이터 추가
        var vet1 = create("반딧불 동물병원",
                "경기도 고양시 일산서구 중앙로 1443, 가람상가 208호 (주엽동)",
                37.6704107,
                126.7597444,
                "904-2345");

        var vet2 = create("동물진료법인 쥬박스동물메디컬센터",
                "경기도 고양시 일산서구 중앙로 1425, 주엽역 삼부르네상스 (주엽동)",
                37.6694342,
                126.7613527,
                "031-913-0649");

        var vet3 = create("정창수외과동물병원",
                "경기도 고양시 일산서구 중앙로 1547, KINTEX ZONE 203,204호 (대화동)",
                37.6748151,
                126.7495672,
                "031-911-7577");

        var vet4 = create("위케어동물의료센터",
                "경기도 고양시 일산동구 위시티4로 46, 상가동 302,303,303A,304호 (식사동, 위시티일산자이2단지아파트)",
                37.6826214,
                126.8161914,
                "031-994-6346");

        var vet5 = create("사과나무 한방재활 동물병원",
                "경기도 고양시 일산서구 일산로 539, 가람빌딩 101호 (일산동)",
                37.6773273,
                126.7679504,
                "031-912-7582");

        var vet6 = create("반딧불 동물병원",
                "경기도 고양시 일산서구 중앙로 1443, 가람상가 208호 (주엽동)",
                37.6704107,
                126.7597444,
                "904-2345");

        var vet7 = create("동물진료법인 쥬박스동물메디컬센터",
                "경기도 고양시 일산서구 중앙로 1425, 주엽역 삼부르네상스 (주엽동)",
                37.6694342,
                126.7613527,
                "031-913-0649");

        var vet8 = create("정창수외과동물병원",
                "경기도 고양시 일산서구 중앙로 1547, KINTEX ZONE 203,204호 (대화동)",
                37.6748151,
                126.7495672,
                "031-911-7577");

        var vet9 = create("위케어동물의료센터",
                "경기도 고양시 일산동구 위시티4로 46, 상가동 302,303,303A,304호 (식사동, 위시티일산자이2단지아파트)",
                37.6826214,
                126.8161914,
                "031-994-6346");

        var vet10 = create("사과나무 한방재활 동물병원",
                "경기도 고양시 일산서구 일산로 539, 가람빌딩 101호 (일산동)",
                37.6773273,
                126.7679504,
                "031-912-7582");

        var vet11 = create("반딧불 동물병원",
                "경기도 고양시 일산서구 중앙로 1443, 가람상가 208호 (주엽동)",
                37.6704107,
                126.7597444,
                "904-2345");

        var vet12 = create("동물진료법인 쥬박스동물메디컬센터",
                "경기도 고양시 일산서구 중앙로 1425, 주엽역 삼부르네상스 (주엽동)",
                37.6694342,
                126.7613527,
                "031-913-0649");

        var vet13 = create("정창수외과동물병원",
                "경기도 고양시 일산서구 중앙로 1547, KINTEX ZONE 203,204호 (대화동)",
                37.6748151,
                126.7495672,
                "031-911-7577");

        var vet14 = create("위케어동물의료센터",
                "경기도 고양시 일산동구 위시티4로 46, 상가동 302,303,303A,304호 (식사동, 위시티일산자이2단지아파트)",
                37.6826214,
                126.8161914,
                "031-994-6346");

        var vet15 = create("사과나무 한방재활 동물병원",
                "경기도 고양시 일산서구 일산로 539, 가람빌딩 101호 (일산동)",
                37.6773273,
                126.7679504,
                "031-912-7582");

        var vet16 = create("반딧불 동물병원",
                "경기도 고양시 일산서구 중앙로 1443, 가람상가 208호 (주엽동)",
                37.6704107,
                126.7597444,
                "904-2345");

        var vet17 = create("동물진료법인 쥬박스동물메디컬센터",
                "경기도 고양시 일산서구 중앙로 1425, 주엽역 삼부르네상스 (주엽동)",
                37.6694342,
                126.7613527,
                "031-913-0649");

        var vet18 = create("정창수외과동물병원",
                "경기도 고양시 일산서구 중앙로 1547, KINTEX ZONE 203,204호 (대화동)",
                37.6748151,
                126.7495672,
                "031-911-7577");

        var vet19 = create("위케어동물의료센터",
                "경기도 고양시 일산동구 위시티4로 46, 상가동 302,303,303A,304호 (식사동, 위시티일산자이2단지아파트)",
                37.6826214,
                126.8161914,
                "031-994-6346");

        var vet20 = create("사과나무 한방재활 동물병원",
                "경기도 고양시 일산서구 일산로 539, 가람빌딩 101호 (일산동)",
                37.6773273,
                126.7679504,
                "031-912-7582");

        save(vet1, vet2, vet3, vet4, vet5);
        save(vet6, vet7, vet8, vet9, vet10);
        save(vet11, vet12, vet13, vet14, vet15);
        save(vet16, vet17, vet18, vet19, vet20);
    }

    private Vet create(String name, String address, Double latitude, Double longitude, String telephone) {
        return Vet.builder()
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .telephone(telephone)
                .build();
    }

    private void save(Vet... vets) {
        vetRepository.saveAll(Arrays.stream(vets).toList());
    }
}
