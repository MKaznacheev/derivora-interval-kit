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
 * Provides a factory interface for creating boundary instances.
 * <p>
 * This package defines a factory for constructing both finite and infinite boundaries,
 * ensuring a structured approach to boundary instantiation.
 * </p>
 *
 * <h2>Key Component:</h2>
 * <ul>
 *     <li>{@link xyz.derivora.intervalkit.bounds.factory.BoundFactory} -
 *         An interface for creating {@link xyz.derivora.intervalkit.bounds.Bound} instances.</li>
 * </ul>
 *
 * <p>
 * The factory allows the creation of finite boundaries from numeric values
 * and infinite boundaries representing positive or negative infinity.
 * </p>
 */
package xyz.derivora.intervalkit.bounds.factory;