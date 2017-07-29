package com.letit0or1.akimaleo.eyedoctor

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.letit0or1.akimaleo.eyedoctor.amslergrid.AmslerGridActivity
import com.letit0or1.akimaleo.eyedoctor.colorblind.ColorBlindDiagnosticActivity

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.activity_color_blind_diagnostic).setOnClickListener {
            //start diagnostic activity
            val intent = Intent(applicationContext, ColorBlindDiagnosticActivity::class.java)
            startActivity(intent)
        }
        findViewById(R.id.activity_amsler_grid).setOnClickListener {
            //start diagnostic activity
            val intent = Intent(applicationContext, AmslerGridActivity::class.java)
            startActivity(intent)
        }
    }
}
