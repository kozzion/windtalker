package com.windtalker.tools;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jaapo on 25-10-2017.
 */

public class ToolsFirebase {

    public static DatabaseReference getContactInbox(){
        return getPersonalInbox(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public static DatabaseReference getContactInbox(String userKey){
        return FirebaseDatabase.getInstance().getReference().child(userKey).child("ContactInbox");
    }

    public static DatabaseReference getPersonalInbox(){
        return getPersonalInbox(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public static DatabaseReference getPersonalInbox(String userKey){
        return FirebaseDatabase.getInstance().getReference().child(userKey).child("MessagingInbox");
    }

    public static DatabaseReference getMixNetInbox(){
        return getPersonalInbox(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public static DatabaseReference geMixNetInbox(String userKey){
        return FirebaseDatabase.getInstance().getReference().child(userKey).child("MixNetInbox");
    }
}

