package com.golfscorecard.cws.golfscorecard;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {


    // REQUIREMENTS:
    // 1) There should be a list of 18 Holes
    // 2) There should be an easy way to add/subtract strokes to each hole
    // 3) It should remember the scores we set (even if we close the app)
    // 4) It should contain a 'Clear Strokes' button which will reset all stroke counts to 0

    String[] mHoles = new String[18];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // populate mHoles String data
        for (int i = 0; i < mHoles.length; i++) {
            mHoles[i] = "Hole " + (i + 1) + ":";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_main, mHoles);
        setListAdapter(adapter);

    }
}
