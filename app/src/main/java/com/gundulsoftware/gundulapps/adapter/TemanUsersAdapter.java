package com.gundulsoftware.gundulapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gundulsoftware.gundulapps.R;
import com.gundulsoftware.gundulapps.helper.AvatarGenerator;
import com.gundulsoftware.gundulapps.helper.LoadData;
import com.gundulsoftware.gundulapps.helper.PickHelper;
import com.gundulsoftware.gundulapps.helper.ReferenceUrl;

import java.util.List;

/**
 * Created by Ardika Bagus on 24-May-16.
 */
public class TemanUsersAdapter extends RecyclerView.Adapter<TemanUsersAdapter.ViewHolderUsers> {

    private List<LoadData> mTemanUsers;
    private Context mContext;
    private String fUserName;
    private String fUserCreatedAt;

    public TemanUsersAdapter(Context context, List<LoadData> temanUser){
        mContext = context;
        mTemanUsers = temanUser ;

    }

    @Override
    public ViewHolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate layout for each row
        return new ViewHolderUsers(mContext,LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile, parent, false));
    }



    @Override
    public void onBindViewHolder(ViewHolderUsers holder, int position) {

        LoadData teman = mTemanUsers.get(position);

        int userAvatarId= AvatarGenerator.getDrawableAvatarId(teman.getAvatarId());
        Drawable avatarDrawable = ContextCompat.getDrawable(mContext,userAvatarId);

        holder.getUserPhoto().setImageDrawable(avatarDrawable);
        holder.getUsersFirstName().setText(teman.getFullName());
        holder.getStatusConnection().setText(teman.getConnection());


        if(teman.getConnection().equals(ReferenceUrl.KEY_ONLINE)) {
            // Green color
            holder.getStatusConnection().setTextColor(Color.parseColor("#00FF00"));
        }else {
            // Red color
            holder.getStatusConnection().setTextColor(Color.parseColor("#FF0000"));
        }

    }

    @Override
    public int getItemCount() {
        return mTemanUsers.size();
    }

    public void refill(LoadData users){
        mTemanUsers.add(users);
        notifyDataSetChanged();

    }
    public void setNameAndCreatedAt(String userName, String userCreatedAt){
        fUserName = userName;
        fUserCreatedAt=userCreatedAt;
    }
    public void changeUser(int index, LoadData user){
        mTemanUsers.set(index,user);
        notifyDataSetChanged();

    }

    public class ViewHolderUsers extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mUserPhoto;
        private TextView currentUsersFirstName;
        private TextView currentStatusConnection;
        private Context mContextViewHolder;

        public ViewHolderUsers(Context context,View itemView) {
            super(itemView);
            mUserPhoto = (ImageView)itemView.findViewById(R.id.userPhotoProfile);
            currentUsersFirstName=(TextView)itemView.findViewById(R.id.userFirstNameProfile);
            currentStatusConnection=(TextView)itemView.findViewById(R.id.connectionStatus);
            mContextViewHolder = context;

            itemView.setOnClickListener(this);
        }

        public ImageView getUserPhoto(){
          return mUserPhoto;
        }

        public TextView getUsersFirstName(){return currentUsersFirstName;}
        public TextView getStatusConnection(){return currentStatusConnection;}

        @Override
        public void onClick(View v) {
            // Handle click on each row

            int position = getLayoutPosition(); // Get row position
            LoadData teman = mTemanUsers.get(position);

            String customText = teman.getFullName();
            PickHelper.setUsername(customText);

            Intent intent = new Intent("custom");
            intent.putExtra("item",customText);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

         //  teman.setPlayerFullname(currentUsersFirstName);
           // teman.setPlayerCreatedAt(currentStatusConnection);

        }
    }





}
