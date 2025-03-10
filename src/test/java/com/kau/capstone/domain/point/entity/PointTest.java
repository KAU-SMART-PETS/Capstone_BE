package com.kau.capstone.domain.point.entity;

import com.kau.capstone.entity.point.Point;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointTest {

    @Nested
    class payment_성공_테스트 {

        @Test
        void 포인트를_차감할_수_있다() {
            // given
            Point point = Point.builder()
                    .amount(10000L)
                    .build();
            Long expected = 5000L;

            // when
            point.payment(5000L);
            Long actual = point.getAmount();

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class earn_성공_테스트 {

        @Test
        void 사용자가_포인트를_획득할_수_있다() {
            // given
            Point point = Point.builder()
                    .amount(10000L)
                    .build();
            Long expected = 15000L;

            // when
            point.deposit(5000L);
            Long actual = point.getAmount();

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
