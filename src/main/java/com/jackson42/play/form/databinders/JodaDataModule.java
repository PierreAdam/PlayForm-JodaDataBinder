package com.jackson42.play.form.databinders;

import com.jackson42.play.form.databinders.joda.formatter.DateTimeBasicAnnotatedFormatter;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeExtendedAnnotatedFormatter;
import com.jackson42.play.form.databinders.joda.formatter.DateTimeSimpleFormatter;
import org.joda.time.DateTime;
import play.data.format.Formatters;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * JodaDataModule.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.05.16
 * @since 16.05.16
 */
@Singleton
public class JodaDataModule {

    /**
     * Build an instance.
     *
     * @param formatters The Play Formatters instance
     * @version 17.02.07
     * @since 16.05.01
     */
    @Inject
    public JodaDataModule(final Formatters formatters) {
        formatters.register(DateTime.class, new DateTimeSimpleFormatter("yyyy-MM-dd"));
        formatters.register(DateTime.class, new DateTimeBasicAnnotatedFormatter());
        formatters.register(DateTime.class, new DateTimeExtendedAnnotatedFormatter());
    }
}
