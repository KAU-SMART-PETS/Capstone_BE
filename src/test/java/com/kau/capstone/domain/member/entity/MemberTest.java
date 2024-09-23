package com.kau.capstone.domain.member.entity;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberTest {

    @Nested
    class updateInfo_성공_테스트 {

        @Test
        void 사용자의_이름과_이메일을_변경할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .build();

            String expectedName = "update";
            String expectedEmail = "update@update.com";

            // when
            member.updateInfo(expectedName, expectedEmail);

            String actualName = member.getName();
            String actualEmail = member.getEmail();

            // then
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(actualName).isEqualTo(expectedName);
                soft.assertThat(actualEmail).isEqualTo(expectedEmail);
            });
        }

        @Test
        void 사용자의_이름만_변경할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .build();

            String expectedName = "update";

            // when
            member.updateInfo(expectedName, null);
            String actualName = member.getName();

            // then
            Assertions.assertThat(actualName).isEqualTo(expectedName);
        }

        @Test
        void 사용자의_이메일만_변경할_수_있다() {
            // given
            Member member = Member.builder()
                    .name("test")
                    .email("test@test.com")
                    .build();

            String expectedEmail = "update@update.com";

            // when
            member.updateInfo(null, expectedEmail);
            String actualEmail = member.getEmail();

            // then
            Assertions.assertThat(actualEmail).isEqualTo(expectedEmail);
        }
    }
}
