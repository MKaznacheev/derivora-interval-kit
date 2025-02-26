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

package xyz.derivora.intervalkit.test.bounds.factory;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import testdata.numbers.ComparableNumber;
import testdata.numbers.SimpleNumber;
import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.bounds.InfiniteBound;
import xyz.derivora.intervalkit.bounds.factory.BoundFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@Tag("xyz/derivora/intervalkit/bounds/factory")
@Tag("base-test")
@DisplayName("Base tests for BoundFactory")
@Disabled("This is a base test class and should not be executed directly")
abstract class BoundFactoryTest {

    protected BoundFactory BOUND_FACTORY;

    protected abstract BoundFactory createBoundFactory();

    @BeforeEach
    final void setBoundFactory() {
        BOUND_FACTORY = createBoundFactory();
    }

    @Nested
    @Tag("xyz/derivora/intervalkit/bounds/factory")
    @Tag("InfiniteBounds")
    @DisplayName("Tests for creating InfiniteBounds")
    class InfiniteBoundCreationTests {

        @Test
        @DisplayName("Should return positive infinite bound when isPositive is true")
        void getInfiniteBound_withTrue_shouldReturnPositiveBound() {
            InfiniteBound result = BOUND_FACTORY.getInfiniteBound(true);
            assertTrue(result.isPositive());
        }

        @Test
        @DisplayName("Should return negative infinite bound when isPositive is false")
        void getInfiniteBound_withFalse_shouldReturnNegativeBound() {
            InfiniteBound result = BOUND_FACTORY.getInfiniteBound(false);
            assertTrue(result.isNegative());
        }

        @Test
        @DisplayName("Should return positive infinite bound")
        void getPositiveInfiniteBound_shouldReturnPositiveBound() {
            InfiniteBound result = BOUND_FACTORY.getPositiveInfiniteBound();
            assertTrue(result.isPositive());
        }

        @Test
        @DisplayName("Should return negative infinite bound")
        void getNegativeInfiniteBound_shouldReturnNegativeBound() {
            InfiniteBound result = BOUND_FACTORY.getNegativeInfiniteBound();
            assertTrue(result.isNegative());
        }
    }

    @Nested
    @Tag("xyz/derivora/intervalkit/bounds/factory")
    @Tag("FiniteBounds")
    @DisplayName("Tests for creating FiniteBounds")
    class FiniteBoundCreationTests {

        static final Number[] ZERO_NUMBERS = {
                (byte) 0, (short) 0,
                0, 0L, 0.0f, 0.0,
                BigInteger.ZERO,
                BigDecimal.ZERO,
                ComparableNumber.ZERO
        };

        @Test
        @DisplayName("Should throw NullPointerException when number is null")
        void getFiniteBound_withNullNumber_shouldThrowNullPointerException() {
            assertThrows(
                    NullPointerException.class,
                    () -> BOUND_FACTORY.getFiniteBound((Number) null)
            );
        }

        @Test
        @DisplayName("Should throw NullPointerException when numeric string representation is null")
        void getFiniteBound_withNullString_shouldThrowNullPointerException() {
            assertThrows(
                    NullPointerException.class,
                    () -> BOUND_FACTORY.getFiniteBound((String) null)
            );
        }

        @Test
        @DisplayName("Should throw NumberFormatException if string is not a valid numeric representation")
        void getFiniteBound_withInvalidString_shouldThrowNumberFormatException() {
            String number = "Not a number";
            assertThrows(
                    NumberFormatException.class,
                    () -> BOUND_FACTORY.getFiniteBound(number)
            );
        }

        @Test
        @DisplayName("Should throw ClassCastException if number is not comparable")
        void getFiniteBound_withNotComparableNumber_shouldThrowClassCastException() {
            Number illegalNumber = SimpleNumber.ILLEGAL_NUMBER;
            assertThrows(
                    ClassCastException.class,
                    () -> BOUND_FACTORY.getFiniteBound(illegalNumber)
            );
        }

        @ParameterizedTest
        @FieldSource("ZERO_NUMBERS")
        @DisplayName("Should return correct bound if number is valid")
        void getFiniteBound_withValidNumber_shouldReturnCorrectBound(Number value) {
            FiniteBound<?> result = BOUND_FACTORY.getFiniteBound(value);

            assertEquals(value, result.getValue());
            assertEquals(value.getClass(), result.getValueType());
        }
    }
}
