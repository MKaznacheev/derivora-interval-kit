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
 * Provides implementations for infinite interval boundaries.
 * <p>
 * This package defines concrete and abstract representations of infinite bounds,
 * ensuring consistent handling of positive and negative infinities.
 * </p>
 *
 * <p>Key components include:</p>
 * <ul>
 *     <li>{@link xyz.derivora.intervalkit.bounds.infinite.AbstractInfiniteBound} – an abstract base
 *         class for infinite boundaries, enforcing correct behavior for infinity-related methods.</li>
 *     <li>{@link xyz.derivora.intervalkit.bounds.infinite.ImmutableInfiniteBound} – an immutable
 *         implementation of an infinite boundary, representing either positive or negative infinity.</li>
 * </ul>
 *
 * <p>
 * These classes provide a structured and type-safe approach to working with infinite bounds,
 * ensuring logical consistency and compatibility with interval arithmetic.
 * </p>
 *
 * @see xyz.derivora.intervalkit.bounds.infinite.AbstractInfiniteBound
 * @see xyz.derivora.intervalkit.bounds.infinite.ImmutableInfiniteBound
 */
package xyz.derivora.intervalkit.bounds.infinite;