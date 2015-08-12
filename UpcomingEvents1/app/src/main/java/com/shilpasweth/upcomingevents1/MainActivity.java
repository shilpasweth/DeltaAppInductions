package com.shilpasweth.upcomingevents1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String[] events = {"cat", "dog", "rat","horse"};
    String[] location = {"barn", "sac", "lhc","eee"};
    int[][] time = {{12, 18, 0, 12, 20, 15},
    {12, 15, 30, 12, 16, 45},
    {12, 20, 15, 12, 21, 30},
            {12,17,15,13,0,30}};
    int n = 4,m=1,t,p=2,ch=0;
    //boolean check=false;

    String[] present;
    String[] prlocation;
    int[][] prtime;

    String[] rlocation= {location[0]};
    String[] register={events[0]};
    int[][] rtime={time[0]};

    String[] follow={events[0],events[2]};
    String[] flocation={location[0],location[2]};
    int[][] ftime={time[0],time[2]};




    int timelimit=1;




    //private Spinner spinner;
    //private static final String[]paths = {"All", "Registered", "Following"};


    protected void sort(String[] tevents, String[] tlocation, int[][] ttime, int o){
       Calendar timenow=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
       String temp;
       int[] tem;
       prtime=new int[o][6];
       String[] store=new String[o];
       prlocation=new String[o];

       for(int i=0;i<o;i++){
           for(int j=i;j<o-1-i;j++){
               Calendar time1=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
               Calendar time2=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
               time1.set(timenow.get(Calendar.YEAR), 7, ttime[j][0], ttime[j][1], ttime[j][2]);
               time2.set(timenow.get(Calendar.YEAR), 7, ttime[j + 1][0], ttime[j + 1][1], ttime[j + 1][2]);


               if(time1.after(time2)){
                   temp=tevents[j];
                   tevents[j]=tevents[j+1];
                   tevents[j+1]=temp;
                   temp=tlocation[j];
                   tlocation[j]=tlocation[j+1];
                   tlocation[j+1]=temp;
                   tem=ttime[j];
                   ttime[j]=ttime[j+1];
                   ttime[j+1]=tem;

               }

           }

       }
       Calendar time3=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
       time3.set(Calendar.YEAR,timenow.get(Calendar.YEAR));
       time3.set(Calendar.MONTH,timenow.get(Calendar.MONTH));
       time3.set(Calendar.DATE,timenow.get(Calendar.DATE));
       time3.set(Calendar.HOUR_OF_DAY,timenow.get(Calendar.HOUR_OF_DAY)+timelimit);
       time3.set(Calendar.MINUTE,timenow.get(Calendar.MINUTE));


       //time3.set(Calendar.HOUR_OF_DAY,((timenow.get(Calendar.HOUR_OF_DAY))+timelimit));
       if(time3.get(Calendar.HOUR_OF_DAY)>23){
           time3.set(Calendar.HOUR_OF_DAY,((timenow.get(Calendar.HOUR_OF_DAY))-24));
           time3.set(Calendar.DATE,(timenow.get(Calendar.DATE)+1));
       }

       t=0;


       for(int k=0;k<o;k++) {
           Calendar time4=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
           time4.set(timenow.get(Calendar.YEAR), 7, ttime[k][0], ttime[k][1], ttime[k][2]);
           Calendar time5=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
           time5.set(timenow.get(Calendar.YEAR), 7, ttime[k][3], ttime[k][4], ttime[k][5]);
           if((time4.before(time3))&&timenow.before(time5)){//||timenow.after(time4)&&timenow.before(time5)){
               //Toast.makeText(getApplicationContext(), "Entered if" + time3.get(Calendar.HOUR_OF_DAY), Toast.LENGTH_LONG).show();
               store[t]=tevents[k];
               prlocation[t]=tlocation[k];
               prtime[t]=ttime[k];
               /*if((timenow.get(Calendar.MINUTE)<=ttime[k][2])) {
                   prtime[t][1]=(-timenow.get(Calendar.HOUR_OF_DAY) + ttime[k][1]);
                   prtime[t][2]=(ttime[k][2]-timenow.get(Calendar.MINUTE));
               }
               else{
                   prtime[t][1]=(-timenow.get(Calendar.HOUR_OF_DAY) + ttime[k][1]-1);
                   prtime[t][2]=(60-timenow.get(Calendar.MINUTE) + ttime[k][2]);
               }*/
               t++;
           }
           /*if (timenow.get(Calendar.DATE) ==time[k][0] && ((time[k][1]-(timenow.get(Calendar.HOUR_OF_DAY)))<2||(((time[k][1]-(timenow.get(Calendar.HOUR_OF_DAY)))==2)&&time[k][2]<=(timenow.get(Calendar.MINUTE))))){
               t++;
               present[t]=events[k];
               prlocation[t]=location[k];
               if((timenow.get(Calendar.MINUTE)<=time[k][2])) {
                   prtime[k][0]=(timenow.get(Calendar.HOUR_OF_DAY) - time[k][1]);
                   prtime[k][1]=(time[k][2]-timenow.get(Calendar.MINUTE));
               }
               else{
                   prtime[k][0]=(timenow.get(Calendar.HOUR_OF_DAY) - time[k][1]-1);
                   prtime[k][1]=(60-timenow.get(Calendar.MINUTE) + time[k][2]);
               }
               prtime[t]=time[k];

           }*/
       }
        present=new String[t];
        for(int q=0;q<t;q++) {
            present[q] = store[q];
        }

       }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if(check){
            sort(register,rlocation,rtime,m);

        }
        else {
            sort(events,location,time,n);

        }*/
        sort(events,location,time,n);
        CustomList adapter = new
                CustomList(MainActivity.this, present,prtime,prlocation,register,m);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


        /*Spinner staticSpinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);*/

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        String[] items = new String[] { "All", "Registered", "Following" };

        ArrayAdapter<String> spadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        spinner.setAdapter(spadapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        ch=0;
                        sort(events, location, time, n);// Whatever you want to happen when the first item gets selected
                        break;
                    case 1:
                        ch=1;
                        sort(register, rlocation, rtime, m);// Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        ch=2;
                        sort(follow, flocation, ftime, p);// Whatever you want to happen when the thrid item gets selected
                        break;

                }
                CustomList adapter = new
                        CustomList(MainActivity.this, present, prtime, prlocation, register, m);
                list = (ListView) findViewById(R.id.list);
                list.setAdapter(adapter);
                //Toast.makeText(getApplicationContext(), "Entered choice", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                sort(events, location, time, n);
                //Toast.makeText(getApplicationContext(), "Entered no choice", Toast.LENGTH_LONG).show();

            }


        });
        Spinner spinnertime = (Spinner) findViewById(R.id.spinnertime);

        String[] itemstime = new String[] { "1 hour", "2 hours"};

        ArrayAdapter<String> tiadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemstime);

        spinnertime.setAdapter(tiadapter);

        spinnertime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        timelimit=1;
                        // Whatever you want to happen when the first item gets selected
                        break;
                    case 1:
                        timelimit=2;
                        // Whatever you want to happen when the second item gets selected
                        break;


                }

                switch(ch) {
                    case 0:sort(events, location, time, n);
                        break;
                    case 1:sort(register,rlocation,rtime,m);
                        break;
                    case 2:sort(follow,flocation,ftime,p);
                }
                CustomList adapter = new
                        CustomList(MainActivity.this, present,prtime,prlocation,register,m);
                list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
                //Toast.makeText(getApplicationContext(), "Entered choice", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                sort(events, location, time, n);
                //Toast.makeText(getApplicationContext(), "Entered no choice", Toast.LENGTH_LONG).show();

            }
        });
        /*spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapters = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapters);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                switch (position) {
                    case 0:
                        sort(events, location, time, n);// Whatever you want to happen when the first item gets selected
                        break;
                    case 1:
                        sort(register, rlocation, rtime, m);// Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        sort(follow, flocation, ftime, p);// Whatever you want to happen when the thrid item gets selected
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sort(events, location, time, n);// TODO Auto-generated method stub
            }
        });*/


    }

    /*public void onItemSelected( AdapterView<?> parent,View v, int position, long id) {
        String[] tempo;
        String[] temploc;
        int[][] temptime;

        switch (position) {
            case 0:
                sort(events,location,time,n);// Whatever you want to happen when the first item gets selected
                break;
            case 1:
                sort(register,rlocation,rtime,m);// Whatever you want to happen when the second item gets selected
                break;
            case 2:
                sort(follow,flocation,ftime,p);// Whatever you want to happen when the thrid item gets selected
                break;

        }
        CustomList adapter = new
                CustomList(MainActivity.this, present,prtime,prlocation,register,m);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }*/

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
