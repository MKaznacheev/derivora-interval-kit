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
import xyz.derivora.intervalkit.bounds.InfiniteBound;
import xyz.derivora.intervalkit.bounds.infinite.ImmutableInfiniteBound;

import java.util.Arrays;
import java.util.stream.Stream;

public class PositiveInfiniteBoundsProvider implements ArgumentsProvider {

    static final InfiniteBound[] POSITIVE_INFINITE_BOUNDS = {
            new ImmutableInfiniteBound(true)
    };

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Arrays.stream(POSITIVE_INFINITE_BOUNDS).map(Arguments::of);
    }
}
