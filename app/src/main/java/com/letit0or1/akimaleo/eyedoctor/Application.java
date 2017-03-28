package com.letit0or1.akimaleo.eyedoctor;

import com.letit0or1.akimaleo.eyedoctor.colorblind.entity.DataCollection;

/**
 * Created by akimaleo on 15.01.17.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //set application context to dataCollection class for allowing resources access
        DataCollection.getInstance().setContext(getApplicationContext());
    }
}
