package com.gundulsoftware.gundulapps.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gundulsoftware.gundulapps.helper.AvatarGenerator;
import com.gundulsoftware.gundulapps.helper.LoadData;
import com.gundulsoftware.gundulapps.R;
import com.gundulsoftware.gundulapps.helper.ReferenceUrl;


public class ProfilActivity extends AppCompatActivity {
    private Firebase mRef;
    private Firebase mRefUser; //User now
    private Firebase.AuthStateListener mAuthStateListener; // session firebase
    Firebase myConnectionsStatusRef;
    Firebase UserDataRef;
    private String mCurrentUserUid;
    private ImageView photouser;

    TextView namauser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfil);
        setSupportActionBar(toolbar);
        Intent data = getIntent();
        namauser = (TextView)findViewById(R.id.nama_user);
        photouser = (ImageView)findViewById(R.id.foto_user);

        mCurrentUserUid = data.getStringExtra("userUid");

        mRef = new Firebase(ReferenceUrl.FIREBASE_URL);
        mRefUser = mRef.child(ReferenceUrl.CHILD_USERS);
        UserDataRef = mRefUser.child(mCurrentUserUid);
        UserDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LoadData data = dataSnapshot.getValue(LoadData.class);
                String uFullname = data.getFullName();

                Resources res = getResources();
                int avatar = AvatarGenerator.getDrawableAvatarId(data.getAvatarId());
                Drawable drawable = res.getDrawable(avatar);

                photouser.setImageDrawable(drawable);
                namauser.setText(uFullname);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        FloatingActionButton floatbtn = (FloatingActionButton) findViewById(R.id.edit_profil_user);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this,editProfilActivity.class);
                intent.putExtra("userUid",mCurrentUserUid);
                startActivity(intent);
            }
        });







    }
}
