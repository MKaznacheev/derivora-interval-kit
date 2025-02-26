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

package xyz.derivora.intervalkit.test.bounds.infinite;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import xyz.derivora.intervalkit.bounds.InfiniteBound;
import xyz.derivora.intervalkit.test.bounds.BoundTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("xyz/derivora/intervalkit/bounds/infinite")
@Tag("base-test")
@DisplayName("Base tests for infinite bounds")
@Disabled("This is a base test class and should not be executed directly")
abstract class AbstractInfiniteBoundTest extends BoundTest {

    protected static final InfiniteBound[] INFINITE_BOUNDS = new InfiniteBound[2];

    protected abstract InfiniteBound createInfiniteBound(boolean isPositive);

    @BeforeEach
    final void createInfiniteBounds() {
        INFINITE_BOUNDS[0] = createInfiniteBound(true);
        INFINITE_BOUNDS[1] = createInfiniteBound(false);
    }

    @ParameterizedTest
    @FieldSource("INFINITE_BOUNDS")
    @DisplayName("Should return false for InfiniteBound implementation")
    void isFinite_forInfiniteBound_shouldReturnFalse(InfiniteBound bound) {
        boolean isFinite = bound.isFinite();
        assertFalse(isFinite);
    }

    @ParameterizedTest
    @FieldSource("INFINITE_BOUNDS")
    @DisplayName("Should return true for InfiniteBound implementation")
    void isInfinite_forInfiniteBound_shouldReturnTrue(InfiniteBound bound) {
        boolean isInfinite = bound.isInfinite();
        assertTrue(isInfinite);
    }

    @Test
    @DisplayName("Should return true for positive infinite bound")
    void isPositive_forPositiveBound_shouldReturnTrue() {
        boolean isPositive = INFINITE_BOUNDS[0].isPositive();
        assertTrue(isPositive);
    }

    @Test
    @DisplayName("Should return false for negative infinite bound")
    void isPositive_forNegativeBound_shouldReturnFalse() {
        boolean isPositive = INFINITE_BOUNDS[1].isPositive();
        assertFalse(isPositive);
    }

    @Test
    @DisplayName("Should return false for positive infinite bound")
    void isNegative_forPositiveBound_shouldReturnFalse() {
        boolean isNegative = INFINITE_BOUNDS[0].isNegative();
        assertFalse(isNegative);
    }

    @Test
    @DisplayName("Should return true for negative infinite bound")
    void isNegative_forNegativeBound_shouldReturnTrue() {
        boolean isNegative = INFINITE_BOUNDS[1].isNegative();
        assertTrue(isNegative);
    }
}
