package com.harrysilman.towersofhanoi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // create an arraylist of strings for the move list
    ArrayList<String> movesList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //access the user input of disk number programmatically
        final EditText numberEditText = findViewById(R.id.numberEditText);

        //register GameAnimationView
        final GameAnimationView gameAnimationView = findViewById(R.id.gameAnimationView);

        // register the start button and a click listener
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                movesList.clear();

                // run the number of disks through the Hanoi solver algorithm
                try {
                    String size = numberEditText.getText().toString();
                    int intSize = Integer.parseInt(size);
                    gameAnimationView.setSize(intSize);
                    solve(intSize,"A","B","C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


     // solve the Towers of Hanoi using recursion for listview text
        public void solve(int n, String start, String auxiliary, String end) throws InterruptedException {
            if (n == 1) {
                // add each move to the arraylist
                movesList.add(start + " -> " + end);
            } else {
                solve(n - 1, start, end, auxiliary);
                // add each move the the arraylist
                movesList.add(start + " -> " + end);
                solve(n - 1, auxiliary, start, end);
            }

            // access the moveListView programmatically
            ListView lv = findViewById(R.id.movesListView);

            // use an ArrayAdapter to display the move list
            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                            movesList);
            lv.setAdapter(itemsAdapter);
        }



    }





