package com.windtalker.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.windtalker.dto.DTOContact;
import com.windtalker.ui.FragmentChat;
import com.windtalker.ui.FragmentContactDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaapo on 11-11-2017.
 */


public class PagerAdapterChannel extends FragmentStatePagerAdapter {

    List<String> mChannelKeyList;

    public PagerAdapterChannel(FragmentManager fragmentManager, DTOContact contact, List<String> channelKeyList) {
        super(fragmentManager);
        mChannelKeyList = new ArrayList<>(channelKeyList);
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("position", "position: " + position );
        if (position == 0) {
            return new FragmentContactDetails();
        }else {
            return new FragmentChat();
        }
    }

    @Override
    public int getCount() {
        return mChannelKeyList.size() + 1;
    }
}