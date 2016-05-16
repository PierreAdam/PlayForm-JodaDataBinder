package com.jackson42.play.form.databinders;

import com.google.inject.AbstractModule;

/**
 * JodaDataBinder.
 *
 * @author Pierre Adam
 * @author Thibault Meyer
 * @version 16.05.16
 * @since 16.01.31
 */
public class JodaDataBinder extends AbstractModule {

    @Override
    protected void configure() {
        bind(JodaDataModule.class).asEagerSingleton();
    }
}
