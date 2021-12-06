package com.anilpraharaj.newsbreeze;

import android.app.Application;

/**
 * @author anilpraharaj on 05/12/21
 */
public class NewsBreezeApplication extends Application {

    static NewsBreezeApplication application;

    /**
     * Singleton Instance for App Application Object
     *
     * @return NewsBreezeApplication Singleton Object
     */
    public static NewsBreezeApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this; // Set Application object to static application variable
    }
}
