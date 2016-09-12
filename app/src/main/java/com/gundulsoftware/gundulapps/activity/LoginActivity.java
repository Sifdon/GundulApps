package com.gundulsoftware.gundulapps.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gundulsoftware.gundulapps.R;
import com.gundulsoftware.gundulapps.helper.ReferenceUrl;

public class LoginActivity extends AppCompatActivity {
    private EditText emailUser;
    private EditText passUser;
    private Button btn_login;
    private Button btn_to_register;
    private Firebase mRef;
    private Firebase.AuthStateListener mAuthStateListener;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRef = new Firebase(ReferenceUrl.FIREBASE_URL);

        emailUser = (EditText)findViewById(R.id.email);
        passUser = (EditText)findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_to_register = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");

        btn_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailUser.getText().toString();
                String pass = passUser.getText().toString();

                email = email.trim();
                pass = pass.trim();

                if(email.isEmpty() || pass.isEmpty()){
                    showErrorMessageToUser("Please make sure  you enter a username and password");
                }
                else{
                    showDialog();
                    mRef.authWithPassword(email, pass, authResultHandler);

                }


            }
        });




    }

    Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
        @Override
        public void onAuthenticated(AuthData authData) {
            // Authenticated successfully with payload authData
            // Go to main activity
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            hideDialog();
            startActivity(intent);
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            showErrorMessageToUser(firebaseError.getMessage());
        }
    };

    public void showErrorMessageToUser(String ErrorMessage){
        // Authenticated failed with error firebaseError
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage(ErrorMessage)
                .setTitle(R.string.login_error_title)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
        hideDialog();
    }

    private void showDialog(){
        if(!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog(){
        if(pDialog.isShowing())
            pDialog.dismiss();
    }
}
