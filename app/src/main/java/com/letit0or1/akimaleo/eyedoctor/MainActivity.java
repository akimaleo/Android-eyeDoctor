package com.letit0or1.akimaleo.eyedoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.letit0or1.akimaleo.eyedoctor.entity.DataCollection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//            DataCollection.getInstance().getData();
        Intent intent = new Intent(getApplicationContext(),ColorBlindDiagnosticActivity.class);
        startActivity(intent);
    }


}
