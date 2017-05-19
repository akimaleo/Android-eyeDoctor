package com.letit0or1.akimaleo.eyedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.letit0or1.akimaleo.eyedoctor.amslergrid.AmslerGridActivity;
import com.letit0or1.akimaleo.eyedoctor.colorblind.ColorBlindDiagnosticActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_color_blind_diagnostic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start diagnostic activity
                Intent intent = new Intent(getApplicationContext(), ColorBlindDiagnosticActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.activity_amsler_grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start diagnostic activity
                Intent intent = new Intent(getApplicationContext(), AmslerGridActivity.class);
                startActivity(intent);
            }
        });
    }


}
