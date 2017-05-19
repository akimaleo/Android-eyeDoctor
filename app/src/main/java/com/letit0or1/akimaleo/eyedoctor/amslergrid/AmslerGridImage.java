package com.letit0or1.akimaleo.eyedoctor.amslergrid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.letit0or1.akimaleo.eyedoctor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmslerGridImage extends Fragment {


    public AmslerGridImage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amsler_grid_image, container, false);
    }

}
