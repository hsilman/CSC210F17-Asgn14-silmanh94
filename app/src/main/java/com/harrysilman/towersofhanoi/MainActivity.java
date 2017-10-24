package com.harrysilman.towersofhanoi;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private GameAnimationView gameAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameAnimationView = (GameAnimationView) findViewById(R.id.gameAnimationView);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
