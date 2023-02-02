package com.netcetera.fitbyte_uek;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_activity1) {
            startActivity(new Intent(this, activity1.class));
            return true;
        } else if (id == R.id.menu_activity2) {
            startActivity(new Intent(this, Activity2.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}