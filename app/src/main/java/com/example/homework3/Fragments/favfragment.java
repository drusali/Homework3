package com.example.homework3.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.homework3.Activity.MainActivity;
import com.example.homework3.R;

public class favfragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    public TextView textView;
    public favfragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite, container, false);
        textView = view.findViewById(R.id.favouriteView);

        for (int o = 0; o < MainActivity.favCats.size(); o++) {
            textView.setText(textView.getText() + " " + MainActivity.favCats.get(o) + " , "); }
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String string);
    }
}