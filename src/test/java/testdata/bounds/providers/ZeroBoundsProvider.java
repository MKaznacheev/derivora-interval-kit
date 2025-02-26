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

package testdata.bounds.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import testdata.numbers.ComparableNumber;
import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.bounds.finite.ImmutableFiniteBound;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class ZeroBoundsProvider implements ArgumentsProvider {

    static final FiniteBound<?>[] ZERO_BOUNDS = {
            new ImmutableFiniteBound<>((byte) 0),
            new ImmutableFiniteBound<>((short) 0),
            new ImmutableFiniteBound<>(0),
            new ImmutableFiniteBound<>(0L),
            new ImmutableFiniteBound<>(0.0f),
            new ImmutableFiniteBound<>(0.0),
            new ImmutableFiniteBound<>(BigInteger.ZERO),
            new ImmutableFiniteBound<>(BigDecimal.ZERO),
            new ImmutableFiniteBound<>(ComparableNumber.ZERO)
    };

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Arrays.stream(ZERO_BOUNDS).map(Arguments::of);
    }
}
