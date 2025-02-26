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

public class SimpleNumber extends Number {

    public static final SimpleNumber NEGATIVE_ONE = new SimpleNumber("-1");
    public static final SimpleNumber ZERO = new SimpleNumber("0");
    public static final SimpleNumber ONE = new SimpleNumber("1");

    public static final SimpleNumber ILLEGAL_NUMBER = new SimpleNumber("0") {
        @Override
        public String toString() {
            return "Not a number value";
        }
    };

    protected final String number;

    public SimpleNumber(String number) {
        this.number = number;
    }

    @Override
    public int intValue() {
        return Integer.parseInt(number);
    }

    @Override
    public long longValue() {
        return Long.parseLong(number);
    }

    @Override
    public float floatValue() {
        return Float.parseFloat(number);
    }

    @Override
    public double doubleValue() {
        return Double.parseDouble(number);
    }

    @Override
    public String toString() {
        return number;
    }
}
