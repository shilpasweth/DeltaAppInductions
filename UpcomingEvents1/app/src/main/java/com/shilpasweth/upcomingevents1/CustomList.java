package com.shilpasweth.upcomingevents1;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Lenovo on 8/5/2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] present;
    private final String[] location;
    private final int[][] time;
    private final String[] register;
    private final int m;

    Calendar timenow=new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));

    int n=3,a,b,c,d;


    public CustomList(Activity context,
                      String[] present, int[][] time, String[] location, String[] register,int m) {
        super(context, R.layout.list_single, present);
        this.context = context;
        this.present = present;
        this.location=location;
        this.time=time;
        this.register=register;
        this.m=m;



    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


            LayoutInflater inflater = context.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.list_single, null, true);
            for(int i=0;i<m;i++) {
                if(present[position].equals(register[i])) {
                    rowView.setBackgroundColor(Color.GREEN);
                    break;
                }
            }

            Calendar time5 = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
            time5.set(timenow.get(Calendar.YEAR), 7, time[position][0], time[position][1], time[position][2]);
        Calendar time6 = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
        time6.set(timenow.get(Calendar.YEAR), 7, time[position][3], time[position][4], time[position][5]);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.Event);
            txtTitle.setText(present[position]);
            txtTitle = (TextView) rowView.findViewById(R.id.Location);
            txtTitle.setText(location[position]);
            txtTitle = (TextView) rowView.findViewById(R.id.Time);

                if (time5.after(timenow)) {
                    if ((timenow.get(Calendar.MINUTE) <= time[position][2])) {
                        a = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][1]);
                        b = (time[position][2] - timenow.get(Calendar.MINUTE));

                    } else {
                        a = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][1] - 1);
                        b = (60 - timenow.get(Calendar.MINUTE) + time[position][2]);

                    }
                    if ((timenow.get(Calendar.HOUR) > time[position][1])) {
                        a+= 24;
                        //b = (time[position][2] - timenow.get(Calendar.MINUTE));
                    }

                    txtTitle.setText("begins in " + a/*timenow.get(Calendar.YEAR)*/ + " hours " + b + " mins ");
                } else {
                    if ((timenow.get(Calendar.MINUTE) <= time[position][5])) {
                        c = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][4]);
                        d = (time[position][5] - timenow.get(Calendar.MINUTE));
                    } else {
                        c = (-timenow.get(Calendar.HOUR_OF_DAY) + time[position][4] - 1);
                        d = (60 - timenow.get(Calendar.MINUTE) + time[position][5]);
                    }
                    if ((timenow.get(Calendar.HOUR) > time[position][4])) {
                        c+= 24;
                        //b = (time[position][2] - timenow.get(Calendar.MINUTE));
                    }
                    txtTitle.setText("ends in " + c/*timenow.get(Calendar.YEAR)*/ + " hours " + d + " mins ");
                }



            return rowView;
        }


}
