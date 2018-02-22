package com.example.miodragmilosevic.roomtest.mainscreen;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.attacklist.AttacksListActivity;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.settings.SettingsListActivity;

public class MainActivity extends BaseToolbarActivity {

    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;
    private int mCurrentPosition = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText(R.string.app_name);

//        mBottomNavigationView = findViewById(R.id.navigation);
//        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        mViewPager = findViewById(R.id.viewpager);
//        setupViewPager(mViewPager);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        setupNavigationDrawer();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Miki", "onSaveInstanceState: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_attacks_list:
                startActivity(new Intent(this, AttacksListActivity.class));
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    private void setupViewPager(ViewPager viewPager) {
//        MainScreenPagerAdapter adapter = new MainScreenPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mBottomNavigationView.getMenu().getItem(mCurrentPosition).setChecked(false);
//                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
//                mCurrentPosition = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }

    private void setupNavigationDrawer() {

        // set a custom shadow that overlays the main content when the drawer opens
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
//        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//                R.layout.drawer_list_item, mPlanetTitles));
//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout,
                        R.string.drawer_open, R.string.drawer_close) {
                    /** Called when a drawer has settled in a completely open state. */
                    public void onDrawerOpened(View drawerView) {
                        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                    }

                    /** Called when a drawer has settled in a completely closed state. */
                    public void onDrawerClosed(View view) {
                        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                    }
                };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(item -> {
            handleDrawerNavigation(item);
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void handleDrawerNavigation(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_adjust_medicaments){
            Log.i("Miki", "onNavigationItemSelected: adjust medicaments");
            Intent intent = new Intent(this, SettingsListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_adjust_activities_before_attack){
            Log.i("Miki", "onNavigationItemSelected: adjust activities");
            Intent intent = new Intent(this, SettingsListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_adjust_attack_locations){
            Log.i("Miki", "onNavigationItemSelected: adjust locations");
            Intent intent = new Intent(this, SettingsListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_adjust_possible_causes){
            Log.i("Miki", "onNavigationItemSelected: adjust possible cause");
            Intent intent = new Intent(this, SettingsListActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_adjust_attack_types){
            Log.i("Miki", "onNavigationItemSelected: adjust attack types");
            Intent intent = new Intent(this, SettingsListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_delete_all) {
            Log.i("Miki", "onNavigationItemSelected: deleteall");
            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            Log.i("Miki", "onNavigationItemSelected: logout");

        }
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mViewPager.setCurrentItem(0);
//                    return true;
//                case R.id.navigation_dashboard:
//                    mViewPager.setCurrentItem(1);
//                    return true;
//                case R.id.navigation_notifications:
//                    mViewPager.setCurrentItem(2);
//                    return true;
//            }
//            return false;
//        }
//    };
}


//    Predefinisana lista napada:
//
//
//
//    o   Aura (predosećaj koji neposredno prethodi napadu)
//§  Izolovano javljanje aure nezavisno do napada (da/ne)
//§  Opišite predosećaj svojim rečima (slobodan unos)
//§  Tip aure (izabrati iz padajućeg niza jednu ili više: vidna / slušna / osećaj dodira, mravinjanja, toplote, bockanja, bola u dobro definisanom delu tela / mirisna / osećaj ukusa / osećaj već doživljenog, nikad doživljenog / strah / nejasno definisan predosećaj da će se dogoditi napad / vrtoglavica / glavobolja / osećaj lupanja, preskakanja srca / osećaj gušenja / nelagoda u gornjem delu stomaka koja se penje naviše / mučnina / drugo)
//    o   Autonomni napad (izabrati jednu od ponuđenih stavki samo ako predloženi simptomi predstavljaju jedinu manifestaciju napada) Usporen, ubrzan srčani rad / ježenje kože / crvenilo lica / povraćanje / nevoljno umokravanje
//    o   Napad samo sa pomućenjem svesti (izabrati samo ako je pomućenje svesti jedini simptom napada i ako je epizoda praćena nesećanjem za događaj)
//    o   Mioklonizmi (neritmični trzajevi ruku ili nogu sa ili bez gubitka svesti)
//    o   Tonički (zadržavanje ruke/noge/čitavog tela u određenom stegnutom položaju sa ili bez gubitka svesti)
//    o   Klonički (ritmički trzajevi ruke ili noge sa ili bez gubitka svesti)
//    o   Generalizovani toničko-klonički napadi (Grand mal) (gubitak svesti sa padom ukoliko je pacijent u uspravnom položaju uz snažno i ritmično grčenje ruku i nogu, obično u trajanju od par minuta)
//    o   Hipermotorni napad (snažni i brzi pokreti donjih ekstemiteta nalik hodanju, trčanju, preskakanju / pokreti gornjih ekstremiteta nalik udaranju u boksu ili odmahivanju)
//    o   Gelastički ili dakristički napad (napad bezrazložnog smeha ili vidljivog osmeha na licu ili napad bezrazložnog plača sa ili bez gubitka svesti ili sećanja)
//    o   Automotorni napad (pokreti mljackanja / pućenja usta / sisanja / oblizivanja / pokreti rukama kao da se nešto traži, ispipava / pokreti kruženja šakama i stopalima sa ili bez gubitka svesti ili sećanja)
//    o   Atonički napad (nagli pad usled napada kao izolovani događaj, ili nagla slabost držanja glave)
//    o   Drugo


