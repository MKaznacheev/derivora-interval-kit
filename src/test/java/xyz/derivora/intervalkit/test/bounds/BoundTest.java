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

package xyz.derivora.intervalkit.test.bounds;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import xyz.derivora.intervalkit.bounds.Bound;

@Tag("xyz/derivora/intervalkit/bounds")
@Tag("base-test")
@DisplayName("Base tests for Bound")
@Disabled("This is a base test class and should not be executed directly")
public abstract class BoundTest extends BoundComparisonTest {

    @Override
    protected final int compare(Bound firstBound, Bound secondBound) {
        return firstBound.compareTo(secondBound);
    }
}
