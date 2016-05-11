package com.overman.metronome;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView mBPM;
    private TextView mDirection;
    private boolean mRunning;
    private DecimalFormat mDF;

    private GestureDetectorCompat mDetector;

    private float mbpm = 99f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        mDirection = (TextView) findViewById(R.id.direction_label);


        mBPM = (TextView) findViewById(R.id.bpm_label);
        mDF = new DecimalFormat("#");
        mBPM.setText(mDF.format(mbpm));
/*        mBPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not going, start the met
                if(!mRunning) {
                    mDirection.setText("running");
                    mRunning = !mRunning;
                } else {  // if it's going, stop the met
                    mDirection.setText("stopped");
                    mRunning = !mRunning;
                }
            }



        });

        */

 /*       mBPM.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int index = event.getActionIndex();
                int action = event.getActionMasked();

                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        mDirection.setText("down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mDirection.setText("moving");
                        break;
            //        case MotionEvent.ACTION_SCROLL:
            //            mDirection.setText("scrolling");
            //            break;
            //        case MotionEvent.ACTION_
                    case MotionEvent.ACTION_UP:
                        mDirection.setText("up");
                        break;
                }

                return true;
            }
        });
*/


    }

    public void onStartStopClicked(View view) {
        // if it's not going, start the met
        if(!mRunning) {
            mDirection.setText("running");
            mRunning = !mRunning;
        } else {  // if it's going, stop the met
            mDirection.setText("stopped");
            mRunning = !mRunning;
        }

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.whole_numbers:
                if (checked)
                    mDF = new DecimalFormat("#");
                break;
            case R.id.decimals:
                if (checked)
                    mDF = new DecimalFormat("#.#");
                break;
        }

    }
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    public void toastMe(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TAG, "onDown: " + e.toString());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mbpm -= distanceX / 10;
            mBPM.setText(mDF.format(mbpm));
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mDirection.setText("flinging: " + velocityX + ", " + velocityY);

            return true;
        }
    }
}
