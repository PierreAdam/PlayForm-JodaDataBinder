package com.jackson42.play.form.databinders.joda;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.format.Formats;
import play.data.format.Formatters;

import java.text.ParseException;
import java.util.Locale;

/**
 * DateTimeBinders.
 *
 * @author Pierre Adam
 * @version 16.01
 * @since 16.01
 */
public class DateTimeBinders {

    /**
     * DateTimeSimpleFormatter.
     *
     * @author Pierre Adam
     * @version 16.01
     * @since 16.01
     */
    public static class DateTimeSimpleFormatter extends Formatters.SimpleFormatter<DateTime> {

        /**
         * The pattern to use when parsing or printing the joda datetime.
         *
         * @since 16.01
         */
        private final String pattern;

        /**
         * Simple constructor.
         *
         * @param pattern The pattern to use
         */
        public DateTimeSimpleFormatter(String pattern) {
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
         */
        @Override
        public DateTime parse(String text, Locale locale) throws ParseException {
            if (text == null || text.trim().isEmpty() || text.compareToIgnoreCase("null") == 0) {
                return null;
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(this.pattern);
            dateTimeFormatter.withLocale(locale);
            return dateTimeFormatter.parseDateTime(text);
        }

        /**
         * Put the Joda {@code DateTime} to a {@code String} using the internal pattern.
         *
         * @param value  The DateTime
         * @param locale The current locale
         * @return An empty string if the DateTime is null. The Correctly formatted string otherwise
         */
        @Override
        public String print(DateTime value, Locale locale) {
            if (value == null) {
                return "";
            }
            return value.toString(this.pattern, locale);
        }
    }

    /**
     * DateTimeAnnotationFormatter.
     *
     * @author Pierre Adam
     * @version 16.01
     * @since 16.01
     */
    public static class DateTimeAnnotationFormatter extends Formatters.AnnotationFormatter<Formats.DateTime, DateTime> {

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
         */
        @Override
        public DateTime parse(Formats.DateTime annotation, String text, Locale locale) throws ParseException {
            if (text == null || text.trim().isEmpty() || text.compareToIgnoreCase("null") == 0) {
                return null;
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(annotation.pattern());
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
         */
        @Override
        public String print(Formats.DateTime annotation, DateTime value, Locale locale) {
            if (value == null) {
                return "";
            }
            return value.toString(annotation.pattern(), locale);
        }
    }
}
