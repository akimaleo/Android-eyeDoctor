package com.letit0or1.akimaleo.eyedoctor.amslergrid


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.letit0or1.akimaleo.eyedoctor.R


/**
 * A simple [Fragment] subclass.
 */
class AmslerGridGuide : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_amsler_grid_guide, container, false)
    }

}// Required empty public constructor
