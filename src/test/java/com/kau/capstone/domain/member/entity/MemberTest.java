package com.kau.capstone.domain.member.entity;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberTest {

    @Nested
    class updateInfo_성공_테스트 {

        @Test
        void 사용자의_이메일만_변경할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .build();

            String expected = "update@update.com";

            // when
            member.updateInfo(expected, null, null, null);
            String actual = member.getEmail();

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 사용자의_휴대번호만_변경할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .build();

            String expected = "010-1234-1234";

            // when
            member.updateInfo(null, expected, null, null);
            String actual = member.getPhoneNumber();

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 사용자의_SMS_수신만_변경할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .build();

            Boolean expected = true;

            // when
            member.updateInfo(null, null, expected, null);
            Boolean actual = member.getSmsOptIn();

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 사용자의_이메일_수신만_변경할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .build();

            Boolean expected = true;

            // when
            member.updateInfo(null, null, null, expected);
            Boolean actual = member.getEmailOptIn();

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class payment_성공_테스트 {

        @Test
        void 사용자의_포인트를_차감할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .point(10000L)
                    .build();

            Long expected = 5000L;

            // when
            member.payment(5000L);
            Long actual = member.getPoint();

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
