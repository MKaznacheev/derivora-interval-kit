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

import java.util.Objects;

/**
 * Represents an immutable infinite boundary with a fixed sign.
 * <p>
 * This class provides a concrete implementation of {@link AbstractInfiniteBound},
 * allowing the creation of immutable positive or negative infinite boundaries.
 * Instances of this class are final and cannot be modified after creation.
 * </p>
 *
 * @see AbstractInfiniteBound
 */
public class ImmutableInfiniteBound extends AbstractInfiniteBound {

    /**
     * Indicates whether this infinite boundary represents positive infinity.
     */
    protected final boolean isPositive;

    /**
     * Creates an immutable infinite boundary with the specified sign.
     * <p>
     * If {@code isPositive} is {@code true}, the instance represents positive infinity.
     * If {@code isPositive} is {@code false}, the instance represents negative infinity.
     * </p>
     *
     * @param isPositive {@code true} for positive infinity, {@code false} for negative infinity
     */
    public ImmutableInfiniteBound(boolean isPositive) {
        this.isPositive = isPositive;
    }

    /**
     * Determines whether this infinite boundary is positive.
     *
     * @return {@code true} if this boundary is positive, {@code false} otherwise
     */
    @Override
    public final boolean isPositive() {
        return isPositive;
    }

    /**
     * Returns the hash code for this infinite bound.
     * <p>
     * The hash code is based solely on the {@code isPositive} field, ensuring consistency
     * with {@link #equals(Object)}. Instances with the same sign will have the same hash code.
     * </p>
     *
     * @return the hash code of this infinite bound, derived from {@code isPositive}
     * @see Boolean#hashCode(boolean)
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(isPositive);
    }

    /**
     * Checks whether this infinite bound is equal to another object.
     * <p>
     * Two instances are considered equal if and only if they belong to the exact same class
     * and represent the same type of infinity (i.e., both positive or both negative).
     * </p>
     *
     * <p>
     * This implementation does not allow equality between subclasses and the base class,
     * as it uses strict type checking via {@code getClass()} instead of {@code instanceof}.
     * </p>
     *
     * @param object the object to compare with this instance
     * @return {@code true} if the given object is of the same class and has the same sign, {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ImmutableInfiniteBound other = (ImmutableInfiniteBound) object;
        return isPositive == other.isPositive;
    }
}
