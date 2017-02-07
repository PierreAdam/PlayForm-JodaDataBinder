package com.jackson42.play.form.databinders.joda.formatter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.format.Formatters;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeSimpleFormatter.
 *
 * @author Pierre Adam
 * @version 17.02.07
 * @since 16.01.31
 */
public class DateTimeSimpleFormatter extends Formatters.SimpleFormatter<DateTime> {

    /**
     * The pattern to use when parsing or printing the joda datetime.
     *
     * @since 16.01.31
     */
    private final String pattern;

    /**
     * Simple constructor.
     *
     * @param pattern The pattern to use
     * @since 16.01.31
     */
    public DateTimeSimpleFormatter(final String pattern) {
        this.pattern = pattern;
    }

    /**
     * Parse the text from a form to a Joda {@code DateTime} using the internal pattern.
     *
     * @param text   The literal date
     * @param locale The current locale
     * @return Null if there is no text. The DateTime otherwise
     * @throws ParseException Parse error
     * @see DateTime
     * @since 16.01.31
     */
    @Override
    public DateTime parse(final String text, final Locale locale) throws ParseException {
        if (text == null || text.trim().isEmpty() || text.compareToIgnoreCase("null") == 0) {
            return null;
        }
        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(this.pattern);
        dateTimeFormatter.withLocale(locale);
        return dateTimeFormatter.parseDateTime(text);
    }

    /**
     * Put the Joda {@code DateTime} to a {@code String} using the internal pattern.
     *
     * @param value  The DateTime
     * @param locale The current locale
     * @return An empty string if the DateTime is null. The Correctly formatted string otherwise
     * @since 16.01.31
     */
    @Override
    public String print(final DateTime value, final Locale locale) {
        if (value == null) {
            return "";
        }
        return value.toString(this.pattern, locale);
    }
}
