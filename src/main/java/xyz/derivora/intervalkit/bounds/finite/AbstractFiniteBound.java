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

package xyz.derivora.intervalkit.bounds.finite;

import xyz.derivora.intervalkit.bounds.FiniteBound;

/**
 * An abstract implementation of {@link FiniteBound}, providing a base for finite interval boundaries.
 * <p>
 * This class ensures that all finite boundaries consistently implement the contract of {@link FiniteBound},
 * enforcing methods such as {@link #isFinite()} and {@link #isInfinite()} to always return {@code true} and
 * {@code false} respectively.
 * </p>
 *
 * <p>
 * Concrete subclasses must implement {@link FiniteBound#getValue()} and {@link FiniteBound#getValueType()}
 * to define the specific numeric value and its type.
 * </p>
 *
 * <p>
 * The {@link #toString()} method provides a formatted string representation of the boundary, including its type
 * and value, to enhance readability and debugging.
 * </p>
 *
 * @param <T> the numeric type of the finite boundary, which must extend {@link Number} and implement {@link Comparable}
 * @see FiniteBound
 */
public abstract class AbstractFiniteBound<T extends Number & Comparable<T>> implements FiniteBound<T> {

    /**
     * Determines whether this boundary is finite.
     * <p>
     * Since this boundary is finite, this method always returns {@code true}.
     * </p>
     *
     * @return {@code true}, as all instances of {@code FiniteBound} are finite
     */
    @Override
    public final boolean isFinite() {
        return FiniteBound.super.isFinite();
    }

    /**
     * Determines whether this boundary is infinite.
     * <p>
     * Since this boundary is finite, this method always returns {@code false}.
     * </p>
     *
     * @return {@code false}, as all instances of {@code FiniteBound} are finite
     */
    @Override
    public final boolean isInfinite() {
        return FiniteBound.super.isInfinite();
    }

    /**
     * Returns a string representation of this finite boundary.
     * <p>
     * The returned string includes the type and value of the finite boundary
     * in the following format:
     * </p>
     *
     * <pre>
     * FiniteBound{FullyQualifiedTypeName: value}
     * </pre>
     *
     * <p>For example, if the boundary stores a {@code Double} value of {@code 3.14},
     * the output might be:</p>
     *
     * <pre>
     * FiniteBound{java.lang.Double: 3.14}
     * </pre>
     *
     * @return a string representation of this finite boundary, including its type and value
     */
    @Override
    public String toString() {
        return String.format("FiniteBound{%s: %s}", getValueType().getName(), getValue());
    }
}
