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
 * Represents a boundary of an interval, which can be either finite or infinite.
 * <p>
 * This interface is declared as {@code sealed} to prevent the creation of unintended
 * subclasses that could violate the principles defined for boundary comparison and classification.
 * Implementations are restricted to {@link FiniteBound} and {@link InfiniteBound}, ensuring
 * that every boundary adheres to a well-defined structure and behavior.
 * </p>
 *
 * <p>
 * The contract of this interface is strictly defined by its methods:
 * <ul>
 *   <li>{@link #isFinite()} determines whether the boundary is finite.</li>
 *   <li>{@link #isInfinite()} is the logical negation of {@code isFinite()}, ensuring consistency.</li>
 * </ul>
 * </p>
 *
 * <p>
 * These principles ensure the correctness and predictability of boundary behavior,
 * preventing ambiguities in interval operations.
 * </p>
 *
 * @see FiniteBound
 * @see InfiniteBound
 */
public sealed interface Bound extends Comparable<Bound> permits FiniteBound, InfiniteBound {

    /**
     * Determines whether this boundary is finite.
     * <p>
     * A return value of {@code true} indicates that this boundary implements the {@link FiniteBound} interface,
     * while a return value of {@code false} indicates that it implements the {@link InfiniteBound} interface.
     * </p>
     *
     * @return {@code true} if this boundary is finite, {@code false} otherwise
     */
    boolean isFinite();

    /**
     * Determines whether this boundary is infinite.
     * <p>
     * This method returns the logical negation of {@link #isFinite()}.
     * A return value of {@code true} indicates that this boundary implements the {@link InfiniteBound} interface,
     * while a return value of {@code false} indicates that it implements the {@link FiniteBound} interface.
     * </p>
     *
     * @return {@code true} if this boundary is infinite, {@code false} otherwise
     */
    default boolean isInfinite() {
        return !isFinite();
    }
}
