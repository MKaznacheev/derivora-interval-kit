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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.bounds.finite.ImmutableFiniteBound;
import xyz.derivora.utilkit.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("xyz/derivora/intervalkit/bounds/finite")
@DisplayName("Tests for ImmutableFiniteBoundTest")
public class ImmutableFiniteBoundTest extends AbstractFiniteBoundTest {

    @Override
    protected <T extends Number & Comparable<T>> FiniteBound<T> createFiniteBound(T value) {
        return new ImmutableFiniteBound<>(value);
    }

    @Test
    @DisplayName("Should throw NullPointerException when value is null")
    void constructor_withNullValue_shouldThrowNullPointerException() {
        assertThrows(
                NullPointerException.class,
                () -> new ImmutableFiniteBound<>(null)
        );
    }

    @ParameterizedTest
    @ValueSource(floats = {Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NaN})
    @DisplayName("Should throw ValidationException when value is float infinite or NaN")
    void constructor_withInvalidFloat_shouldThrowValidationException(float value) {
        assertThrows(
                ValidationException.class,
                () -> new ImmutableFiniteBound<>(value)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN})
    @DisplayName("Should throw ValidationException when value is double infinite or NaN")
    void constructor_withInvalidDouble_shouldThrowValidationException(double value) {
        assertThrows(
                ValidationException.class,
                () -> new ImmutableFiniteBound<>(value)
        );
    }
}
