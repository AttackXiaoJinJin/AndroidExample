package com.example.materialdesign.navigation;

/*
 * Created by chenjin on 2017/4/28.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.materialdesign.R;

public class NavigationActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_navigationview);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {   //显示导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                drawerLayout.closeDrawers();
                return true;
            }
        });


        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener(){
                            @Override
                            public void onClick(View view)
                            {
                                Toast.makeText(NavigationActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });



    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {

        switch (menuItem.getItemId())
        {
            case R.id.backup:
                Toast.makeText(this, "YOu Clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "YOu Clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "YOu Clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:break;

        }
        return  true;
    }



}