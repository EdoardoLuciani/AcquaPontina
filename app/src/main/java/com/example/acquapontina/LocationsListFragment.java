package com.example.acquapontina;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LocationsListFragment extends Fragment {

    int index;
    String[] locations_name;
    String[] locations_text;
    private static final int n_lakes = 7;
    private static final int n_wellsprings = 4;

    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //set the layout you want to display in First Fragment
        View view = inflater.inflate(R.layout.fragment_locations_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        index = getArguments().getInt("index");
        TextView textViewToChange = (TextView) getActivity().findViewById(R.id.toolbar_title);

        switch (index) {
            case 1:
                textViewToChange.setText(R.string.fragment_interests_points_laghi);
                locations_name = new String[n_lakes];
                locations_text = new String[n_lakes];

                locations_name[0] = getString(R.string.lake_Ninfa);
                locations_name[1] = getString(R.string.lake_Vescovo);
                locations_name[2] = getString(R.string.lake_Carlo);
                locations_name[3] = getString(R.string.lake_Paola);
                locations_name[4] = getString(R.string.lake_Caprolace);
                locations_name[5] = getString(R.string.lake_Monaci);
                locations_name[6] = getString(R.string.lake_Fogliano);

                locations_text[0] = getString(R.string.lake_ninfa_content);
                locations_text[1] = getString(R.string.lake_vescovo_content);
                locations_text[2] = getString(R.string.lake_carlo_content);
                locations_text[3] = getString(R.string.lake_paola_content);
                locations_text[4] = getString(R.string.lake_caprolace_content);
                locations_text[5] = getString(R.string.lake_monaci_content);
                locations_text[6] = getString(R.string.lake_fogliano_content);

                expandableListView = (ExpandableListView) getActivity().findViewById(R.id.places_list);
                expandableListView.setGroupIndicator(null);

                setItems();

                break;
            case 2:
                textViewToChange.setText(R.string.fragment_interests_points_sorgenti);
                locations_name = new String[n_wellsprings];
                locations_text = new String[n_wellsprings];

                locations_name[0] = getString(R.string.wellspring_Suio);
                locations_name[1] = getString(R.string.wellspring_Puzza);
                locations_name[2] = getString(R.string.wellspring_Ninfa);
                locations_name[3] = getString(R.string.wellspring_Monticchio);

                locations_text[0] = getString(R.string.wellspring_suio_content);
                locations_text[1] = getString(R.string.wellspring_acquapuzza_content);
                locations_text[2] = getString(R.string.wellspring_ninfa_content);
                locations_text[3] = getString(R.string.wellspring_monticchio_content);

                expandableListView = (ExpandableListView) getActivity().findViewById(R.id.places_list);
                expandableListView.setGroupIndicator(null);

                setItems();

                break;
            case 3:
                textViewToChange.setText(R.string.fragment_interests_points_bonifica);
                JustifyTextView history_text = getActivity().findViewById(R.id.justifyTextView2);
                ListView list = getActivity().findViewById(R.id.places_list);
                history_text.setVisibility(View.VISIBLE);
                FrameLayout layout = getActivity().findViewById(R.id.layout_locations_list);
                list.setVisibility(View.GONE);
                layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
        //setListener();
    }

    // Setting headers and childs to expandable listview
    void setItems() {

        // Array list for header
        ArrayList<String> header = new ArrayList<String>();

        List<List<String>> lists = new ArrayList<>();
        for(int i=0; i<locations_name.length; i++) {
            lists.add(new ArrayList<String>());
        }

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        for (int i = 0; i < locations_name.length; i++) {
            header.add(locations_name[i]);
            lists.get(i).add(locations_text[i]);
        }

        for (int i = 0; i<locations_name.length; i++) {
            hashMap.put(header.get(i), lists.get(i));
        }


        adapter = new ExpandableListAdapter(getActivity(), header, hashMap);

        expandableListView.setAdapter(adapter);
    }

    // Setting different listeners to expandablelistview
    /*void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(getActivity(),
                        "You clicked : " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view, int groupPos, int childPos, long id) {
                Toast.makeText(
                        getActivity(),
                        "You clicked : " + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }*/

}
