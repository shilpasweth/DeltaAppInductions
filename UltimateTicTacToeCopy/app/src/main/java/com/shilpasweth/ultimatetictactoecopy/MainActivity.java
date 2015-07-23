package com.shilpasweth.ultimatetictactoecopy;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {


    int p=1;
    int pm=0;
    int w1=0;
    int w2=0;
    int sc1=0;
    int sc2=0;
    int flag=0;
    String tag[][]= new String[9][9];
    boolean [][] no = new boolean[9][9];
    boolean[] b={false,false,false,false,false,false,false,false,false};

    public void invalid(View view,boolean on,int i){
        //String text=view.getText().toString;
        ((ToggleButton) view).setChecked(!on);
        if(flag==0) {
            Toast.makeText(getApplicationContext(), "Invalid move", Toast.LENGTH_SHORT).show();
        }
        if(view.getTag()==null) {
            if (view.getTag() == null && b[i - 1]) {//(view.getTag() == null && b[i - 1])
                view.setBackgroundColor(Color.DKGRAY);

                ((ToggleButton) view).setText(" ");
            } else {
                ((ToggleButton) view).setBackgroundColor(Color.parseColor("#ffd9d9d9"));
                //((ToggleButton) view).setEnabled(true);
                ((ToggleButton) view).setText(" ");
            }
        }
        else {
            if ((view.getTag()).toString() == "2") {

                ((ToggleButton) view).setBackgroundColor(Color.GREEN);//parseColor("#8200ff2e"));
                //((ToggleButton) view).setEnabled(true);
                ((ToggleButton) view).setText("O");
                ((ToggleButton) view).setTextSize(16);
                ((ToggleButton) view).setTextColor(Color.parseColor("#ffff0000"));
                ((ToggleButton) view).setTypeface(null, Typeface.BOLD);


            } else /*((view.getTag()).toString() == "1")*/ {
                ((ToggleButton) view).setBackgroundColor(Color.RED);
                //((ToggleButton) view).setEnabled(true);
                ((ToggleButton) view).setText("X");
                ((ToggleButton) view).setTextSize(16);
                ((ToggleButton) view).setTextColor(Color.parseColor("#ff00ff19"));
                ((ToggleButton) view).setTypeface(null, Typeface.BOLD);

            }
        }
        if(b[i-1]){
            view.setBackgroundColor(Color.DKGRAY);
        }




    }



    ImageView drawingImageView;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("pc", p);
        savedInstanceState.putInt("pmc", pm);
        savedInstanceState.putInt("w1c", w1);
        savedInstanceState.putInt("w2c", w2);
        savedInstanceState.putInt("sc1c", sc1);
        savedInstanceState.putInt("sc2c", sc2);
        savedInstanceState.putBooleanArray("bc", b);
        savedInstanceState.putStringArray("tag0", tag[0]);
        savedInstanceState.putStringArray("tag1", tag[1]);
        savedInstanceState.putStringArray("tag2", tag[2]);
        savedInstanceState.putStringArray("tag3", tag[3]);
        savedInstanceState.putStringArray("tag4", tag[4]);
        savedInstanceState.putStringArray("tag5", tag[5]);
        savedInstanceState.putStringArray("tag6", tag[6]);
        savedInstanceState.putStringArray("tag7", tag[7]);
        savedInstanceState.putStringArray("tag8",tag[8]);
        savedInstanceState.putBooleanArray("no0", no[0]);
        savedInstanceState.putBooleanArray("no1", no[1]);
        savedInstanceState.putBooleanArray("no2", no[2]);
        savedInstanceState.putBooleanArray("no3", no[3]);
        savedInstanceState.putBooleanArray("no4", no[4]);
        savedInstanceState.putBooleanArray("no5", no[5]);
        savedInstanceState.putBooleanArray("no6", no[6]);
        savedInstanceState.putBooleanArray("no7", no[7]);
        savedInstanceState.putBooleanArray("no8", no[8]);


    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        flag=1;
        View view;

        p=savedInstanceState.getInt("pc");
        pm=savedInstanceState.getInt("pmc");
        w1=savedInstanceState.getInt("w1c");
        w2=savedInstanceState.getInt("w2c");
        sc1=savedInstanceState.getInt("sc1c");
        sc2=savedInstanceState.getInt("sc2c");
        b=savedInstanceState.getBooleanArray("bc");
        tag[0]=savedInstanceState.getStringArray("tag0");
        tag[1]=savedInstanceState.getStringArray("tag1");
        tag[2]=savedInstanceState.getStringArray("tag2");
        tag[3]=savedInstanceState.getStringArray("tag3");
        tag[4]=savedInstanceState.getStringArray("tag4");
        tag[5]=savedInstanceState.getStringArray("tag5");
        tag[6]=savedInstanceState.getStringArray("tag6");
        tag[7]=savedInstanceState.getStringArray("tag7");
        tag[8]=savedInstanceState.getStringArray("tag8");
        no[0]=savedInstanceState.getBooleanArray("no0");
        no[1]=savedInstanceState.getBooleanArray("no1");
        no[2]=savedInstanceState.getBooleanArray("no2");
        no[3]=savedInstanceState.getBooleanArray("no3");
        no[4]=savedInstanceState.getBooleanArray("no4");
        no[5]=savedInstanceState.getBooleanArray("no5");
        no[6]=savedInstanceState.getBooleanArray("no6");
        no[7]=savedInstanceState.getBooleanArray("no7");
        no[8]=savedInstanceState.getBooleanArray("no8");
        ((TextView)findViewById(R.id.Player1)).setText("Player 1 : " + Integer.toString(sc1));
        ((TextView)findViewById(R.id.Player2)).setText("Player 2 : " + Integer.toString(sc2));
        for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                view=findViewById(getResources().getIdentifier("t" + i + j, "id", getPackageName()));
                view.setTag(tag[i-1][j-1]);
                invalid(view,!no[i-1][j-1],i);

            }
        }


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.Player1)).setText("Player 1 : " + Integer.toString(sc1));
        ((TextView)findViewById(R.id.Player2)).setText("Player 2 : " + Integer.toString(sc2));
        /*Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;

        drawingImageView = (ImageView) this.findViewById(R.id.DrawingImageView);
        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawingImageView.setImageBitmap(bitmap);

        // Line
        Paint paint = new Paint();
        paint.setColor(Color.rgb(255, 153, 51));
        paint.setStrokeWidth(10);
        float startx = (width-(float)(35*9))/(float)2;
        float starty = height-(35*9);
        float endx = startx;
        float endy =height;
        canvas.drawLine(startx, starty, endx, endy, paint);*/
    }
     public void click(View view){
         boolean on=false,on1=false,on2=false,on3=false,on4=false,on5=false,on6=false,on7=false,on8=false,on9=false;
         //Toast.makeText(getApplicationContext(), "Checking", Toast.LENGTH_SHORT).show();
         int id =view.getId();
         int i,j = 0,row=0,column=0;
         int r;
         int diag=0;
         flag=0;

         View view1=null,view2=null,view3=null,view4=null,view5=null,view6=null,view7=null,view8=null,view9=null;
         on = ((ToggleButton) view).isChecked();

         for (i = 1; i < 10; i++) {
             for (j = 1; j < 10; j++) {
                 if (id == getResources().getIdentifier("t" + i + j, "id", getPackageName())) {


                     row = i;
                     column = j;
                 }

             }
         }

              /*else {//(view.getTag() == null && b[i - 1])
             view.setBackgroundColor(Color.DKGRAY);
             ((ToggleButton) view).setChecked(true);
             ((ToggleButton) view).setText(" ");
             //((ToggleButton) view).setEnabled(true);

         }
        /*else {
            ((ToggleButton) view).setBackgroundColor(Color.LTGRAY);
            //((ToggleButton) view).setEnabled(true);
            ((ToggleButton) view).setText(" ");
        }*/

         if ((pm != row && pm != 0) ||!on) {
                 invalid(view, on, row);
             }


         else {
             choose(view,row);
                 view.setTag(p);
                 tag[row-1][column-1]=(view.getTag()).toString();
                 no[row-1][column-1]=on;
                 pm = column;
                 if (b[pm-1]){
                     pm=0;
                 }

                 r = column % 3;
                 if (r == 0) {
                     r=3;
                 }
                 view1 = findViewById(getResources().getIdentifier("t" + row + r, "id", getPackageName()));
                 on1 = ((ToggleButton) view1).isChecked();
                 view2 = findViewById(getResources().getIdentifier("t" + row + (r + 3), "id", getPackageName()));
                 on2 = ((ToggleButton) view2).isChecked();
                 view3 = findViewById(getResources().getIdentifier("t" + row + (r + 6), "id", getPackageName()));
                 on3 = ((ToggleButton) view3).isChecked();
                 if(on1&& on2&&on3 &&(view1.getTag()==view2.getTag())&&(view1.getTag())==(view3.getTag())){
                     diag=1;
                 }

                 view4 = findViewById(getResources().getIdentifier("t" + row + column, "id", getPackageName()));
                 on4 = ((ToggleButton) view4).isChecked();

                 if (r == 1) {
                     view5 = findViewById(getResources().getIdentifier("t" + row + (column + 1), "id", getPackageName()));
                     on5 = ((ToggleButton) view5).isChecked();
                     view6 = findViewById(getResources().getIdentifier("t" + row + (column + 2), "id", getPackageName()));
                     on6 = ((ToggleButton) view6).isChecked();
                 }
                 if (r == 2) {
                     view5 = findViewById(getResources().getIdentifier("t" + row + (column + 1), "id", getPackageName()));
                     on5 = ((ToggleButton) view5).isChecked();
                     view6 = findViewById(getResources().getIdentifier("t" + row + (column - 1), "id", getPackageName()));
                     on6 = ((ToggleButton) view6).isChecked();
                 }
                 if (r == 3) {
                     view5 = findViewById(getResources().getIdentifier("t" + row + (column - 1), "id", getPackageName()));
                     on5 = ((ToggleButton) view5).isChecked();
                     view6 = findViewById(getResources().getIdentifier("t" + row + (column - 2), "id", getPackageName()));
                     on6 = ((ToggleButton) view6).isChecked();
                 }

                 if(on4 && on5 && on6 &&(view4.getTag())==(view5.getTag())&&(view4.getTag())==(view6.getTag())){
                     diag=1;
                 }

                 if(column==1||column==5||column==9) {
                     view7 = findViewById(getResources().getIdentifier("t" + row + 1, "id", getPackageName()));
                     on7 = ((ToggleButton) view7).isChecked();
                     view8 = findViewById(getResources().getIdentifier("t" + row + 5, "id", getPackageName()));
                     on8 = ((ToggleButton) view8).isChecked();
                     view9 = findViewById(getResources().getIdentifier("t" + row + 9, "id", getPackageName()));
                     on9 = ((ToggleButton) view9).isChecked();
                     if(on7&&on8&&on9&&view7.getTag()==view8.getTag()&&view7.getTag()==view9.getTag()){
                         diag=1;
                     }
                 }
                 if(column==3||column==5||column==7) {
                     view7 = findViewById(getResources().getIdentifier("t" + row + 3, "id", getPackageName()));
                     on7 = ((ToggleButton) view7).isChecked();
                     view8 = findViewById(getResources().getIdentifier("t" + row + 5, "id", getPackageName()));
                     on8 = ((ToggleButton) view8).isChecked();
                     view9 = findViewById(getResources().getIdentifier("t" + row + 7, "id", getPackageName()));
                     on9 = ((ToggleButton) view9).isChecked();
                     if(on7&& on8&&on9 &&view7.getTag()==view8.getTag()&&view7.getTag()==view9.getTag()){
                         diag=1;
                     }
                 }
                 if (diag==1) {
                     block(row,column);
                     if (p % 2 == 0) {
                         w2++;
                         if (w2 == 3) {
                             sc2++;
                             Toast.makeText(getApplicationContext(), "Player 2 wins the game", Toast.LENGTH_LONG).show();
                             ((TextView)findViewById(R.id.Player2)).setText("Player 2 : " + Integer.toString(sc2));
                             reset();
                         }
                     }
                     else {
                         w1++;
                         if (w1 == 3) {
                             sc1++;
                             Toast.makeText(getApplicationContext(), "Player 1 wins the game", Toast.LENGTH_LONG).show();
                             ((TextView)findViewById(R.id.Player1)).setText("Player 1 : " + Integer.toString(sc1));
                             reset();
                         }
                     }
                 }
                 p++;
                 if(p==3){
                     p-=2;
                 }
             }

     }

    public void resetgame(View view){
        reset();
        sc1=0;
        sc2=0;
        ((TextView)findViewById(R.id.Player1)).setText("Player 1 : " + Integer.toString(sc1));
        ((TextView)findViewById(R.id.Player2)).setText("Player 2 : " + Integer.toString(sc2));

    }

    public void block(int i,int k){
        View view;
        b[i-1]=true;
        if(i==k){
            pm=0;
        }

        for(int j=1;j<10;j++){
                view=findViewById(getResources().getIdentifier("t" + i + j, "id", getPackageName()));

                if(!((ToggleButton)view).isChecked()) {
                    ((ToggleButton) view).setChecked(true);
                    ((ToggleButton) view).setText(" ");
                }
                view.setBackgroundColor(Color.DKGRAY);
        }

    }

    public void reset(){
        View view;
        w1=0;
        w2=0;
        pm=0;
        p=1;
        for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
                view=findViewById(getResources().getIdentifier("t" + i + j, "id", getPackageName()));
                ((ToggleButton) view).setBackgroundColor(Color.LTGRAY);
                ((ToggleButton) view).setEnabled(true);
                ((ToggleButton) view).setText(" ");
                ((ToggleButton) view).setTag(null);
                ((ToggleButton) view).setChecked(false);

            }
        }

    }

    public void choose(View view,int i){
        /*if(view.getTag()==null&&b[i-1]){
            view.setBackgroundColor(Color.DKGRAY);
            ((ToggleButton) view).setChecked(true);
            ((ToggleButton) view).setText(" ");
            ((ToggleButton) view).setEnabled(true);

        }
        else if(view.getTag()==null&&!b[i-1]){
            ((ToggleButton) view).setBackgroundColor(Color.LTGRAY);
            ((ToggleButton) view).setEnabled(true);
            ((ToggleButton) view).setText(" ");
        }*/
        if(p%2==0){

            ((ToggleButton) view).setBackgroundColor(Color.GREEN);//parseColor("#8200ff2e"));
            ((ToggleButton) view).setEnabled(true);
            ((ToggleButton) view).setText("O");
            ((ToggleButton) view).setTextSize(16);
            ((ToggleButton) view).setTextColor(Color.parseColor("#ffff0000"));
            ((ToggleButton) view).setTypeface(null, Typeface.BOLD);


        }
        else{
            ((ToggleButton) view).setBackgroundColor(Color.RED);
            ((ToggleButton) view).setEnabled(true);
            ((ToggleButton) view).setText("X");
            ((ToggleButton) view).setTextSize(16);
            ((ToggleButton) view).setTextColor(Color.parseColor("#ff00ff19"));
            ((ToggleButton) view).setTypeface(null, Typeface.BOLD);

        }


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
