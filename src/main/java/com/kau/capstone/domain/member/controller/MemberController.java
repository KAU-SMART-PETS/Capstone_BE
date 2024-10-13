package com.kau.capstone.domain.member.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.member.dto.EarnPointRequest;
import com.kau.capstone.domain.member.dto.MemberInfoResponse;
import com.kau.capstone.domain.member.dto.ModifyMemberRequest;
import com.kau.capstone.domain.member.dto.OwnedPetsResponse;
import com.kau.capstone.domain.member.dto.PayPointRequest;
import com.kau.capstone.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/users")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(@LoginUser LoginInfo loginInfo) {
        MemberInfoResponse response = memberService.getMemberInfo(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/v1/users")
    public ResponseEntity<Void> putMemberInfo(@LoginUser LoginInfo loginInfo,
                                              @RequestBody ModifyMemberRequest request) {
        memberService.putMemberInfo(loginInfo.memberId(), request);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/v1/users/pets")
    public ResponseEntity<OwnedPetsResponse> getOwnerPets(@LoginUser LoginInfo loginInfo) {
        OwnedPetsResponse response = memberService.getOwnedPets(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/v1/users/pay")
    public ResponseEntity<Void> payWithPoints(@LoginUser LoginInfo loginInfo,
                                              @RequestBody PayPointRequest request) {
        memberService.processPointPayment(loginInfo.memberId(), request);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/v1/users/earn")
    public ResponseEntity<Void> earnWithPoints(@LoginUser LoginInfo loginInfo,
                                               @RequestBody EarnPointRequest request) {
        memberService.processPointEarn(loginInfo.memberId(), request);

        return ResponseEntity.ok().build();
    }
}
