package com.jackson42.play.form.databinders;

import com.jackson42.play.form.databinders.joda.DateTimeBinders;
import org.joda.time.DateTime;
import play.data.format.Formatters;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * JodaDataModule.
 *
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
     * @since 16.05.016
     */
    @Inject
    public JodaDataModule(final Formatters formatters) {
        formatters.register(DateTime.class, new DateTimeBinders.DateTimeSimpleFormatter("yyyy-MM-dd"));
        formatters.register(DateTime.class, new DateTimeBinders.DateTimeAnnotationFormatter());
    }
}
