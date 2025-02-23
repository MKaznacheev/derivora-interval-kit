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
 * Represents a finite boundary of an interval.
 * <p>
 * Unlike {@link InfiniteBound}, instances of this interface are associated with a specific numeric value,
 * which defines the boundary within the finite number set. The type of this value is determined by the
 * generic parameter {@code T}, which must extend both {@link Number} and {@link Comparable}.
 * </p>
 *
 * <p>
 * The methods {@link #getValue()} and {@link #getValueType()} provide access to the boundary value and its type,
 * respectively.
 * </p>
 *
 * <p>
 * Since all instances of {@code FiniteBound} represent finite boundaries, the method {@link #isFinite()}
 * always returns {@code true}.
 * </p>
 *
 * @param <T> the numeric type of the finite boundary value, which must extend {@link Number} and {@link Comparable}
 * @see InfiniteBound
 * @see Bound
 */
public non-sealed interface FiniteBound<T extends Number & Comparable<T>> extends Bound {

    /**
     * Returns the numeric value of this finite boundary.
     * <p>
     * This value defines the exact position of the boundary within the finite number set.
     * The returned value must remain immutable for a given instance.
     * </p>
     *
     * @return the numeric value of this boundary
     */
    T getValue();

    /**
     * Returns the runtime type of the numeric value associated with this boundary.
     * <p>
     * The returned class represents the specific numeric type of the value stored in this finite boundary.
     * </p>
     *
     * @return the {@link Class} object representing the type of the boundary value
     */
    Class<? extends T> getValueType();

    /**
     * Determines whether this boundary is finite.
     * <p>
     * Since this boundary is finite, this method always returns {@code true}.
     * </p>
     *
     * @return {@code true}, as all instances of {@code FiniteBound} are finite
     */
    @Override
    default boolean isFinite() {
        return true;
    }
}
