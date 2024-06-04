package com.kau.capstone.domain.member.controller;

import com.kau.capstone.domain.auth.dto.LoginInfo;
import com.kau.capstone.domain.auth.util.LoginUser;
import com.kau.capstone.domain.member.dto.MemberInfoResponse;
import com.kau.capstone.domain.member.dto.ModifyMemberRequest;
import com.kau.capstone.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/user")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(@LoginUser LoginInfo loginInfo) {
        MemberInfoResponse response = memberService.getMemberInfo(loginInfo.memberId());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/v1/user")
    public ResponseEntity<Void> putMemberInfo(@LoginUser LoginInfo loginInfo,
                                              @RequestBody ModifyMemberRequest request) {
        memberService.putMemberInfo(loginInfo.memberId(), request);

        return ResponseEntity.noContent().build();
    }
}
