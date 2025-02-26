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
 * Provides implementations for finite interval boundaries.
 * <p>
 * This package defines concrete and abstract representations of finite bounds,
 * ensuring consistent handling of numeric interval boundaries with fixed values.
 * </p>
 *
 * <p>Key components include:</p>
 * <ul>
 *     <li>{@link xyz.derivora.intervalkit.bounds.finite.AbstractFiniteBound} – an abstract base
 *         class for finite boundaries, enforcing correct behavior for methods related to finiteness.</li>
 *     <li>{@link xyz.derivora.intervalkit.bounds.finite.ImmutableFiniteBound} – a final,
 *         immutable implementation of a finite boundary, storing a fixed numeric value.</li>
 * </ul>
 *
 * <p>
 * These classes provide a structured and type-safe approach to working with finite bounds,
 * ensuring logical consistency and compatibility with interval arithmetic.
 * </p>
 *
 * @see xyz.derivora.intervalkit.bounds.finite.AbstractFiniteBound
 * @see xyz.derivora.intervalkit.bounds.finite.ImmutableFiniteBound
 */
package xyz.derivora.intervalkit.bounds.finite;