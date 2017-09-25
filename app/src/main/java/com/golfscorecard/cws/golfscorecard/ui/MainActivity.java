package com.golfscorecard.cws.golfscorecard.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.golfscorecard.cws.golfscorecard.R;
import com.golfscorecard.cws.golfscorecard.adapters.HoleAdapter;
import com.golfscorecard.cws.golfscorecard.hole.Hole;

import java.lang.reflect.Array;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ListActivity {


    // REQUIREMENTS:
    // 1) There should be a list of 18 Holes
    // 2) There should be an easy way to add/subtract strokes to each hole
    // 3) It should remember the scores we set (even if we close the app)
    // 4) It should contain a 'Clear Strokes' button which will reset all stroke counts to 0

    private static final String PREFS_FILE = "cws.golfscorecard.preferences";

    private SharedPreferences mSharedPreferences;
    private static final String KEY_STROKECOUNT = "key_strokecount";
    private SharedPreferences.Editor mEditor;
    private Hole[] mHoles = new Hole[18];
    private HoleAdapter mHoleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        // initialize holes
        int strokes = 0;
        for (int i = 0; i < mHoles.length; i++) {
            strokes = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0);
            mHoles[i] = new Hole("Hole " + (i + 1) + ":", strokes);
        }

        mHoleAdapter = new HoleAdapter(this, mHoles);
        setListAdapter(mHoleAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int i = 0; i < mHoles.length; i++) {
            // make 18 different keys for SharedPreferences to store 18 different values
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getStrokeCount());
        }
        mEditor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_strokes) {
            mEditor.clear();
            mEditor.apply();

            for (Hole hole : mHoles) {
                hole.setStrokeCount(0);
            }
            mHoleAdapter.notifyDataSetChanged();;

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}