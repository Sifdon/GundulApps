package com.gundulsoftware.gundulapps.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gundulsoftware.gundulapps.R;
import com.gundulsoftware.gundulapps.adapter.TabFragmentPagerAdapter;
import com.gundulsoftware.gundulapps.helper.AvatarGenerator;
import com.gundulsoftware.gundulapps.helper.LoadData;
import com.gundulsoftware.gundulapps.helper.ReferenceUrl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /* firebase url */
    private Firebase mRef;
    private Firebase mRefUser; //User now
    private Firebase.AuthStateListener mAuthStateListener; // session firebase
    Firebase myConnectionsStatusRef;
    Firebase UserDataRef;

    private View mProgressBarForUsers;
    private AuthData mAuthData;
    private String mCurrentUserUid;
    private String mCurrentUserEmail;
    private ChildEventListener mListenerUsers;
    private ValueEventListener mConnectedListener;
    private ValueEventListener mUserDataListener;
    private TextView tvHeaderName;
    private TextView tvHeaderEmail;
    private TextView koneksi;
    private ImageView ivHeaderPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRef = new Firebase(ReferenceUrl.FIREBASE_URL);
        mRefUser = mRef.child(ReferenceUrl.CHILD_USERS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        tabs.setTabTextColors(getResources().getColor(android.R.color.darker_gray), getResources().getColor(android.R.color.white));
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);



        mProgressBarForUsers = findViewById(R.id.progress_bar_users);
      //  koneksi = (TextView) findViewById(R.id.txvMenuItem);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        tvHeaderName = (TextView) header.findViewById(R.id.nav_Fullname);
        tvHeaderEmail = (TextView) header.findViewById(R.id.nav_Email);
        ivHeaderPhoto = (ImageView) header.findViewById(R.id.navbarIV);

        mAuthStateListener = new Firebase.AuthStateListener(){
            @Override
            public void onAuthStateChanged(AuthData authData) {
             setAuthenticatedUser(authData);
            }
        };

        mRef.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main,  menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_account) {
            Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
            intent.putExtra("userUid",mCurrentUserUid);
            startActivity(intent);

        } else if (id == R.id.nav_kuis) {



        } else if (id == R.id.nav_kuis_add) {


        } else if (id == R.id.nav_teman) {
            Intent intent = new Intent(MainActivity.this,TemanActivity.class);
            intent.putExtra("userUid",mCurrentUserUid);
            intent.putExtra("userEmail",mCurrentUserEmail);
            startActivity(intent);

        } else if (id == R.id.nav_peringkat) {
            Intent intent = new Intent(MainActivity.this,PeringkatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_pengaturan) {
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_bantuan) {
            Intent intent = new Intent(MainActivity.this,HelpActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_info) {
            Intent intent = new Intent(MainActivity.this,InfoActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_logout) {
            logout();

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadDataUser(){

        myConnectionsStatusRef = mRefUser.child(mCurrentUserUid).child(ReferenceUrl.CHILD_CONNECTION);
        mConnectedListener = mRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if(connected){
                    myConnectionsStatusRef.setValue(ReferenceUrl.KEY_ONLINE);
                    myConnectionsStatusRef.onDisconnect().setValue(ReferenceUrl.KEY_OFFLINE);

                    Toast.makeText(MainActivity.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        UserDataRef = mRefUser.child(mCurrentUserUid);
        mUserDataListener = UserDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LoadData data = dataSnapshot.getValue(LoadData.class);
                String uFullname = data.getFullName();
                String uEmail = data.getUserEmail();
                String uConnection = data.getConnection();

                Resources res = getResources();
                int avatar = AvatarGenerator.getDrawableAvatarId(data.getAvatarId());
                Drawable drawable = res.getDrawable(avatar);
                ivHeaderPhoto.setImageDrawable(drawable);

                tvHeaderName.setText(uFullname);
                tvHeaderEmail.setText(uEmail);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


    private void setAuthenticatedUser (AuthData authData){
        mAuthData = authData;
        if(authData != null){
            mCurrentUserUid = authData.getUid();
            mCurrentUserEmail = (String) authData.getProviderData().get(ReferenceUrl.KEY_EMAIL);
    //        mRefUser.child(mCurrentUserUid).child(ReferenceUrl.CHILD_CONNECTION).setValue(ReferenceUrl.KEY_ONLINE);

            loadDataUser();

        }
        else loadLoginView();

    }

    public void loadLoginView(){
        Intent intent= new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void logout(){
        if(this.mAuthData != null){
            // kondisi user offline
            myConnectionsStatusRef.setValue(ReferenceUrl.KEY_OFFLINE);
            // kondisi session hilang
            mRef.unauth();
            //update Authenticated user dan balik ke login
            setAuthenticatedUser(null);
        }
    }

    /*Progress Bar */

    private void showProgressBarForUser(){
        mProgressBarForUsers.setVisibility(View.VISIBLE);
    }
    private void hideProgressBarForUser(){
        if(mProgressBarForUsers.getVisibility() == View.VISIBLE){mProgressBarForUsers.setVisibility(View.GONE);}
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRef.removeAuthStateListener(mAuthStateListener);
/*
       if(mListenerUsers!=null){
            mRefUser.removeEventListener(mListenerUsers);
        }*/
        if(mConnectedListener!=null){
           myConnectionsStatusRef.removeEventListener(mConnectedListener);
        }
        if(mUserDataListener!=null){
            UserDataRef.removeEventListener(mUserDataListener);
        }
    }


}