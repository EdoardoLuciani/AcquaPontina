package com.example.acquapontina;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContactUsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //set the layout you want to display in First Fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        return view;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView textViewToChange = (TextView) getActivity().findViewById(R.id.toolbar_title);
        textViewToChange.setText(R.string.contactus);
    }



}