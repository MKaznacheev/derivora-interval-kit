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
 * The {@code derivora.interval.kit} module provides tools for working with numerical intervals.
 *
 *
 * <p>Currently, it includes:
 * <ul>
 *   <li>A structured representation of interval boundaries through the {@link xyz.derivora.intervalkit.bounds.Bound}
 *       interface and its specializations: {@link xyz.derivora.intervalkit.bounds.FiniteBound} and
 *       {@link xyz.derivora.intervalkit.bounds.InfiniteBound}.</li>
 * </ul>
 *
 * <p>This module is designed for use in mathematical computations, range-based operations, and
 * interval arithmetic, ensuring consistency and correctness in boundary handling.</p>
 */
module derivora.interval.kit {
    requires derivora.util.kit;

    exports xyz.derivora.intervalkit.bounds;
}