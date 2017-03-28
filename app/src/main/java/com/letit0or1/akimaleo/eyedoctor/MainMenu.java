package com.letit0or1.akimaleo.eyedoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.letit0or1.akimaleo.eyedoctor.colorblind.ColorBlindDiagnosticActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_color_blind_diagnostic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start diagnostic activity
                Intent intent = new Intent(getApplicationContext(),ColorBlindDiagnosticActivity.class);
                startActivity(intent);
            }
        });
    }


}
