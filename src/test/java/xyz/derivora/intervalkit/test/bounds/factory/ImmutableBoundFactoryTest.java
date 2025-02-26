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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.bounds.factory.BoundFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("xyz/derivora/intervalkit/bounds/factory")
@DisplayName("Tests for ImmutableBoundFactory")
public class ImmutableBoundFactoryTest extends BoundFactoryTest {

    @Override
    protected BoundFactory createBoundFactory() {
        return BoundFactory.immutable();
    }

    @Test
    @DisplayName("Should return correct bound if number is valid")
    void getFiniteBound_withValidString_shouldReturnCorrectBound() {
        String number = "0";
        FiniteBound<?> result = BOUND_FACTORY.getFiniteBound(number);

        assertEquals(new BigDecimal(number), result.getValue());
        assertEquals(BigDecimal.class, result.getValueType());
    }
}
