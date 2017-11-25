package com.windtalker.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.windtalker.R;
import com.windtalker.database.IDataSnapshot;
import com.windtalker.database.IDatabase;
import com.windtalker.database.IDatabaseValueListener;
import com.windtalker.database.firebase.DatabaseFirebase;
import com.windtalker.dto.DTOContact;
import com.windtalker.model.ModelApplication;
import com.windtalker.ui.adapter.AdapterContact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.ACTION_VIEW;

/**
 * Created by jaapo on 30-10-2017.
 */

public class ActivityContact extends ActivityBase {

    @BindView(R.id.contact_recycler_contact)
    RecyclerView mRecyclerContact;
    @BindView(R.id.contact_button_share)
    Button mButtonShare;

    private AdapterContact mAdapter;
    private IDatabase mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        mButtonShare.setOnClickListener(view -> executeShare());
        mAdapter = new AdapterContact();
        mDatabase = new DatabaseFirebase(FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerContact.setLayoutManager(layoutManager);
        mRecyclerContact.setAdapter(mAdapter);

        mDatabase.getReference(this).child("Contact").addValueEventListener(this, new IDatabaseValueListener() {
            @Override
            public void onDataChange(IDataSnapshot dataSnapshot) {
                List<DTOContact> contactList = new ArrayList<>();
                Log.e(TAG, "contactList: " + contactList.size() );
                for (IDataSnapshot contactSnapshot : dataSnapshot.getChildren()) {
                    DTOContact contact = contactSnapshot.getValue(DTOContact.class);
                    contactList.add(contact);
                }
                mAdapter.setData(contactList);
                mDatabase.getReference(ActivityContact.this).child("Contact").push();
            }

            @Override
            public void onCancelled(String error) {

            }
        });
        if (getIntent().getAction() != null){
            if (getIntent().getAction().equals(ACTION_VIEW)) {
                Uri uri = getIntent().getData();
                String contactKey = uri.getPath().substring(1);
                DTOContact contact = new DTOContact(contactKey, "");
                mDatabase.getReference(this).child("Contact").child(contactKey).setValue(this, contact);
            }
        }

    }

    public void executeShare() {
        String applicationText = getResources().getString(R.string.contact_share_title);
        String shareText = getResources().getString(R.string.contact_share_text);
        //TODO link in code is bad

        String linkText = "http://www.windtalker.com/" + FirebaseAuth.getInstance().getUid();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, applicationText);
        intent.putExtra(Intent.EXTRA_TEXT, shareText + linkText);
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.contact_share_choose)));
    }

    @Override
    public void onBackPressed() {
        ModelApplication.getInstance(this).logout(this);
        super.onBackPressed();
    }

    private void executeSelectContact() {
        startActivity(new Intent(this, ActivityInteract.class));
    }


}
