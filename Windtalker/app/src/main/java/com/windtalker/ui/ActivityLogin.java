package com.windtalker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.windtalker.R;
import com.windtalker.model.ModelApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityLogin extends ActivityBase {

    @BindView(R.id.login_edit_email)
    TextView mEmail;
    @BindView(R.id.login_edit_password)
    TextView mPassword;
    @BindView(R.id.login_button_login)
    Button mButtonLogin;
    @BindView(R.id.login_button_register)
    Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        mEmail.setText(ModelApplication.getInstance(this).getEmail());
        mPassword.setText(ModelApplication.getInstance(this).getPassword());
        mButtonLogin.setOnClickListener(view -> executeLogin());
        mButtonRegister.setOnClickListener(view -> executeRegister());


        ModelApplication instance = ModelApplication.getInstance(this);
        if(instance.isLoggedIn())
        {
            startActivity(new Intent(this, ActivityContact.class));
        }
    }

    private void executeRegister() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        ModelApplication.getInstance(this).saveLogin(this, email, password);
        FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                {
                    firebaseAuth.removeAuthStateListener(this);
                    startActivity(new Intent(ActivityLogin.this, ActivityContact.class));
                }
            }
        };
        ModelApplication.getInstance(this).attemptRegisterAndLogin(email, password, listener);
    }



    public void executeLogin()
    {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        ModelApplication.getInstance(this).saveLogin(this, email, password);
        FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                {
                    firebaseAuth.removeAuthStateListener(this);
                    startActivity(new Intent(ActivityLogin.this, ActivityContact.class));
                }
            }
        };

        ModelApplication.getInstance(this).attemptLogin(email, password,listener);
    }

}

