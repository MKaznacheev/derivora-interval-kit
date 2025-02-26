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

package xyz.derivora.intervalkit.bounds.factory;

import xyz.derivora.intervalkit.bounds.Bound;
import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.bounds.InfiniteBound;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A factory interface for creating instances of {@link Bound}.
 * <p>
 * This interface provides methods for creating both finite and infinite boundaries,
 * ensuring a structured approach to boundary instantiation.
 * </p>
 *
 * @see Bound
 * @see FiniteBound
 * @see InfiniteBound
 */
public interface BoundFactory {

    /**
     * Creates an infinite boundary with the specified sign.
     * <p>
     * This method returns an instance of {@link InfiniteBound} representing either
     * positive or negative infinity based on the given {@code isPositive} flag.
     * </p>
     *
     * @param isPositive {@code true} for positive infinity, {@code false} for negative infinity
     * @return an {@link InfiniteBound} representing either positive or negative infinity
     */
    InfiniteBound getInfiniteBound(boolean isPositive);

    /**
     * Returns an infinite boundary representing positive infinity.
     * <p>
     * This method is a shortcut for {@code getInfiniteBound(true)}, providing a more
     * explicit and readable way to obtain a positive infinite bound.
     * </p>
     *
     * @return an {@link InfiniteBound} representing positive infinity
     * @see #getInfiniteBound(boolean)
     */
    default InfiniteBound getPositiveInfiniteBound() {
        return getInfiniteBound(true);
    }

    /**
     * Returns an infinite boundary representing negative infinity.
     * <p>
     * This method is a shortcut for {@code getInfiniteBound(false)}, providing a more
     * explicit and readable way to obtain a negative infinite bound.
     * </p>
     *
     * @return an {@link InfiniteBound} representing negative infinity
     * @see #getInfiniteBound(boolean)
     */
    default InfiniteBound getNegativeInfiniteBound() {
        return getInfiniteBound(false);
    }

    /**
     * Creates a finite boundary from the given numeric value.
     * <p>
     * This method converts the provided {@link Number} into a {@link FiniteBound} instance
     * while ensuring that it is a valid {@code Comparable<T>} type. The resulting boundary
     * retains the precision and characteristics of the original number.
     * </p>
     *
     * <p>
     * The returned {@code FiniteBound} will be parameterized with a numeric type that
     * extends both {@link Number} and {@link Comparable}. If the provided number does
     * not implement {@code Comparable<T>}, a {@link ClassCastException} is thrown.
     * </p>
     *
     * @param number the numeric value to convert into a finite boundary; must not be {@code null}
     * @return a {@link FiniteBound} representing the given number
     * @throws NullPointerException if {@code number} is {@code null}
     * @throws ClassCastException if {@code number} does not implement {@code Comparable<T>}
     */
    FiniteBound<?> getFiniteBound(Number number);

    /**
     * Creates a finite boundary from the given numeric string representation.
     * <p>
     * This method converts the provided string into a {@link BigDecimal}
     * and delegates to {@link #getFiniteBound(Number)} to create a {@link FiniteBound} instance.
     * The resulting boundary retains the precision of {@code BigDecimal}.
     * </p>
     *
     * @param number the string representation of the numeric value; must not be {@code null}
     * @return a {@link FiniteBound} representing the given number
     * @throws NullPointerException if {@code number} is {@code null}
     * @throws NumberFormatException if {@code number} is not a valid numeric representation
     */
    default FiniteBound<?> getFiniteBound(String number) {
        return getFiniteBound(new BigDecimal(
                 Objects.requireNonNull(number, "Number cannot be null")
        ));
    }

    /**
     * Returns a shared immutable instance of {@code BoundFactory}.
     * <p>
     * This method provides access to a singleton instance of an immutable
     * factory implementation, ensuring efficient and consistent creation
     * of immutable {@link Bound} objects.
     * </p>
     *
     * @return a shared instance of an immutable {@code BoundFactory}
     */
    static BoundFactory immutable() {
        return ImmutableBoundFactory.getInstance();
    }
}
