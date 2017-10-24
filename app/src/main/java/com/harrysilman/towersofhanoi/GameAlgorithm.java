package com.harrysilman.towersofhanoi;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Silman on 10/24/2017.
 */

public class GameAlgorithm extends Activity{

    TextView moveView = (TextView)findViewById(R.id.movesTextView);

    public void solve(int n, String start, String auxiliary, String end) {
        if (n == 1) {
            moveView.setText(start + " -> " + end);
        } else {
            solve(n - 1, start, end, auxiliary);
            moveView.setText(start + " -> " + end);
            solve(n - 1, auxiliary, start, end);
        }
    }
}
