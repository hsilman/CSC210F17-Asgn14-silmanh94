package com.harrysilman.towersofhanoi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {

    private GameAnimationView gameAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameAnimationView = (GameAnimationView) findViewById(R.id.gameAnimationView);
        Button startButton = (Button)findViewById(R.id.startButton);
        //startButton.setOnClickListener(mStartListener); //disabled until I figure this out

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /*
    * Have to figure out starting the gamethread with a click event
    private View.OnClickListener mStartListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            gameAnimationView.init();
        }
    };
    */


}
