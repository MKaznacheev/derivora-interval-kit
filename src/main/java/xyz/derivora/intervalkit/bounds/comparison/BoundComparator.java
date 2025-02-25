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

package xyz.derivora.intervalkit.bounds.comparison;

import xyz.derivora.intervalkit.bounds.Bound;
import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.bounds.InfiniteBound;
import xyz.derivora.utilkit.numbers.NumberComparator;

import java.util.Comparator;
import java.util.Objects;

/**
 * A comparator for {@link Bound} instances, providing a consistent ordering of finite and infinite boundaries.
 * <p>
 * This class defines a singleton comparator that can be used to compare different types of interval boundaries.
 * The comparison logic ensures that:
 * </p>
 * <ul>
 *     <li>Finite boundaries are compared based on their numeric values.</li>
 *     <li>Infinite boundaries are ordered according to their sign.</li>
 *     <li>Finite boundaries are always smaller than positive infinite boundaries and larger
 *         than negative infinite boundaries.</li>
 * </ul>
 *
 * <p>
 * This comparator relies on the assumption that:
 * </p>
 * <ul>
 *     <li>If a boundary is finite, it implements {@link FiniteBound}.</li>
 *     <li>If a boundary is infinite, it implements {@link InfiniteBound}.</li>
 * </ul>
 * If these assumptions are violated, an {@link IncomparableBoundsException} will be thrown.
 *
 * <p>
 * The singleton instance of this comparator is accessible via {@link #getInstance()}.
 * </p>
 *
 * @see Bound
 * @see FiniteBound
 * @see InfiniteBound
 * @see NumberComparator
 */
public final class BoundComparator implements Comparator<Bound> {

    /**
     * A singleton instance of {@link BoundComparator}.
     */
    private static final BoundComparator SINGLETON = new BoundComparator();

    /**
     * Private constructor to enforce the singleton pattern.
     * <p>
     * This constructor prevents external instantiation of {@link BoundComparator},
     * ensuring that the only accessible instance is the singleton provided by
     * {@link #getInstance()}.
     * </p>
     */
    private BoundComparator() {
    }

    /**
     * Returns the singleton instance of {@link BoundComparator}.
     * <p>
     * This method provides access to a single, reusable instance of
     * {@link BoundComparator}, ensuring efficient and consistent bound comparisons.
     * </p>
     *
     * @return the singleton instance of {@link BoundComparator}
     */
    public static BoundComparator getInstance() {
        return SINGLETON;
    }

    /**
     * Compares two boundaries to determine their relative ordering.
     * <p>
     * This method assumes that if a boundary is finite, it implements {@link FiniteBound}, and
     * if it is infinite, it implements {@link InfiniteBound}. If this assumption is violated,
     * an exception will be thrown.
     * </p>
     *
     * <p>
     * If the comparison cannot be performed due to an unexpected type or other issue,
     * an {@link IncomparableBoundsException} is thrown.
     * </p>
     *
     * @param firstBound  the first boundary to compare
     * @param secondBound the second boundary to compare
     * @return a negative integer, zero, or a positive integer as {@code firstBound} is less than, equal to,
     * or greater than {@code secondBound}
     * @throws NullPointerException        if either {@code firstBound} or {@code secondBound} is {@code null}
     * @throws IncomparableBoundsException if the comparison cannot be completed due to an unexpected issue
     */
    @Override
    public int compare(Bound firstBound, Bound secondBound) {
        Objects.requireNonNull(firstBound, "First bound cannot be null");
        Objects.requireNonNull(secondBound, "Second bound cannot be null");

        if (firstBound.equals(secondBound)) {
            return 0;
        }

        try {
            if (firstBound.isFinite()) {
                return compare((FiniteBound<?>) firstBound, secondBound);
            }

            return compare((InfiniteBound) firstBound, secondBound);
        } catch (Exception e) {
            throw new IncomparableBoundsException(firstBound, secondBound, e);
        }
    }

    /**
     * Compares a finite boundary with another boundary to determine their relative ordering.
     *
     * <p>
     * If {@code secondBound} is finite, it is expected to be an instance of {@link FiniteBound},
     * and an explicit cast is performed. If {@code secondBound} is infinite, it is expected to be an instance
     * of {@link InfiniteBound}, which is also cast accordingly. If these expectations are not met,
     * a {@link ClassCastException} will be thrown.
     * </p>
     *
     * @param firstBound  the finite bound to compare
     * @param secondBound the bound to compare against
     * @return a negative integer, zero, or a positive integer as {@code firstBound} is less than, equal to,
     * or greater than {@code secondBound}
     * @throws ClassCastException       if {@code secondBound} is finite but does not implement {@link FiniteBound},
     *                                  if it is infinite but does not implement {@link InfiniteBound},
     *                                  or if an unexpected type prevents a valid comparison
     * @throws IllegalArgumentException if a numeric conversion issue occurs during comparison
     */
    private static int compare(FiniteBound<?> firstBound, Bound secondBound) {
        if (secondBound.isFinite()) {
            Number firstValue = firstBound.getValue();
            Number secondValue = ((FiniteBound<?>) secondBound).getValue();

            return NumberComparator.getInstance().compare(firstValue, secondValue);
        }

        return -compare((InfiniteBound) secondBound, firstBound);
    }

    /**
     * Compares an infinite boundary with another boundary to determine their relative ordering.
     *
     * <p>
     * If {@code secondBound} is infinite but does not implement {@link InfiniteBound},
     * an exception will be thrown due to an invalid type cast.
     * </p>
     *
     * @param firstBound  the infinite bound to compare
     * @param secondBound the bound to compare against
     * @return {@code 1} if {@code firstBound} is positive and {@code secondBound} is finite,
     * {@code -1} if {@code firstBound} is negative and {@code secondBound} is finite,
     * or the result of {@link #compareInfiniteBounds(InfiniteBound, InfiniteBound)} if both are infinite
     * @throws ClassCastException if {@code secondBound} is infinite but does not implement {@link InfiniteBound}
     */
    private static int compare(InfiniteBound firstBound, Bound secondBound) {
        if (secondBound.isFinite()) {
            return firstBound.isPositive() ? 1 : -1;
        }

        return compareInfiniteBounds(firstBound, (InfiniteBound) secondBound);
    }

    /**
     * Compares two infinite boundaries to determine their relative ordering.
     * <p>
     * This method assumes that both boundaries are instances of {@link InfiniteBound} and determines their order based on
     * their sign.
     *
     * @param firstBound  the first infinite bound to compare
     * @param secondBound the second infinite bound to compare
     * @return {@code 0} if both bounds have the same sign, {@code -1} if the first is negative and the second is positive,
     * or {@code 1} if the first is positive and the second is negative
     */
    private static int compareInfiniteBounds(InfiniteBound firstBound, InfiniteBound secondBound) {
        if (firstBound.isNegative()) {
            return secondBound.isNegative() ? 0 : -1;
        }

        return secondBound.isPositive() ? 0 : 1;
    }
}
