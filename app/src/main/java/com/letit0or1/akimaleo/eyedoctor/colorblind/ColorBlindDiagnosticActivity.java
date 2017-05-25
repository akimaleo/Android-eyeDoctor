package com.letit0or1.akimaleo.eyedoctor.colorblind;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.letit0or1.akimaleo.eyedoctor.R;
import com.letit0or1.akimaleo.eyedoctor.colorblind.entity.DataCollection;
import com.letit0or1.akimaleo.eyedoctor.colorblind.entity.DataItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ch.halcyon.squareprogressbar.SquareProgressBar;

public class ColorBlindDiagnosticActivity extends AppCompatActivity {
    //Data collection
    private ArrayList<DataItem> mDataSet;
    //image view and progressbar
    private SquareProgressBar mProgressBar;
    private int mCurrentSlide = 0;
    private Button first, second, third, fourth;
    private int fullBlindCount = 0, red_greenBlind = 0, normal = 0, fake = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //displaying layout
        setContentView(R.layout.activity_color_blind_diagnostic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //get data
        mDataSet = DataCollection.getInstance().getData();
        Collections.shuffle(mDataSet);
        //init progressbar
        mProgressBar = (SquareProgressBar) findViewById(R.id.progressbar);

        first = (Button) findViewById(R.id.first);
        second = (Button) findViewById(R.id.second);
        third = (Button) findViewById(R.id.third);
        fourth = (Button) findViewById(R.id.fourth);

        //start diagnostic
        startSlideshow();
    }


    void startSlideshow() {
        nextImage();
    }

    void setButtons(DataItem item) {
        int right = item.getAnswer()[0];
        int green_red = item.getAnswer()[1];
        final int full = item.getAnswer()[2];

        ArrayList<Button> btns = new ArrayList<>();
        btns.add(first);
        btns.add(second);
        btns.add(third);
        btns.add(fourth);

        Collections.shuffle(btns);
        btns.get(0).setText(right == 0 ? getString(R.string.button_nothing) : right + "");
        btns.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                normal++;
                nextImage();
            }
        });
        btns.get(1).setText(green_red + "");
        btns.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                red_greenBlind++;
                nextImage();
            }
        });
        if (right == 0)
            btns.get(2).setText(new Random().nextInt(85) + "");
        else
            btns.get(2).setText(full == 0 ? "Nothing" : full + "");
        btns.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullBlindCount++;
                nextImage();
            }
        });
        btns.get(3).setText(new Random().nextInt(85) + "");
        btns.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fake++;
                nextImage();
            }
        });

    }

    void nextImage() {
        if (mCurrentSlide == mDataSet.size()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("The result").setMessage(result()).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
            return;
        }
        DataItem i = mDataSet.get(mCurrentSlide++);
        mProgressBar.setImage(i.getImageResource());
        mProgressBar.setProgress((((float) (mCurrentSlide)) / mDataSet.size() * 100) - 0.1f);
        setButtons(i);
    }

    private String result() {
        int answersSum = fullBlindCount + red_greenBlind + normal + fake;
        int percentage = (int) ((((float) normal) / ((float) answersSum))* 100) ;
        return "В ході діагностики було отримо такі результати:" +
                "\nВідповідей при нормальному зорі " + percentage + " %" +
                (percentage < 98 ? "\nУ вас є підозри на паталогію кольосприйняття" : "");

//        return "В ході діагностики було отримо такі результати:" +
//                "\n\tПри нормально зорі відповідей: " + normal +
//                "\n\tПри сліпоті в зеленому або червоно спектрі: " + red_greenBlind +
//                "\n\tПри повній кольоровій сліпоті: " + fullBlindCount +
//                "\n\tМоживих симуляцій: " + fake;
    }
}
