package com.jackson42.play.form.databinders.joda.formatter;

import com.jackson42.play.form.databinders.joda.annotation.JodaDateTimeFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.format.Formatters;

import java.text.ParseException;
import java.util.Locale;
import java.util.StringJoiner;

/**
 * DateTimeBasicAnnotatedFormatter.
 *
 * @author Pierre Adam
 * @version 17.02.07
 * @since 16.01.31
 */
public class DateTimeExtendedAnnotatedFormatter extends Formatters.AnnotationFormatter<JodaDateTimeFormat, DateTime> {

    /**
     * Parse the text from a form to a Joda {@code DateTime} using the play
     * {@code Formats.DateTime} annotation in order to obtain the correct format.
     *
     * @param annotation The annotation
     * @param text       The literal date
     * @param locale     The current locale
     * @return Null if there is no text. The DateTime otherwise
     * @throws ParseException Parse error
     * @see DateTime
     * @since 16.01.31
     */
    @Override
    public DateTime parse(final JodaDateTimeFormat annotation, final String text, final Locale locale) throws ParseException {
        if (text == null || text.trim().isEmpty() || text.compareToIgnoreCase("null") == 0) {
            return null;
        }
        for (final String pattern : annotation.patterns()) {
            try {
                final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
                dateTimeFormatter.withLocale(locale);
                return dateTimeFormatter.parseDateTime(text);
            } catch (final IllegalArgumentException ignore) {
            }
        }

        final StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (final String pattern : annotation.patterns()) {
            joiner.add(pattern);
        }
        throw new ParseException(String.format("The date doesn't match one of the following pattern %s", joiner.toString()), 0);
    }

    /**
     * Put the Joda {@code DateTime} to a {@code String} using the the play
     * {@code Formats.DateTime} annotation in order to obtain the correct format.
     *
     * @param annotation The annotation
     * @param value      The DateTime
     * @param locale     The current locale
     * @return An empty string if the DateTime is null. The Correctly formatted string otherwise
     * @since 16.01.31
     */
    @Override
    public String print(final JodaDateTimeFormat annotation, final DateTime value, final Locale locale) {
        if (value == null) {
            return "";
        }
        String pattern = annotation.printPattern();
        if (pattern.equals("") && annotation.patterns().length > 0) {
            pattern = annotation.patterns()[0];
        }
        return value.toString(pattern, locale);
    }
}
