/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 - 2018 PayinTech
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.jackson42.play.form.databinders.joda.formatter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.StringJoiner;

/**
 * DateTimeParser.
 *
 * @author Pierre Adam
 * @since 18.08.08
 */
public interface DateTimeParser {

    /**
     * Parse the datetime.
     *
     * @param patterns the patterns
     * @param text     the text
     * @param locale   the locale
     * @return the date time
     * @throws ParseException the parse exception
     */
    default DateTime parse(final String[] patterns, final String text, final Locale locale) throws ParseException {
        if (text == null || text.trim().isEmpty() || text.compareToIgnoreCase("null") == 0) {
            return null;
        }

        for (final String pattern : patterns) {
            try {
                final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
                return dateTimeFormatter.withLocale(locale).withZoneUTC().parseDateTime(text);
            } catch (final IllegalArgumentException ignore) {
            }
        }

        final StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (final String pattern : patterns) {
            joiner.add(pattern);
        }
        throw new ParseException(String.format("The date doesn't match one of the following patterns %s or is not a valid date.", joiner.toString()), 0);
    }

    /**
     * Parse the datetime.
     *
     * @param pattern the pattern
     * @param text    the text
     * @param locale  the locale
     * @return the date time
     * @throws ParseException the parse exception
     */
    default DateTime parse(final String pattern, final String text, final Locale locale) throws ParseException {
        if (text == null || text.trim().isEmpty() || text.compareToIgnoreCase("null") == 0) {
            return null;
        }
        try {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
            return dateTimeFormatter.withLocale(locale).withZoneUTC().parseDateTime(text);
        } catch (final IllegalArgumentException ignore) {
        }

        throw new ParseException(String.format("The date doesn't match the following pattern %s or is not a valid date.", pattern), 0);
    }

    /**
     * Print the datetime representation.
     *
     * @param printPattern the pattern
     * @param value        the value
     * @param locale       the locale
     * @return the datetime representation
     */
    default String print(final String printPattern, final DateTime value, final Locale locale) {
        if (value == null) {
            return "";
        }
        return value.toString(printPattern, locale);
    }

    /**
     * Print the datetime representation.
     *
     * @param printPattern the pattern
     * @param value        the value
     * @param locale       the locale
     * @return the datetime representation
     */
    default String print(final String printPattern, final String[] parsePatterns, final DateTime value, final Locale locale) {
        String pattern = printPattern;
        if ((pattern == null || pattern.equals("")) && parsePatterns.length > 0) {
            pattern = parsePatterns[0];
        }
        return this.print(pattern, value, locale);
    }
}
