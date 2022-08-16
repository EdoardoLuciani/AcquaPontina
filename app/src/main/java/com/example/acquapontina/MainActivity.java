package com.example.acquapontina;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private NavigationView nv;

    private WhoAreWeFragment whoarewefragment;
    private ContactUsFragment contactusfragment;
    private MapsFragment mapsfragment;
    private InterestsPointsFragment interestspointsfragment;
    private FragmentManager fm;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        if(!preferences.getBoolean("onboarding_complete",false)) {
            Intent onboarding = new Intent(context, PagerActivity.class);
            startActivity(onboarding);
        }
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout)findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        final ImageButton open_menu_btn = findViewById(R.id.open_menu);
        open_menu_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dl.openDrawer(GravityCompat.START);
            }
        });

        final Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        final ImageButton rate_us_btn = findViewById(R.id.action_favorite);
        rate_us_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                rate_us_btn.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                rate_us_btn.startAnimation(rotation);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setTitle(getString(R.string.alert_dialog_title));

                // set dialog message
                alertDialogBuilder
                        .setMessage(getString(R.string.alert_dialog_message))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.alert_dialog_positive_button),new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.googlequicksearchbox&hl=it")));
                                rate_us_btn.setColorFilter(null);
                            }
                        })
                        .setNegativeButton(getString(R.string.alert_dialog_negative_button),new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                rate_us_btn.setColorFilter(null);
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        });

        final ImageButton show_help_btn = findViewById(R.id.help);
        show_help_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent onboarding = new Intent(context, PagerActivity.class);
                startActivity(onboarding);
            }
        });

        nv = findViewById(R.id.nv);
        nv.setItemIconTintList(null);
        nv.setCheckedItem(R.id.nav_map);

        mapsfragment = new MapsFragment();
        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, mapsfragment)
                .addToBackStack(null)
                .commit();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {

                    case R.id.nav_map:
                        nv.setCheckedItem(R.id.nav_map);
                        fm = getSupportFragmentManager();
                        fm.beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                                .replace(R.id.fragment_container, mapsfragment)
                                .addToBackStack(null)
                                .commit();
                        break;

                    case R.id.nav_points:
                        if(interestspointsfragment == null) {
                            interestspointsfragment = new InterestsPointsFragment();
                        }

                        nv.setCheckedItem(R.id.nav_points);
                        fm = getSupportFragmentManager();
                        fm.beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                                .replace(R.id.fragment_container, interestspointsfragment)
                                .addToBackStack(null)
                                .commit();
                        break;

                    case R.id.nav_ways:
                    case R.id.nav_privacypolicy:
                        //nv.setCheckedItem(R.id.nav_privacypolicy);
                        //nv.setCheckedItem(R.id.nav_ways);
                        Toast.makeText(getApplicationContext(), "Feature Ancora in Sviluppo", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_whoarewe:

                        if (whoarewefragment == null) {
                            whoarewefragment = new WhoAreWeFragment();
                        }

                        nv.setCheckedItem(R.id.nav_whoarewe);
                        fm = getSupportFragmentManager();
                        fm.beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                                .replace(R.id.fragment_container, whoarewefragment)
                                .addToBackStack(null)
                                .commit();
                        break;

                    case R.id.nav_contactus:

                        if (contactusfragment == null) {
                            contactusfragment = new ContactUsFragment();
                        }

                        nv.setCheckedItem(R.id.nav_contactus);
                        fm = getSupportFragmentManager();
                        fm.beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                                .replace(R.id.fragment_container, contactusfragment)
                                .addToBackStack(null)
                                .commit();
                        break;

                }
                dl.closeDrawer(GravityCompat.START);
                return false;
            }
        });


    }

}
