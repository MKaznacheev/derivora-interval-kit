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

package xyz.derivora.intervalkit.bounds.infinite;

import xyz.derivora.intervalkit.bounds.InfiniteBound;

/**
 * An abstract implementation of {@link InfiniteBound}, providing common behavior for infinite boundaries.
 * <p>
 * This class ensures that all instances correctly implement the contract of {@link InfiniteBound},
 * making methods such as {@link #isFinite()} and {@link #isInfinite()} final to enforce consistent behavior.
 * </p>
 *
 * <p>
 * Concrete subclasses must implement {@link #isPositive()} to specify whether the bound represents
 * positive or negative infinity.
 * </p>
 *
 * @see InfiniteBound
 */
public abstract class AbstractInfiniteBound implements InfiniteBound {

    /**
     * Determines whether this infinite boundary is negative.
     * <p>
     * This method is the logical negation of {@link #isPositive()}.
     * </p>
     *
     * @return {@code true} if this boundary is negative, {@code false} otherwise
     */
    @Override
    public final boolean isNegative() {
        return InfiniteBound.super.isNegative();
    }

    /**
     * Determines whether this boundary is finite.
     * <p>
     * Since this boundary is infinite, this method always returns {@code false}.
     * </p>
     *
     * @return {@code false}, as all instances of {@code InfiniteBound} are infinite
     */
    @Override
    public final boolean isFinite() {
        return InfiniteBound.super.isFinite();
    }

    /**
     * Determines whether this boundary is infinite.
     * <p>
     * Since this boundary is infinite, this method always returns {@code true}.
     * </p>
     *
     * @return {@code true}, as all instances of {@code InfiniteBound} are infinite
     */
    @Override
    public final boolean isInfinite() {
        return InfiniteBound.super.isInfinite();
    }

    /**
     * Returns a string representation of the bound.
     * <p>
     * The returned string indicates whether this bound represents positive or negative infinity.
     * </p>
     *
     * @return a string representation of the bound in the format "Positive InfiniteBound" or "Negative InfiniteBound"
     */
    @Override
    public String toString() {
        String kind = isPositive() ? "Positive " : "Negative ";
        return kind.concat("InfiniteBound");
    }
}
