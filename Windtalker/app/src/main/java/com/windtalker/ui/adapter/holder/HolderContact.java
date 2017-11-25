package com.windtalker.ui.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.windtalker.R;
import com.windtalker.dto.DTOContact;
import com.windtalker.ui.ActivityInteract;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Dmytro Gorodnytskyi
 *         on 03-Feb-17.
 */

public class HolderContact extends RecyclerView.ViewHolder {


    @BindView(R.id.item_contact_text_key)
    TextView mTextKey;
    @BindView(R.id.item_contact_text_name)
    TextView mTextName;

    private View mRootView;

    public HolderContact(@NonNull View rootView) {
        super(rootView);
        ButterKnife.bind(this, rootView);
        this.mRootView = rootView;
    }

    public void bind(@NonNull final DTOContact contact) {
        mTextKey.setText(contact.Key);
        mTextName.setText(contact.Name);

        mRootView.setOnClickListener(v -> ActivityInteract.start(mRootView.getContext(), contact.Key));


    }
}
