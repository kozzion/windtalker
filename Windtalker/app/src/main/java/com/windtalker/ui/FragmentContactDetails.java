package com.windtalker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.windtalker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaapo on 11-11-2017.
 */

public class FragmentContactDetails extends Fragment {


    @BindView(R.id.contact_details_text_key)
    TextView mTextKey;
    @BindView(R.id.contact_details_text_name)
    TextView mTextName;


    private String mContactKey;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_contact_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }




}