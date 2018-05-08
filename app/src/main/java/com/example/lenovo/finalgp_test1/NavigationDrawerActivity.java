package com.example.lenovo.finalgp_test1;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        mDrawerToggle.syncState();
        super.onPostCreate( savedInstanceState );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_navigation_drawer );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.navigation_drawer, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {

        FragmentManager fragmentManager = getFragmentManager();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Hotel)
        {
            Intent intent = new Intent( NavigationDrawerActivity.this , HotelActivity.class );
            startActivity( intent );
        }
        else if (id == R.id.nav_Flight)
        {
            Intent intent = new Intent( NavigationDrawerActivity.this , FlightActivity.class);
            startActivity( intent );
        }
        else if (id == R.id.nav_Car)
        {
            Intent intent = new Intent( NavigationDrawerActivity.this , CarActivity.class);
            startActivity( intent );        }
        else if (id == R.id.nav_Tour)
        {
            Intent intent = new Intent( NavigationDrawerActivity.this , ToursActivity.class);
            startActivity( intent );
        }

        else if (id == R.id.nav_MyHistory)
        {
            Intent intent = new Intent( NavigationDrawerActivity.this , MyHistoryActivity.class);
            startActivity( intent );
        }
        else if (id == R.id.nav_EditProfile)
        {
            Intent intent = new Intent( NavigationDrawerActivity.this , EditProfileActivity.class);
            startActivity( intent );
        }
        else if (id == R.id.nav_SignOut)
        {
            Intent intent = new Intent( NavigationDrawerActivity.this , LoginActivity.class);
            startActivity( intent );
        }
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
