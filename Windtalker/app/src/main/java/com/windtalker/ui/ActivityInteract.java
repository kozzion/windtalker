package com.windtalker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.windtalker.R;
import com.windtalker.dto.DTOContact;
import com.windtalker.ui.adapter.PagerAdapterChannel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityInteract extends ActivityBase {

    public static final String EXTRA_STRING_CONTACT_KEY = "EXTRA_STRING_CONTACT_KEY";

    public static void start(Context context, String key) {
        Intent intent = new Intent(context, ActivityInteract.class);
        intent.putExtra("EXTRA_STRING_CONTACT_KEY", key);
        context.startActivity(intent);
    }


    @BindView(R.id.interact_view_pager)
    ViewPager mPager;

    private String mContactKey;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact);
        ButterKnife.bind(this);
        mContactKey = getIntent().getStringExtra(EXTRA_STRING_CONTACT_KEY);

        // Instantiate a ViewPager and a PagerAdapter.
        List<String> channelKeys = new ArrayList<>();
        channelKeys.add("Test");
        mPagerAdapter = new PagerAdapterChannel(getSupportFragmentManager(), new DTOContact(), channelKeys);
        mPager.setAdapter(mPagerAdapter);
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


}
