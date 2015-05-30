
package com.shilpasweth.contacts3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    ListView list;


    String[] names = {
            "Black",
            "Blue",
            "Green"/*,
            "Indigo",
            "Orange"/*,
            "Red",
            "Violet",
            "Yellow"*/


    } ;

    String[] namesrev={/*names[7],names[6],names[5],names[4],names[3],*/names[2],names[1],names[0]};

    Integer[] imageId = {
            R.drawable.black,
            R.drawable.blue,
            R.drawable.green/*,
            R.drawable.indigo,
            R.drawable.orange/*,
            R.drawable.red,
            R.drawable.violet,
            R.drawable.yellow*/



    };

    Integer[] imageIdrev={/*imageId[7],imageId[6],imageId[5],imageId[4],imageId[3],*/imageId[2],imageId[1],imageId[0]};

    boolean on;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displaywhich();


    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        on = ((ToggleButton) view).isChecked();
        displaywhich();


    }

    public void displaywhich(){
        if (on) {
            CustomList adapter = new
                    CustomList(MainActivity.this, namesrev, imageIdrev);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter); // Disable vibrate // Enable vibrate
        } else {
            CustomList adapter = new
                    CustomList(MainActivity.this, names, imageId);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter); // Disable vibrate
        }
    }

}