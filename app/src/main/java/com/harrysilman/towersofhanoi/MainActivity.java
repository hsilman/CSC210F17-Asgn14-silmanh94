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
        startButton.setOnClickListener(mStartListener);

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private View.OnClickListener mStartListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gameAnimationView.init();
        }
    };


}
