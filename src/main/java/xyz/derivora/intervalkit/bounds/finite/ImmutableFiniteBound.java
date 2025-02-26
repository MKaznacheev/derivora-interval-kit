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

package xyz.derivora.intervalkit.bounds.finite;

import xyz.derivora.utilkit.validation.ValidationException;
import xyz.derivora.utilkit.validation.Validator;

import java.util.Objects;

/**
 * Represents an immutable finite boundary with a fixed numeric value.
 * <p>
 * This class extends {@link AbstractFiniteBound} and provides a concrete, immutable
 * implementation of a finite boundary. The boundary's numeric value and its type are
 * determined at construction and cannot be modified.
 * </p>
 *
 * <p>
 * If the provided value is of type {@code Double} or {@code Float}, additional validation
 * is performed to ensure that it is neither infinite nor {@code NaN}. If validation fails,
 * a {@link ValidationException} is thrown.
 * </p>
 *
 * <p>
 * Instances of this class are compared based on both their numeric value and their type.
 * The equality check is strict, meaning subclass instances are not considered equal to
 * base class instances.
 * </p>
 *
 * @param <T> the numeric type of the finite boundary, which must extend {@link Number}
 *            and implement {@link Comparable}
 * @see AbstractFiniteBound
 */
public class ImmutableFiniteBound<T extends Number & Comparable<T>> extends AbstractFiniteBound<T> {

    /**
     * The numeric value of this finite boundary.
     * <p>
     * This value is immutable.
     * </p>
     */
    protected final T value;

    /**
     * The runtime type of the finite boundary's value.
     */
    protected final Class<? extends T> type;

    /**
     * Creates an immutable finite boundary with the specified numeric value.
     * <p>
     * The value must not be {@code null}. If the value is of type {@code Double} or {@code Float},
     * additional validation is performed to ensure that it is neither infinite nor {@code NaN}.
     * If the validation fails, a {@link ValidationException} is thrown.
     * </p>
     *
     * <p>
     * The type of the finite bound is inferred from the runtime class of the provided value.
     * </p>
     *
     * @param value the numeric value of this finite bound; must not be {@code null}
     * @throws NullPointerException if {@code value} is {@code null}
     * @throws ValidationException if the value is {@code Double.POSITIVE_INFINITY},
     *                             {@code Double.NEGATIVE_INFINITY}, or {@code Double.NaN}
     */
    @SuppressWarnings("unchecked")
    public ImmutableFiniteBound(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        this.value = value;
        type = (Class<? extends T>) value.getClass();

        if (Double.class.isAssignableFrom(type) || Float.class.isAssignableFrom(type)) {
            FiniteValidator finiteValidator = new FiniteValidator();
            finiteValidator.validate(value.doubleValue());
        }
    }

    /**
     * Returns the numeric value of this finite boundary.
     * <p>
     * This value defines the exact position of the boundary within the finite number set.
     * </p>
     *
     * @return the numeric value of this boundary
     */
    @Override
    public final T getValue() {
        return value;
    }

    /**
     * Returns the runtime type of the numeric value associated with this boundary.
     * <p>
     * The returned class represents the specific numeric type of the value stored in this finite boundary.
     * </p>
     *
     * @return the {@link Class} object representing the type of the boundary value
     */
    @Override
    public final Class<? extends T> getValueType() {
        return type;
    }

    /**
     * Returns the hash code for this finite bound.
     * <p>
     * The hash code is computed based on the numeric value and its type,
     * ensuring consistency with {@link #equals(Object)}. Two instances with the same
     * value and type will have the same hash code.
     * </p>
     *
     * @return the hash code of this finite bound, derived from its value and type
     * @see Objects#hash(Object...)
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    /**
     * Checks whether this finite bound is equal to another object.
     * <p>
     * Two instances of {@code ImmutableFiniteBound} are considered equal if and only if:
     * </p>
     * <ul>
     *     <li>They are of the exact same class (subclass instances are not considered equal).</li>
     *     <li>Their numeric values are equal.</li>
     *     <li>Their numeric types are equal.</li>
     * </ul>
     *
     * <p>
     * This implementation enforces strict type checking using {@code getClass()} instead of {@code instanceof},
     * meaning that subclasses of {@code ImmutableFiniteBound} will not be considered equal to instances of this class.
     * </p>
     *
     * @param object the object to compare with this instance
     * @return {@code true} if the given object is of the same class, has the same value, and the same type;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ImmutableFiniteBound<?> other = (ImmutableFiniteBound<?>) object;
        return Objects.equals(value, other.value) && Objects.equals(type, other.type);
    }

    /**
     * A validator for finite boundary values.
     * <p>
     * This validator ensures that a given {@code Double} value is a valid finite number.
     * Specifically, it rejects values that are {@code Double.POSITIVE_INFINITY},
     * {@code Double.NEGATIVE_INFINITY}, or {@code Double.NaN}.
     * </p>
     *
     * <p>If an invalid value is provided, a {@link ValidationException} is thrown.</p>
     *
     * @see Validator
     * @see ValidationException
     */
    private static final class FiniteValidator implements Validator<Double> {

        /**
         * Validates that the provided value is a finite number.
         * <p>
         * This method checks whether the given {@code Double} value is either infinite
         * or {@code NaN}. If the validation fails, a {@link ValidationException} is thrown.
         * </p>
         *
         * @param arg the value to validate
         * @throws ValidationException if {@code arg} is {@code Double.POSITIVE_INFINITY},
         *                             {@code Double.NEGATIVE_INFINITY}, or {@code Double.NaN}
         */
        @Override
        public void validate(Double arg) {
            if (arg.isInfinite() || arg.isNaN()) {
                throw new ValidationException("Value of finite bound cannot be infinite or NaN");
            }
        }
    }
}
