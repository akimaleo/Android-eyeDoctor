package com.letit0or1.akimaleo.eyedoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.letit0or1.akimaleo.eyedoctor.entity.DataCollection;
import com.letit0or1.akimaleo.eyedoctor.entity.DataItem;

import java.util.ArrayList;
import java.util.Collections;

import ch.halcyon.squareprogressbar.SquareProgressBar;

public class ColorBlindDiagnosticActivity extends AppCompatActivity {
    //Data collection
    private ArrayList<DataItem> mDataSet;
    //image view and progressbar
    private SquareProgressBar mProgressBar;
    private TextView mDescriptionTest;
    private int milliseconds = 5000;
    //init timer thread
//    private Thread timer = new Thread(new Runnable() {
//        int currentImage = 0;
//
//        @Override
//        public void run() {
//            for (int i = 0; i < mDataSet.size(); i++) {
//                Log.i("item num", String.valueOf(i));
//                final DataItem item = mDataSet.get(currentImage);
//                //access UI elements only form UI thread
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mProgressBar.setImage(item.getImageResource());
//                        mDescriptionTest.setText(item.getDescription());
//
//                        mProgressBar.invalidate();
//                        mDescriptionTest.invalidate();
//                    }
//                });
//
//                //wait X seconds
//                try {
//                    synchronized (this) {
//                        wait(milliseconds);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }
//    }
//    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //displaying layout
        setContentView(R.layout.activity_color_blind_diagnostic);

        //get data
        mDataSet = DataCollection.getInstance().getData();
        //init progressbar
        mProgressBar = (SquareProgressBar) findViewById(R.id.progressbar);
        mDescriptionTest = (TextView) findViewById(R.id.textView);
        //start diagnostic
        startSlideshow();
    }

    void startSlideshow() {
        //shuffle collection for
        Collections.shuffle(mDataSet);
        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
/*animation
* Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                fadeIn.setDuration(1000);

                Animation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
                fadeOut.setStartOffset(1000);
                fadeOut.setDuration(1000);

                AnimationSet animation = new AnimationSet(false); //change to false
                animation.addAnimation(fadeIn);
                animation.addAnimation(fadeOut);
                mDescriptionTest.setAnimation(animation);*/
}
