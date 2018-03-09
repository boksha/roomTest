package com.example.miodragmilosevic.roomtest.startattack;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miodragmilosevic.roomtest.common.dialog.AlertDialogFragment;
import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.attacklist.AttacksListActivity;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.createattackrecord.CreateAttackRecordActivity;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;
import com.example.miodragmilosevic.roomtest.settings.SettingsListActivity;

public class StartAttackActivity extends BaseToolbarActivity {

    private static final String DELETE_ALL_DIALOG_TAG = "deleteAllDialog";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Button mAttackButton;
    private LinearLayout mAddAttackContainer;
    private LinearLayout mReminderContainer;
    private StartAttackViewModel mViewModel;
    private TextView mCounter;
    private AlertDialogFragment.OnPositiveClickListener mOnDeleteAllListener = new AlertDialogFragment.OnPositiveClickListener() {
        @Override
        public void onClick() {
            mViewModel.onDeleteAllButtonClick();
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mTitle.setText(R.string.app_name);
            mDrawerLayout = findViewById(R.id.drawer_layout);
            mAddAttackContainer = findViewById(R.id.add_attack_container);
            mReminderContainer = findViewById(R.id.reminder_container);
            FloatingActionButton addAttackButton = findViewById(R.id.add_attack);
            addAttackButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, CreateAttackRecordActivity.class);
                mViewModel.setDefaultState();
                startActivity(intent);
            });
            mAttackButton = findViewById(R.id.attack_btn);
            mAttackButton.setOnClickListener(view -> {
                mViewModel.onAttackButtonClick();
            });
            mCounter = findViewById(R.id.attack_counter);
            setupNavigationDrawer();
            mViewModel = ViewModelProviders.of(this,
                    new StartAttackViewModel.Factory(new StartAttackRepository(AppDataBase.get(getApplicationContext()).getEpiAttackDao())))
                    .get(StartAttackViewModel.class);
            mViewModel.getLiveData().observe(this, viewData ->
                    {
                        int viewState = viewData.getState();
                        Log.i("Miki", "onCreate: view state" + viewState);
                        if (viewState == StartAttackUiModel.STARTED) {
                            mAttackButton.setText(R.string.btn_stop_attack);
                            mCounter.setText(viewData.getFormattedElapsedTime());
                            mAddAttackContainer.setVisibility(View.GONE);
                            mReminderContainer.setVisibility(View.GONE);
                        } else if (viewState == StartAttackUiModel.IN_PROCESSING) {
                            Intent intent = new Intent(this, CreateAttackRecordActivity.class);
                            intent.putExtra(CreateAttackRecordActivity.EXTRA_START_TIME, viewData.getStartTime());
                            intent.putExtra(CreateAttackRecordActivity.EXTRA_ELAPSED_TIME, viewData.getElapsedTime());
                            mViewModel.setDefaultState();
                            startActivity(intent);
                        }  else if (viewState == StartAttackUiModel.DELETE_COMPLETED) {
                            Toast.makeText(this,R.string.toast_delete_all_completed,Toast.LENGTH_SHORT).show();
                            mViewModel.setDefaultState();
                        }  else if (viewState == StartAttackUiModel.ERROR) {
                            Toast.makeText(this,R.string.toast_delete_all_failed,Toast.LENGTH_SHORT).show();
                            mViewModel.setDefaultState();
                        }  else {
                            mAttackButton.setText(R.string.btn_start_attack);
                            mAddAttackContainer.setVisibility(View.VISIBLE);
                            mReminderContainer.setVisibility(View.VISIBLE);
                            mCounter.setText(viewData.getFormattedElapsedTime());
                        }
                    }
            );
        }

        @Override
        protected int getLayoutId() {
            return R.layout.activity_start_attack;
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
        public void onBackPressed() {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }


        private void setupNavigationDrawer() {
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
            if (id == R.id.nav_adjust_medicaments) {
                Log.i("Miki", "onNavigationItemSelected: adjust medicaments");
                Intent intent = new Intent(this, SettingsListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_adjust_activities_before_attack) {
                Log.i("Miki", "onNavigationItemSelected: adjust activities");
                Intent intent = new Intent(this, SettingsListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_adjust_attack_locations) {
                Log.i("Miki", "onNavigationItemSelected: adjust locations");
                Intent intent = new Intent(this, SettingsListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_adjust_possible_causes) {
                Log.i("Miki", "onNavigationItemSelected: adjust possible cause");
                Intent intent = new Intent(this, SettingsListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_adjust_attack_types) {
                Log.i("Miki", "onNavigationItemSelected: adjust attack types");
                Intent intent = new Intent(this, SettingsListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_delete_all) {
                Log.i("Miki", "onNavigationItemSelected: deleteall");
                showDeleteAllDialog();
            } else if (id == R.id.nav_logout) {
                Log.i("Miki", "onNavigationItemSelected: logout");

            }
        }

        @Override
        protected void onResume() {
            super.onResume();
            AlertDialogFragment dialog = (AlertDialogFragment) getSupportFragmentManager().findFragmentByTag(DELETE_ALL_DIALOG_TAG);
            if (dialog != null) {
                dialog.setOnPositiveClickListener(mOnDeleteAllListener);
            }
        }

        protected void showDeleteAllDialog() {
            AlertDialogFragment deleteAllDialog = AlertDialogFragment.newInstance(R.string.title_dialog_delete_all,R.string.message_delete_all);
            deleteAllDialog.setOnPositiveClickListener(mOnDeleteAllListener);
            deleteAllDialog.show(getSupportFragmentManager(), DELETE_ALL_DIALOG_TAG);
        }
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


