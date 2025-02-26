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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import xyz.derivora.intervalkit.bounds.InfiniteBound;
import xyz.derivora.intervalkit.bounds.infinite.ImmutableInfiniteBound;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("xyz/derivora/intervalkit/bounds/infinite")
@DisplayName("Tests for ImmutableInfiniteBoundTest")
class ImmutableInfiniteBoundTest extends AbstractInfiniteBoundTest {

    @Override
    protected InfiniteBound createInfiniteBound(boolean isPositive) {
        return new ImmutableInfiniteBound(isPositive);
    }

    @Test
    @DisplayName("Should create a positive infinite bound when isPositive is true")
    void constructor_withTrue_createsPositiveBound() {
        InfiniteBound bound = new ImmutableInfiniteBound(true);
        boolean isPositive = bound.isPositive();
        assertTrue(isPositive);
    }

    @Test
    @DisplayName("Should create a negative infinite bound when isPositive is false")
    void constructor_withFalse_createsNegativeBound() {
        InfiniteBound bound = new ImmutableInfiniteBound(false);
        boolean isNegative = bound.isNegative();
        assertTrue(isNegative);
    }
}
