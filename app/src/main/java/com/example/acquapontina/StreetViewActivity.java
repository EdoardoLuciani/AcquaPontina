package com.example.acquapontina;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class StreetViewActivity extends AppCompatActivity implements
        OnStreetViewPanoramaReadyCallback
        {

    StreetViewPanoramaFragment streetViewPanoramaFragment;
    LatLng loc_coordinates;
    float loc_angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);

        Intent mIntent = getIntent();
        loc_coordinates = (LatLng)mIntent.getParcelableExtra("stv_loc");
        loc_angle = (float) mIntent.getFloatExtra("stv_loc_angle",-1);

        streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetViewMap);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .zoom(streetViewPanorama.getPanoramaCamera().zoom)
                .bearing(loc_angle)
                .tilt(0)
                .build();

        streetViewPanorama.setPosition(loc_coordinates);
        streetViewPanorama.setStreetNamesEnabled(false);
        streetViewPanorama.animateTo(camera,1000);
    }
}
