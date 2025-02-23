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

package xyz.derivora.intervalkit.bounds;

/**
 * Represents an infinite boundary of an interval.
 * <p>
 * Unlike {@link FiniteBound}, instances of this interface represent boundaries that extend infinitely
 * in either the positive or negative direction. This distinction is crucial for interval operations
 * and comparisons.
 * </p>
 *
 * <p>
 * Implementations of this interface must provide a consistent definition of the sign of the boundary.
 * The methods {@link #isPositive()} and {@link #isNegative()} ensure that an instance of
 * {@code InfiniteBound} is either strictly positive or strictly negative, never both.
 * </p>
 *
 * <p>
 * Since all instances of {@code InfiniteBound} represent infinite boundaries, the method
 * {@link #isFinite()} always returns {@code false}.
 * </p>
 *
 * @see FiniteBound
 * @see Bound
 */
public non-sealed interface InfiniteBound extends Bound {

    /**
     * Determines whether this infinite boundary is positive.
     * <p>
     * The sign of an infinite boundary is consistent for the same instance. If this method returns {@code true},
     * then {@link #isNegative()} must return {@code false}, ensuring logical consistency.
     * </p>
     *
     * @return {@code true} if this boundary is positive, {@code false} otherwise
     */
    boolean isPositive();

    /**
     * Determines whether this infinite boundary is negative.
     * <p>
     * This method is the logical negation of {@link #isPositive()}. An implementation must guarantee that
     * both methods return opposite values for the same instance.
     * </p>
     *
     * @return {@code true} if this boundary is negative, {@code false} otherwise
     */
    default boolean isNegative() {
        return !isPositive();
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
    default boolean isFinite() {
        return false;
    }
}
