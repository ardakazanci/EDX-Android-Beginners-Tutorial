package com.ardakazanci.moviemanager.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ardakazanci.moviemanager.R;
import com.ardakazanci.moviemanager.fragments.NowPlayingFragment;
import com.ardakazanci.moviemanager.fragments.UpcomingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // OnCreate metodu içinde ilk olarak
        // Varsayılan gösterilecek fragment

        showFragment(NowPlayingFragment.class); // ı göstereceğiz.


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();
        // Bu referans ilgili fragment dosyasına . class olarak geçişi belirtecek.
        Class fragment = null;

        if (id == R.id.nav_now_playing) {
            fragment = NowPlayingFragment.class;
            showFragment(fragment);
        } else if (id == R.id.nav_upcoming) {
            fragment = UpcomingFragment.class;
            showFragment(fragment);
        } else if (id == R.id.nav_logout) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Bu metot ile birlikte FrameLayout içine ilgili Fragment ' ı ekleyeceğiz.
    private void showFragment(Class fragment) {

        Fragment mFragment = null;

        try {
            mFragment = (Fragment) fragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // Fragment Manager ile birlikte yapılan işlem
        // İlgili butona tıklayan görünümün flContent içinde değişimimin sağlanması
        // Tıklanan Fragment fl content ile yer değişterecek

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction() // İçeriğin içeriğini değiştirmek için.
                .replace(R.id.flContent,mFragment)
                .commit();


    }
}
