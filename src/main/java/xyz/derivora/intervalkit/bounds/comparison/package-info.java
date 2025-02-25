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

/**
 * Provides utilities for comparing interval boundaries.
 * <p>
 * This package contains components responsible for the comparison of {@link xyz.derivora.intervalkit.bounds.Bound} instances.
 * It ensures that finite and infinite boundaries are compared consistently, following a well-defined order.
 * </p>
 *
 * <p>Key components of this package include:</p>
 * <ul>
 *     <li>{@link xyz.derivora.intervalkit.bounds.comparison.BoundComparator} – a singleton comparator
 *         that defines a consistent ordering for finite and infinite boundaries.</li>
 *     <li>{@link xyz.derivora.intervalkit.bounds.comparison.IncomparableBoundsException} – an exception
 *         thrown when an attempt is made to compare two incompatible boundaries.</li>
 * </ul>
 *
 * <p>
 * The classes in this package rely on the assumption that:
 * </p>
 * <ul>
 *     <li>If a boundary is finite, it implements {@link xyz.derivora.intervalkit.bounds.FiniteBound}.</li>
 *     <li>If a boundary is infinite, it implements {@link xyz.derivora.intervalkit.bounds.InfiniteBound}.</li>
 * </ul>
 *
 * @see xyz.derivora.intervalkit.bounds.Bound
 * @see xyz.derivora.intervalkit.bounds.FiniteBound
 * @see xyz.derivora.intervalkit.bounds.InfiniteBound
 * @see xyz.derivora.intervalkit.bounds.comparison.BoundComparator
 * @see xyz.derivora.intervalkit.bounds.comparison.IncomparableBoundsException
 */
package xyz.derivora.intervalkit.bounds.comparison;