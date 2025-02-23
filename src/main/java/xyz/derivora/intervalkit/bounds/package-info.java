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
 * Provides abstractions for representing and handling interval boundaries.
 * <p>
 * This package defines the {@link xyz.derivora.intervalkit.bounds.Bound} interface, which serves as a common
 * representation of interval boundaries. Boundaries can be either:
 * </p>
 * <ul>
 *     <li>{@link xyz.derivora.intervalkit.bounds.FiniteBound} – represents a boundary with a specific numeric value.</li>
 *     <li>{@link xyz.derivora.intervalkit.bounds.InfiniteBound} – represents an infinitely extending boundary, either positive or negative.</li>
 * </ul>
 *
 * <p>
 * The {@code Bound} interface is declared as {@code sealed} to enforce a strict classification of
 * boundaries, preventing unintended extensions that could violate the comparison logic.
 * </p>
 *
 * <p>
 * The classes and interfaces in this package are essential for interval arithmetic, ensuring
 * well-defined operations on bounded and unbounded intervals.
 * </p>
 *
 * @see xyz.derivora.intervalkit.bounds.Bound
 * @see xyz.derivora.intervalkit.bounds.FiniteBound
 * @see xyz.derivora.intervalkit.bounds.InfiniteBound
 */
package xyz.derivora.intervalkit.bounds;