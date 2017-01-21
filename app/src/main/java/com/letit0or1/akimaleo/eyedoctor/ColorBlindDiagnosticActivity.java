package com.letit0or1.akimaleo.eyedoctor;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letit0or1.akimaleo.eyedoctor.entity.DataCollection;
import com.letit0or1.akimaleo.eyedoctor.entity.DataItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ch.halcyon.squareprogressbar.SquareProgressBar;

public class ColorBlindDiagnosticActivity extends AppCompatActivity {
    //Data collection
    private ArrayList<DataItem> mDataSet;
    //image view and progressbar
    private SquareProgressBar mProgressBar;
    private TextView mDescriptionTest;
    private int milliseconds = 5000;
    private int mCurrentSlide = 0;
    private LinearLayout buttonLine0, buttonLine1;
    private Button first, second, third, fourth;
    private int fullBlindCount = 0, red_greenBlind = 0, normal = 0, fake = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //displaying layout
        setContentView(R.layout.activity_color_blind_diagnostic);

        buttonLine0 = (LinearLayout) findViewById(R.id.line0);
        buttonLine1 = (LinearLayout) findViewById(R.id.line1);
        //get data
        mDataSet = DataCollection.getInstance().getData();
        //init progressbar
        mProgressBar = (SquareProgressBar) findViewById(R.id.progressbar);
//        mDescriptionTest = (TextView) findViewById(R.id.textView);

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
        btns.get(0).setText(right == 0 ? "Nothing" : right + "");
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
        if (mCurrentSlide == mDataSet.size() - 1) {
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
        mProgressBar.setProgress(((float) (mCurrentSlide - 1) / mDataSet.size()) * 100);
        setButtons(i);
    }

    private String result() {
        return "В ході діагностики було отримо такі результати:" +
                "\n\tПри нормально зорі відповідей: " + normal +
                "\n\tПри сліпоті в зеленому або червоно спектрі: " + red_greenBlind +
                "\n\tПри повній кольоровій сліпоті: " + fullBlindCount +
                "\n\tМоживих симуляцій: " + fake;
    }
}
