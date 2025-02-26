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

package xyz.derivora.intervalkit.test.bounds.finite;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import testdata.numbers.ComparableNumber;
import xyz.derivora.intervalkit.bounds.Bound;
import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.test.bounds.BoundTest;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@Tag("xyz/derivora/intervalkit/bounds/finite")
@Tag("base-test")
@DisplayName("Base tests for finite bounds")
@Disabled("This is a base test class and should not be executed directly")
abstract class AbstractFiniteBoundTest extends BoundTest {

    static final Number[] ZERO_NUMBERS = {
            (byte) 0, (short) 0,
            0, 0L, 0.0f, 0.0,
            BigInteger.ZERO,
            BigDecimal.ZERO,
            ComparableNumber.ZERO
    };

    protected abstract <T extends Number & Comparable<T>> FiniteBound<T> createFiniteBound(T value);

    @ParameterizedTest
    @FieldSource("ZERO_NUMBERS")
    @DisplayName("Should return true for FiniteBound implementation")
    <T extends Number & Comparable<T>> void isFinite_forFiniteBound_shouldReturnTrue(T number) {
        Bound finiteBound = createFiniteBound(number);
        boolean isFinite = finiteBound.isFinite();

        assertTrue(isFinite);
    }

    @ParameterizedTest
    @FieldSource("ZERO_NUMBERS")
    @DisplayName("Should return false for FiniteBound implementation")
    <T extends Number & Comparable<T>> void isInfinite_forFiniteBound_shouldReturnFalse(T number) {
        Bound finiteBound = createFiniteBound(number);
        boolean isInfinite = finiteBound.isInfinite();

        assertFalse(isInfinite);
    }

    @ParameterizedTest
    @FieldSource("ZERO_NUMBERS")
    @DisplayName("Should return correct value")
    <T extends Number & Comparable<T>> void getValue_forFiniteBound_shouldReturnCorrectValue(T number) {
        FiniteBound<T> finiteBound = createFiniteBound(number);
        T value = finiteBound.getValue();

        assertEquals(number, value);
    }

    @ParameterizedTest
    @FieldSource("ZERO_NUMBERS")
    @DisplayName("Should return correct type")
    <T extends Number & Comparable<T>> void getValueType_forFiniteBound_shouldReturnCorrectType(T number) {
        FiniteBound<T> finiteBound = createFiniteBound(number);
        Class<? extends T> type = finiteBound.getValueType();

        assertEquals(number.getClass(), type);
    }
}
