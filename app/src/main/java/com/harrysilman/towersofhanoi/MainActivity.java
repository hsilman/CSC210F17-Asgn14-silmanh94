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

    private GameAnimationView gameAnimationView;

    // create an arraylist of strings for the move list
    ArrayList<String> movesList = new ArrayList<String>();

    // create an arraylist for the graphical movement
    public static ArrayList<String> diskMoveList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register the start button and a click listener
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


    // describe what the button does
    private View.OnClickListener mStartListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int numberOfDisks;
            diskMoveList.clear();
            movesList.clear();

            //access the user input of disk number programmatically
            EditText numberEditText = (EditText)findViewById(R.id.numberEditText);
            numberOfDisks = Integer.valueOf(numberEditText.getText().toString());

            // run the number of disks through the Hanoi solver algorithm
            try {
                solve(numberOfDisks,"A","B","C");
                Log.d(TAG, diskMoveList.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

        // solve the Towers of Hanoi using recursion
        public void solve(int n, String start, String auxiliary, String end) throws InterruptedException {
            if (n == 1) {
                // add each move to the arraylist & diskmovelist
                movesList.add(start + " -> " + end);
                diskMoveList.add(start + "," + end);
            } else {
                solve(n - 1, start, end, auxiliary);
                movesList.add(start + " -> " + end);
                diskMoveList.add(start + "," + end);
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

        public static ArrayList<String> returnList(){
            return(diskMoveList);
        }
    }





