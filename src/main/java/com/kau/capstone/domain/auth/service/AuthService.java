package com.kau.capstone.domain.auth.service;

import com.kau.capstone.domain.auth.dto.SignUserDto;
import com.kau.capstone.domain.auth.dto.UserInfoDto;
import com.kau.capstone.domain.auth.util.SocialSite;
import com.kau.capstone.domain.auth.util.provider.OAuthProvider;
import com.kau.capstone.domain.auth.util.provider.OAuthProviders;
import com.kau.capstone.domain.member.entity.Member;
import com.kau.capstone.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String COOKIE_NAME = "JSESSIONID";
    private static final int EXPIRE_TIME = 0;
    private static final String HOME_PATH = "/";

    private final OAuthProviders oAuthProviders;
    private final MemberService memberService;

    public Cookie expireCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(EXPIRE_TIME);
        cookie.setPath(HOME_PATH);

        return cookie;
    }

    public String getLoginRedirectURL(String site) {
        SocialSite socialSite = SocialSite.findBySocialSite(site);
        OAuthProvider oAuthProvider = oAuthProviders.getClient(socialSite);

        return oAuthProvider.getRedirectURL();
    }

    public SignUserDto loginAuthorizeUser(String site, String code) {
        SocialSite socialSite = SocialSite.findBySocialSite(site);
        OAuthProvider oAuthProvider = oAuthProviders.getClient(socialSite);
        UserInfoDto userInfoDto = oAuthProvider.getUserInfo(code);

        return memberService.findOrCreateMember(site, userInfoDto);
    }

    public void logout(Long memberId) {
        Member member = memberService.findById(memberId);
        SocialSite socialSite = SocialSite.findBySocialSite(member.getSocialSite());
        OAuthProvider oAuthProvider = oAuthProviders.getClient(socialSite);
        oAuthProvider.logout(member.getPlatformId());
    }
}
