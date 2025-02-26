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

import xyz.derivora.intervalkit.bounds.FiniteBound;
import xyz.derivora.intervalkit.bounds.InfiniteBound;
import xyz.derivora.intervalkit.bounds.finite.ImmutableFiniteBound;
import xyz.derivora.intervalkit.bounds.infinite.ImmutableInfiniteBound;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * A factory for creating immutable boundary instances.
 * <p>
 * This class provides methods for creating both finite and infinite boundary instances.
 * It follows the singleton pattern, ensuring that only one instance of this factory
 * is available via {@link #getInstance()}.
 * </p>
 *
 * <p>
 * This class is {@code package-private} and should only be accessed via the
 * {@link BoundFactory} interface.
 * </p>
 *
 * @see BoundFactory
 * @see FiniteBound
 * @see InfiniteBound
 */
class ImmutableBoundFactory implements BoundFactory {

    /**
     * A singleton instance of {@link ImmutableBoundFactory}.
     */
    private static final ImmutableBoundFactory SINGLETON = new ImmutableBoundFactory();

    /**
     * Private constructor to enforce the singleton pattern.
     * <p>
     * This constructor prevents external instantiation of {@link ImmutableBoundFactory},
     * ensuring that the only accessible instance is the singleton provided by
     * {@link #getInstance()}.
     * </p>
     */
    private ImmutableBoundFactory() {
    }

    /**
     * Returns the singleton instance of {@link ImmutableBoundFactory}.
     * <p>
     * This method provides access to a single, reusable instance of
     * {@link ImmutableBoundFactory}, ensuring efficient and consistent bound comparisons.
     * </p>
     *
     * @return the singleton instance of {@link ImmutableBoundFactory}
     */
    static ImmutableBoundFactory getInstance() {
        return SINGLETON;
    }

    /**
     * Creates an infinite boundary with the specified sign.
     * <p>
     * This method returns an instance of {@link ImmutableInfiniteBound} representing
     * either positive or negative infinity based on the given {@code isPositive} flag.
     * </p>
     *
     * @param isPositive {@code true} for positive infinity, {@code false} for negative infinity
     * @return an {@link InfiniteBound} representing either positive or negative infinity
     */
    @Override
    public InfiniteBound getInfiniteBound(boolean isPositive) {
        return new ImmutableInfiniteBound(isPositive);
    }

    /**
     * Creates a finite boundary from the given numeric value.
     * <p>
     * This method checks whether the provided {@link Number} is a valid implementation
     * of {@code Comparable<T>} where {@code T} matches the number's runtime type.
     * If the number is valid, it is wrapped in an {@link ImmutableFiniteBound}.
     * </p>
     *
     * @param number the numeric value to wrap in a finite boundary; must not be {@code null}
     * @return a {@link FiniteBound} representing the given number
     * @throws NullPointerException if {@code number} is {@code null}
     * @throws ClassCastException   if {@code number} does not implement {@code Comparable<T>}
     */
    @Override
    public FiniteBound<?> getFiniteBound(Number number) {
        Objects.requireNonNull(number, "Number cannot be null");
        if (isValidComparable(number)) {
            return createFiniteBound(number);
        }

        throw new ClassCastException(createClassCastExceptionMessage(number));
    }

    /**
     * Constructs an error message for a {@link ClassCastException} when a numeric value
     * does not implement {@code Comparable<T>}.
     * <p>
     * This method generates a detailed message indicating that the provided {@link Number}
     * cannot be used to create a {@link FiniteBound} because its class does not implement
     * {@code Comparable<T>}, where {@code T} matches the number's own type.
     * </p>
     *
     * @param number the numeric value that caused the exception; must not be {@code null}
     * @return a formatted error message describing the incompatibility
     * @throws NullPointerException if {@code number} is {@code null}
     */
    private static String createClassCastExceptionMessage(Number number) {
        Class<?> clazz = number.getClass();
        return String.format(
                "Cannot create a finite bound: %s does not implement Comparable<%s>",
                clazz.getName(),
                clazz.getSimpleName()
        );
    }

    /**
     * Creates a finite boundary from the given numeric value.
     * <p>
     * This method assumes that the provided {@link Number} is a valid instance of
     * {@code T}, where {@code T} extends both {@link Number} and {@link Comparable<T>}.
     * It performs an unchecked cast and wraps the value in an {@link ImmutableFiniteBound}.
     * </p>
     *
     * <p><strong>Warning:</strong> This method suppresses unchecked cast warnings and relies on the caller
     * to ensure type safety. Improper usage may lead to {@link ClassCastException} at runtime.</p>
     *
     * @param number the numeric value to wrap in a finite boundary
     * @param <T>    the target numeric type that extends {@link Number} and {@link Comparable}
     * @return a {@link FiniteBound} representing the given number
     * @throws ClassCastException if the cast to {@code T} is invalid at runtime
     */
    @SuppressWarnings("unchecked")
    private <T extends Number & Comparable<T>> FiniteBound<T> createFiniteBound(Number number) {
        return new ImmutableFiniteBound<>((T) number);
    }

    /**
     * Checks whether the given {@link Number} explicitly implements {@code Comparable<T>}
     * with its own type as the generic parameter.
     * <p>
     * This method verifies whether the runtime class of the provided number
     * has an explicit declaration of {@code Comparable<T>} where {@code T} matches
     * the number's class. It does this by inspecting the generic interfaces of the class.
     * </p>
     *
     * <p>
     * The method ignores raw {@code Comparable} implementations (i.e., those without
     * a specific generic type parameter).
     * </p>
     *
     * @param number the numeric value to check; must not be {@code null}
     * @return {@code true} if the number explicitly implements {@code Comparable<T>} for its own type,
     * {@code false} otherwise
     */
    private static boolean isValidComparable(Number number) {
        Class<?> clazz = number.getClass();
        for (Type type : clazz.getGenericInterfaces()) {
            if (type instanceof ParameterizedType parameterizedType) {
                Type rawType = parameterizedType.getRawType();
                if (rawType != Comparable.class) {
                    continue;
                }

                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                if (typeArguments.length == 1 && typeArguments[0] == clazz) {
                    return true;
                }
            }
        }

        return false;
    }
}
