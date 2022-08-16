package com.example.acquapontina;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static java.sql.Types.NULL;

public class MapsFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapLoadedCallback,
        GoogleMap.OnMapClickListener
        {

    MapView mapView;
    GoogleMap map;

    private static final int n_locations = 11;  // This value has to be ALWAYS: locations_number+1

    Marker m_loc[] = new Marker[n_locations];        // Marker
    LatLng ll_loc[] = new LatLng[n_locations];       // coordinates of the point
    LatLng stv_loc[] = new LatLng[n_locations];      // coordinates of the street view location
    float stv_loc_angle[] = new float[n_locations];  // angle of the street view location

    CameraPosition savedcameraposition = null;
    FloatingActionButton fab;
    Animation slide_from_right;
    Animation slide_to_right;
    int location_number_selected = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView textViewToChange = (TextView) getActivity().findViewById(R.id.toolbar_title);
        textViewToChange.setText(R.string.map);

        slide_from_right = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_from_right);
        slide_to_right = AnimationUtils.loadAnimation(getActivity(), R.anim.exit_to_right);

        fab = (FloatingActionButton)view.findViewById(R.id.map_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent street_view = new Intent(getActivity(), StreetViewActivity.class);
                street_view.putExtra("stv_loc", stv_loc[location_number_selected]);
                street_view.putExtra("stv_loc_angle", stv_loc_angle[location_number_selected]);
                startActivity(street_view);
            }
        });

        mapView = (MapView) view.findViewById(R.id.map_view);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);

        stv_loc[0] = new LatLng(41.5833341,12.956349);
        stv_loc[1] = new LatLng(41.4568829,13.1241926);
        stv_loc[2] = new LatLng(0,0);
        stv_loc[3] = new LatLng(41.2962304,13.0170897);
        stv_loc[4] = new LatLng(41.3410223,12.988311);
        stv_loc[5] = new LatLng(41.3847742,12.9183973);
        stv_loc[6] = new LatLng(41.4004159,12.9073603);
        stv_loc[7] = new LatLng(0,0);
        stv_loc[8] = new LatLng(0,0);
        stv_loc[9] = new LatLng(0,0);
        stv_loc[10] = new LatLng(0,0);

        stv_loc_angle[0] = 213.4f;
        stv_loc_angle[1] = 217.0f;
        stv_loc_angle[2] = NULL;
        stv_loc_angle[3] = 155.0f;
        stv_loc_angle[4] = 224.28f;
        stv_loc_angle[5] = 105.17f;
        stv_loc_angle[6] = 227.59f;
        stv_loc_angle[7] = NULL;
        stv_loc_angle[8] = NULL;
        stv_loc_angle[9] = NULL;
        stv_loc_angle[10] = NULL;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        BitmapDescriptor icon1 = BitmapDescriptorFactory.fromResource(R.drawable.puntatore_1_lago_nosfondo_piccolo_rosso);
        BitmapDescriptor icon2 = BitmapDescriptorFactory.fromResource(R.drawable.puntatore_fiumi_1_piccolo_rosso);
        BitmapDescriptor icon3 = BitmapDescriptorFactory.fromResource(R.drawable.puntatore_sorgenti_1_piccolo_rosso);

        ll_loc[0] = new LatLng(41.58301, 12.95593);    //lago di ninfa
        ll_loc[1] = new LatLng(41.45463, 13.12383);    //laghi del vescovo
        ll_loc[2] = new LatLng(41.45807, 13.1184);  //lago di san carlo
        ll_loc[3] = new LatLng(41.26997, 13.03425);    //lago di paola
        ll_loc[4] = new LatLng(41.34845, 12.97377);    //lago di caprolace
        ll_loc[5] = new LatLng(41.38016, 12.93348);  //lago dei monaci
        ll_loc[6] = new LatLng(41.41176, 12.90823); //lago di fogliano

        ll_loc[7] = new LatLng(41.295693,13.851031); //Sorgente di suio
        ll_loc[8] = new LatLng(41.519558,12.994093); //sorgente di acqua puzza
        ll_loc[9] = new LatLng(41.576366,12.953503); //Gruppo sorgenti ninfa
        ll_loc[10] = new LatLng(41.533301,12.981750); //Gruppo sorgenti Monticchio

        LatLng map_center = new LatLng(41.5086, 13.10933);

        m_loc[0] = map.addMarker(new MarkerOptions().position(ll_loc[0]).title(getString(R.string.lake_Ninfa)).icon(icon1));
        m_loc[1] = map.addMarker(new MarkerOptions().position(ll_loc[1]).title(getString(R.string.lake_Vescovo)).icon(icon1));
        m_loc[2] = map.addMarker(new MarkerOptions().position(ll_loc[2]).title(getString(R.string.lake_Carlo)).icon(icon1));
        m_loc[3] = map.addMarker(new MarkerOptions().position(ll_loc[3]).title(getString(R.string.lake_Paola)).icon(icon1));
        m_loc[4] = map.addMarker(new MarkerOptions().position(ll_loc[4]).title(getString(R.string.lake_Caprolace)).icon(icon1));
        m_loc[5] = map.addMarker(new MarkerOptions().position(ll_loc[5]).title(getString(R.string.lake_Monaci)).icon(icon1));
        m_loc[6] = map.addMarker(new MarkerOptions().position(ll_loc[6]).title(getString(R.string.lake_Fogliano)).icon(icon1));

        m_loc[7] = map.addMarker(new MarkerOptions().position(ll_loc[7]).title(getString(R.string.wellspring_Suio)).icon(icon3));
        m_loc[8] = map.addMarker(new MarkerOptions().position(ll_loc[8]).title(getString(R.string.wellspring_Puzza)).icon(icon3));
        m_loc[9] = map.addMarker(new MarkerOptions().position(ll_loc[9]).title(getString(R.string.wellspring_Ninfa)).icon(icon3));
        m_loc[10] = map.addMarker(new MarkerOptions().position(ll_loc[10]).title(getString(R.string.wellspring_Monticchio)).icon(icon3));

        String html = "[\n" +
                "  {\n" +
                "    \"featureType\": \"administrative.neighborhood\",\n" +
                "    \"stylers\": [\n" +
                "      {\n" +
                "        \"visibility\": \"off\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"featureType\": \"road\",\n" +
                "    \"stylers\": [\n" +
                "      {\n" +
                "        \"visibility\": \"off\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"featureType\": \"road\",\n" +
                "    \"elementType\": \"labels\",\n" +
                "    \"stylers\": [\n" +
                "      {\n" +
                "        \"visibility\": \"off\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"featureType\": \"water\",\n" +
                "    \"elementType\": \"labels.text\",\n" +
                "    \"stylers\": [\n" +
                "      {\n" +
                "        \"visibility\": \"off\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        MapStyleOptions Style = new MapStyleOptions(html);
        map.setMapStyle(Style);
        map.setBuildingsEnabled(false);

        if (savedcameraposition == null) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(map_center,5));
            map.setOnMapLoadedCallback(this);
        }
        else {
            map.moveCamera(CameraUpdateFactory.newCameraPosition(savedcameraposition));
        }

        map.setOnMarkerClickListener(this);
        map.setOnMapClickListener(this);
    }

    @Override
    public void onMapLoaded() {

        LatLng MapCenter = new LatLng(41.5086, 13.10933);

        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(MapCenter).
                tilt(75).
                zoom(9.5f).
                bearing(0).
                build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),2500,null);
    }

    @Override
    public void onMapClick (final LatLng point) {
        fab.startAnimation(slide_to_right);
        fab.hide();
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {

        for(int i=0; i<n_locations; i++){
            if (marker.equals(m_loc[i])) {
                location_number_selected = i;
                break;
            }
        }

        //if ( (fab.getVisibility() == View.GONE) && (stv_locations_to_show[location_number_selected] == true)) {
        //else if (stv_locations_to_show[location_number_selected] == false) {

        if ( (fab.getVisibility() == View.GONE) && (stv_loc_angle[location_number_selected] != NULL)) {
            fab.show();
            fab.startAnimation(slide_from_right);
        }
        else if (stv_loc_angle[location_number_selected] == NULL) {
            fab.startAnimation(slide_to_right);
            fab.hide();
        }

        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedcameraposition = map.getCameraPosition();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
