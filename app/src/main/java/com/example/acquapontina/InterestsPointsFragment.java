package com.example.acquapontina;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InterestsPointsFragment extends Fragment {

    private LocationsListFragment locations_list_fragment;
    private FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //set the layout you want to display in First Fragment
        View view = inflater.inflate(R.layout.fragment_interests_points, container, false);
        return view;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView textViewToChange = (TextView) getActivity().findViewById(R.id.toolbar_title);
        textViewToChange.setText(R.string.Interestpoints);

        final TextView lakes = getActivity().findViewById(R.id.interests_points_lakes);
        lakes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CallListFragment(1);
            }
        });

        final TextView wellsprings = getActivity().findViewById(R.id.interests_points_wellsprings);
        wellsprings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CallListFragment(2);
            }
        });

        final TextView drainage = getActivity().findViewById(R.id.interests_points_drainage);
        drainage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CallListFragment(3);
            }
        });
    }

    public void CallListFragment(int index) {
        Bundle data = new Bundle();
        data.putInt("index", index);
        locations_list_fragment = new LocationsListFragment();
        locations_list_fragment.setArguments(data);

        fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                .replace(R.id.fragment_container, locations_list_fragment)
                .addToBackStack(null)
                .commit();
    }

}