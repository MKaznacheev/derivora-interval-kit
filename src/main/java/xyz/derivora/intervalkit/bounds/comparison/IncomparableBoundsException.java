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

import java.io.Serial;


/**
 * Thrown to indicate that two {@link Bound} instances cannot be compared.
 * <p>
 * This exception is used when an attempt is made to compare two interval boundaries
 * that do not have a well-defined ordering. It ensures that such cases are explicitly
 * handled rather than leading to unpredictable behavior.
 * </p>
 *
 * <p>
 * The exception can optionally store the two bounds that were involved in the failed
 * comparison, allowing detailed debugging information.
 * </p>
 *
 * @see Bound
 */
public class IncomparableBoundsException extends RuntimeException {

    /**
     * Serialization identifier for ensuring class compatibility across different versions.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default message format used when no custom message is provided.
     * <p>
     * The format expects two placeholders ({@code %s}) for the bounds that cannot be compared.
     * </p>
     */
    protected static final String DEFAULT_MESSAGE_FORMAT = "Bounds %s and %s cannot be compared";

    /**
     * The first bound involved in the failed comparison.
     * <p>
     * This field may be {@code null} if the exception was constructed without specifying the bounds.
     * </p>
     */
    private final Bound firstBound;

    /**
     * The second bound involved in the failed comparison.
     * <p>
     * This field may be {@code null} if the exception was constructed without specifying the bounds.
     * </p>
     */
    private final Bound secondBound;

    /**
     * Constructs an {@code IncomparableBoundsException} with no detail message and no specific bounds.
     */
    public IncomparableBoundsException() {
        this((Bound) null, null);
    }

    /**
     * Constructs an {@code IncomparableBoundsException} with the specified detail message.
     *
     * @param message the detail message
     */
    public IncomparableBoundsException(String message) {
        this(null, null, message);
    }

    /**
     * Constructs an {@code IncomparableBoundsException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of this exception
     */
    public IncomparableBoundsException(String message, Throwable cause) {
        this(null, null, message, cause);
    }

    /**
     * Constructs an {@code IncomparableBoundsException} with the specified cause.
     *
     * @param cause the cause of this exception
     */
    public IncomparableBoundsException(Throwable cause) {
        this(null, null, cause);
    }

    /**
     * Constructs an {@code IncomparableBoundsException} with the specified bounds.
     *
     * @param firstBound  the first bound involved in the comparison
     * @param secondBound the second bound involved in the comparison
     */
    public IncomparableBoundsException(Bound firstBound, Bound secondBound) {
        this(firstBound, secondBound, createDefaultMessage(firstBound, secondBound));
    }

    /**
     * Constructs an {@code IncomparableBoundsException} with the specified bounds and a custom message.
     *
     * @param firstBound  the first bound involved in the comparison
     * @param secondBound the second bound involved in the comparison
     * @param message     the detail message
     */
    public IncomparableBoundsException(Bound firstBound, Bound secondBound, String message) {
        super(message);
        this.firstBound = firstBound;
        this.secondBound = secondBound;
    }

    /**
     * Constructs an {@code IncomparableBoundsException} with the specified bounds and cause.
     *
     * @param firstBound  the first bound involved in the comparison
     * @param secondBound the second bound involved in the comparison
     * @param cause       the cause of this exception
     */
    public IncomparableBoundsException(Bound firstBound, Bound secondBound, Throwable cause) {
        this(firstBound, secondBound, createDefaultMessage(firstBound, secondBound), cause);
    }

    /**
     * Constructs an {@code IncomparableBoundsException} with the specified bounds, message, and cause.
     *
     * @param firstBound  the first bound involved in the comparison
     * @param secondBound the second bound involved in the comparison
     * @param message     the detail message
     * @param cause       the cause of this exception
     */
    public IncomparableBoundsException(Bound firstBound, Bound secondBound, String message, Throwable cause) {
        super(message, cause);
        this.firstBound = firstBound;
        this.secondBound = secondBound;
    }

    /**
     * Returns the first bound involved in the failed comparison.
     *
     * @return the first bound, or {@code null} if not specified
     */
    public Bound getFirstBound() {
        return firstBound;
    }

    /**
     * Returns the second bound involved in the failed comparison.
     *
     * @return the second bound, or {@code null} if not specified
     */
    public Bound getSecondBound() {
        return secondBound;
    }

    /**
     * Creates a default error message for the exception based on the given bounds.
     *
     * @param firstBound  the first bound
     * @param secondBound the second bound
     * @return a formatted error message indicating that the bounds cannot be compared
     */
    protected static String createDefaultMessage(Bound firstBound, Bound secondBound) {
        return String.format(
                DEFAULT_MESSAGE_FORMAT,
                firstBound == null ? "null" : firstBound,
                secondBound == null ? "null" : secondBound
        );
    }
}
