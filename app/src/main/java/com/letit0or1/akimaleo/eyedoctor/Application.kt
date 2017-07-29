package com.letit0or1.akimaleo.eyedoctor

import com.letit0or1.akimaleo.eyedoctor.colorblind.entity.DataCollection

/**
 * Created by akimaleo on 15.01.17.
 */

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        //set application context to dataCollection class for allowing resources access
        DataCollection.instance.context = applicationContext
    }
}
