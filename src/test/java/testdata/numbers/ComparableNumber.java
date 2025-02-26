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

package testdata.numbers;

import java.math.BigDecimal;

public class ComparableNumber extends SimpleNumber implements Comparable<ComparableNumber> {

    public static final ComparableNumber ZERO = new ComparableNumber("0");
    public static final ComparableNumber ONE = new ComparableNumber("1");
    public static final ComparableNumber NEGATIVE_ONE = new ComparableNumber("-1");

    public static final ComparableNumber ILLEGAL_NUMBER = new ComparableNumber("0") {
        @Override
        public String toString() {
            return "Not a number value";
        }
    };

    public ComparableNumber(String number) {
        super(number);
    }

    public static ComparableNumber getIncomparableInstance(String number) {
        return new ComparableNumber(number) {
            @Override
            public int compareTo(ComparableNumber other) {
                throw new UnsupportedOperationException("Comparison not supported");
            }
        };
    }

    @Override
    public int compareTo(ComparableNumber other) {
        BigDecimal thisBigDecimal = new BigDecimal(number);
        BigDecimal otherBigDecimal = new BigDecimal(other.number);
        return thisBigDecimal.compareTo(otherBigDecimal);
    }
}
