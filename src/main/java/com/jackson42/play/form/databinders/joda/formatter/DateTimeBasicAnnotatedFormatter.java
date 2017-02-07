package com.jackson42.play.form.databinders.joda.formatter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.format.Formats;
import play.data.format.Formatters;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeBasicAnnotatedFormatter.
 *
 * @author Pierre Adam
 * @version 17.02.07
 * @since 16.01.31
 */
public class DateTimeBasicAnnotatedFormatter extends Formatters.AnnotationFormatter<Formats.DateTime, DateTime> {

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
    public DateTime parse(final Formats.DateTime annotation, final String text, final Locale locale) throws ParseException {
        if (text == null || text.trim().isEmpty() || text.compareToIgnoreCase("null") == 0) {
            return null;
        }
        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(annotation.pattern());
        dateTimeFormatter.withLocale(locale);
        return dateTimeFormatter.parseDateTime(text);
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
    public String print(final Formats.DateTime annotation, final DateTime value, final Locale locale) {
        if (value == null) {
            return "";
        }
        return value.toString(annotation.pattern(), locale);
    }
}
