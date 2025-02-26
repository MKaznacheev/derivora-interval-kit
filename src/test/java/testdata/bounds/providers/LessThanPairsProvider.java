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

import static testdata.bounds.providers.NegativeInfiniteBoundsProvider.NEGATIVE_INFINITE_BOUNDS;
import static testdata.bounds.providers.NegativeOneBoundsProvider.NEGATIVE_ONE_BOUNDS;
import static testdata.bounds.providers.OneBoundsProvider.ONE_BOUNDS;
import static testdata.bounds.providers.PositiveInfiniteBoundsProvider.POSITIVE_INFINITE_BOUNDS;
import static testdata.bounds.providers.ZeroBoundsProvider.ZERO_BOUNDS;

public class LessThanPairsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Stream<Arguments> negativeOneToZero = generateBoundPairs(NEGATIVE_ONE_BOUNDS, ZERO_BOUNDS);
        Stream<Arguments> negativeOneToOne = generateBoundPairs(NEGATIVE_ONE_BOUNDS, ONE_BOUNDS);
        Stream<Arguments> zeroToOne = generateBoundPairs(ZERO_BOUNDS, ONE_BOUNDS);

        Stream<Arguments> negativeInfinityToNegativeOne = generateBoundPairs(NEGATIVE_INFINITE_BOUNDS, NEGATIVE_ONE_BOUNDS);
        Stream<Arguments> negativeInfinityToZero = generateBoundPairs(NEGATIVE_INFINITE_BOUNDS, ZERO_BOUNDS);
        Stream<Arguments> negativeInfinityToOne = generateBoundPairs(NEGATIVE_INFINITE_BOUNDS, ONE_BOUNDS);

        Stream<Arguments> negativeOneToPositiveInfinity = generateBoundPairs(NEGATIVE_ONE_BOUNDS, POSITIVE_INFINITE_BOUNDS);
        Stream<Arguments> zeroToPositiveInfinity = generateBoundPairs(ZERO_BOUNDS, POSITIVE_INFINITE_BOUNDS);
        Stream<Arguments> oneToPositiveInfinity = generateBoundPairs(ONE_BOUNDS, POSITIVE_INFINITE_BOUNDS);

        return Stream.of(
                negativeOneToZero,
                negativeOneToOne,
                zeroToOne,
                negativeInfinityToNegativeOne,
                negativeInfinityToZero,
                negativeInfinityToOne,
                negativeOneToPositiveInfinity,
                zeroToPositiveInfinity,
                oneToPositiveInfinity
        ).flatMap(s -> s);
    }

    static Stream<Arguments> generateBoundPairs(Bound[] firstArr, Bound[] secondArr) {
        int pairsCount = firstArr.length * secondArr.length;
        Bound[][] pairs = new Bound[pairsCount][2];

        for (int i = 0; i < firstArr.length; i++) {
            for (int j = 0; j < secondArr.length; j++) {
                int pos = i * secondArr.length + j;
                pairs[pos] = new Bound[]{firstArr[i], secondArr[j]};
            }
        }

        return Arrays.stream(pairs).map(Arguments::of);
    }
}
