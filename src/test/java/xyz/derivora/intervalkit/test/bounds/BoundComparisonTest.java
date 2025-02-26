/*
 * This file is part of Derivora Interval Kit.
 *
 * Derivora Interval Kit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Derivora Interval Kit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Derivora Interval Kit. If not, see https://www.gnu.org/licenses/lgpl-3.0.html.
 */

package xyz.derivora.intervalkit.test.bounds;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import testdata.bounds.providers.*;
import testdata.numbers.ComparableNumber;
import xyz.derivora.intervalkit.bounds.Bound;
import xyz.derivora.intervalkit.bounds.comparison.IncomparableBoundsException;
import xyz.derivora.intervalkit.bounds.finite.ImmutableFiniteBound;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("xyz/derivora/intervalkit/bounds")
@Tag("base-test")
@DisplayName("Base tests for bound comparison methods")
@Disabled("This is a base test class and should not be executed directly")
public abstract class BoundComparisonTest {

    protected static final Bound ZERO_BOUND = new ImmutableFiniteBound<>(0);
    protected static final Bound ILLEGAL_BOUND = new ImmutableFiniteBound<>(ComparableNumber.ILLEGAL_NUMBER);

    protected abstract int compare(Bound firstBound, Bound secondBound);

    @Nested
    @Tag("xyz/derivora/intervalkit/bounds")
    @Tag("InvalidInput")
    @DisplayName("Validation of invalid inputs in Bound comparison")
    class InvalidInputTests {

        @Test
        @DisplayName("Should throw NullPointerException when first bound is null")
        void compare_withNullFirstBound_shouldThrowNullPointerException() {
            assertThrows(
                    NullPointerException.class,
                    () -> compare(null, ZERO_BOUND)
            );
        }

        @Test
        @DisplayName("Should throw NullPointerException when second bound is null")
        void compare_withNullSecondBound_shouldThrowNullPointerException() {
            assertThrows(
                    NullPointerException.class,
                    () -> compare(ZERO_BOUND, null)
            );
        }

        @Test
        @DisplayName("Should throw IncomparableBoundsException when first bound value doesn't provide valid String value")
        void compare_withIllegalFirstBound_shouldThrowIncomparableBoundsException() {
            assertThrows(
                    IncomparableBoundsException.class,
                    () -> compare(ILLEGAL_BOUND, ZERO_BOUND)
            );
        }

        @Test
        @DisplayName("Should throw IncomparableBoundsException when second bound value doesn't provide valid String value")
        void compare_withIllegalSecondBound_shouldThrowIncomparableBoundsException() {
            assertThrows(
                    IncomparableBoundsException.class,
                    () -> compare(ZERO_BOUND, ILLEGAL_BOUND)
            );
        }

        @Test
        @DisplayName("Should throw IncomparableBoundsException when bounds are incomparable")
        void compare_withIncomparableBounds_shouldThrowIncomparableBoundsException() {
            Bound firstBound = new ImmutableFiniteBound<>(ComparableNumber.getIncomparableInstance("-1"));
            Bound secondBound = new ImmutableFiniteBound<>(ComparableNumber.getIncomparableInstance("1"));

            assertThrows(
                    IncomparableBoundsException.class,
                    () -> compare(firstBound, secondBound)
            );
        }
    }

    @Nested
    @Tag("xyz/derivora/intervalkit/bounds")
    @Tag("Ordering")
    @DisplayName("Validation of Bound ordering in comparisons")
    class OrderingTests {

        @ParameterizedTest
        @ArgumentsSources({
                @ArgumentsSource(NegativeOneBoundsProvider.class),
                @ArgumentsSource(ZeroBoundsProvider.class),
                @ArgumentsSource(OneBoundsProvider.class),
                @ArgumentsSource(NegativeInfiniteBoundsProvider.class),
                @ArgumentsSource(PositiveInfiniteBoundsProvider.class)
        })
        @DisplayName("Should return zero for identical bounds")
        void compare_withIdenticalBounds_shouldReturnZero(Bound bound) {
            int result = compare(bound, bound);
            assertEquals(0, result);
        }

        @ParameterizedTest
        @ArgumentsSource(EqualBoundsProvider.class)
        @DisplayName("Should return zero for equal bounds")
        void compare_withEqualBounds_shouldReturnZero(Bound firstBound, Bound secondBound) {
            int result = compare(firstBound, secondBound);
            assertEquals(0, result);
        }

        @ParameterizedTest
        @ArgumentsSource(LessThanPairsProvider.class)
        @DisplayName("Should return negative value when first bound is less than second")
        void compare_whenFirstIsLessThanSecond_shouldReturnNegativeValue(Bound lesser, Bound greater) {
            int result = compare(lesser, greater);
            assertTrue(result < 0);
        }

        @ParameterizedTest
        @ArgumentsSource(LessThanPairsProvider.class)
        @DisplayName("Should return positive value when first bound is greater than second")
        void compare_whenFirstIsGreaterThanSecond_shouldReturnPositiveValue(Bound lesser, Bound greater) {
            int result = compare(greater, lesser);
            assertTrue(result > 0);
        }
    }
}
