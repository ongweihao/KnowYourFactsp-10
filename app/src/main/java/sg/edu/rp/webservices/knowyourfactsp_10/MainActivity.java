package sg.edu.rp.webservices.knowyourfactsp_10;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import layout.Frag1;
import layout.Frag2;
import layout.Frag3;

public class MainActivity extends AppCompatActivity {

    int reqcode = 12345;
    ArrayList<Fragment>al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    Button btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (Button)findViewById(R.id.button);
btnRead.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);

        Intent intent = new Intent(MainActivity.this,
                ScheduledNotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MainActivity.this, reqcode,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am = (AlarmManager)
                getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                pendingIntent);

        finish();
    }
});


        vPager = (ViewPager)findViewById(R.id.viewpager1);

        FragmentManager fm = getSupportFragmentManager();

        al=new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new MyFragmentPagerAdapter(fm,al);
        vPager.setAdapter(adapter);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.previous) {
            if(vPager.getCurrentItem()>0){
                int previousPage = vPager.getCurrentItem() -1;
                vPager.setCurrentItem(previousPage,true);
            }
            //Hide the TextView
            return true;
        } else if (id == R.id.random){

            Random r = new Random();
            int random = r.nextInt(vPager.getChildCount());
            vPager.setCurrentItem(random,true);
            return true;
        } else if(id == R.id.next){
            int max = vPager.getChildCount();
            if (vPager.getCurrentItem() < max-1){
                int nextPage = vPager.getCurrentItem() + 1;
                vPager.setCurrentItem(nextPage,true);
            }

            return true;

        }

        return true;

    }
}
