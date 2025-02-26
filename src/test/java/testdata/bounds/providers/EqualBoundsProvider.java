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
import xyz.derivora.intervalkit.bounds.Bound;

import java.util.Arrays;
import java.util.stream.Stream;

import static testdata.bounds.providers.NegativeOneBoundsProvider.NEGATIVE_ONE_BOUNDS;
import static testdata.bounds.providers.OneBoundsProvider.ONE_BOUNDS;
import static testdata.bounds.providers.ZeroBoundsProvider.ZERO_BOUNDS;

public class EqualBoundsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Stream<Arguments> negativeStream = generateBoundPairs(NEGATIVE_ONE_BOUNDS);
        Stream<Arguments> zeroesStream = generateBoundPairs(ZERO_BOUNDS);
        Stream<Arguments> positiveStream = generateBoundPairs(ONE_BOUNDS);

        return Stream.of(negativeStream, zeroesStream, positiveStream)
                     .flatMap(s -> s);
    }

    static Stream<Arguments> generateBoundPairs(Bound[] bounds) {
        int boundsCount = bounds.length;
        // Number of combinations of elementsCount taken 2 at a time
        int pairsCount = (boundsCount - 1) * boundsCount / 2;
        Bound[][] pairs = new Bound[pairsCount][2];

        int processedPairsCount = 0;
        for (int i = 0; i < boundsCount; i++) {
            for (int j = i + 1; j < boundsCount; j++) {
                int pos = processedPairsCount + j - i - 1;
                pairs[pos] = new Bound[]{bounds[i], bounds[j]};
            }
            processedPairsCount += boundsCount - i - 1;
        }

        return Arrays.stream(pairs).map(Arguments::of);
    }
}
